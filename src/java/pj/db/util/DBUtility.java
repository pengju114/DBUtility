package pj.db.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author pengju 2011-7-13 21:44
 */
public class DBUtility {

    private static final ConnectionManager connMgr = ConnectionFactory.getConnMgr();

    public static Connection getConnection() throws SQLException {
        return connMgr.getConnection();
    }

    private static void setParameter(PreparedStatement ps, int index, Object val) throws Exception {
        if (val == null) {
            ps.setObject(index, val);
        } else {
            String className = val.getClass().getSimpleName();
            if (className.equals("Integer")) {
                ps.setInt(index, (Integer) val);
            } else {
                PreparedStatement.class.getMethod(mm(className, "set"), new Class[]{Integer.TYPE, val.getClass()}).invoke(ps, new Object[]{Integer.valueOf(index), val});
            }
        }
    }

    /**
     *
     * @param preparedSql 用来创建PreparedStatement对象的字符串
     * @param values    用来设置PreparedStatement对象的值,值的个数一定要和"?"的个数一致,可以为null
     * @return 返回更新行数
     */
    public static int update(String preparedSql, Object[] values) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        int c = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(preparedSql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    setParameter(ps,i+1,values[i]);
                }
            }

            c = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            returnConnection(conn);
            if (ps != null) {
                ps.close();
            }
        }

        return c;
    }

    /**
     *
     * @param target 要更新的对象
     * @param key 指定target中的标识，即update XXX set XXX=XXX where [这里将用key参数作条件,多个可用逗号分开.key不会被更新]
     * @return 返回影响行
     */
    public static int update(Object target, String key) throws Exception {
        if (key == null) {
            throw new NullPointerException("key不能为null");
        }
        if (target == null) {
            throw new NullPointerException("指定对象不能为空");
        }
        String[] keys = key.split(",");
        Method[] mds = target.getClass().getMethods();
        HashMap<String, Method> mdMap = new HashMap<String, Method>();
        String table = target.getClass().getSimpleName();

        String mName = null;
        for (Method md : mds) {
            mName = md.getName();
            if (md.getParameterTypes().length == 0 && mName.startsWith("get")) {
                mdMap.put(mName, md);
            }
        }

        Connection conn = getConnection();
        HashMap<String, Integer> getterToIndex = new HashMap<String, Integer>();
        PreparedStatement chk = conn.prepareStatement("select top 0 * from " + table);
        ResultSet rsChk = chk.executeQuery();
        ResultSetMetaData meta = rsChk.getMetaData();
        String name = null;
        String getter = null;
        String set = " set ";

        int i = 1;
        for (int j = 1; j <= meta.getColumnCount(); j++) {
            name = meta.getColumnName(j);
            getter = mm(name, "get");
            if (mdMap.containsKey(getter) && !Util.contains(keys, name)) {
                getterToIndex.put(getter, Integer.valueOf(i));
                set += name + "=?,";
                i++;
            }
        }
        if (set.endsWith(",")) {
            set = set.substring(0, set.length() - 1);
        }

        String sql = "update " + table + set + " where " + Util.join(keys, "=? and ") + "=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        Iterator<Entry<String, Integer>> entry = getterToIndex.entrySet().iterator();
        Object[] args = new Object[]{};
        while (entry.hasNext()) {
            Entry<String, Integer> each = entry.next();
            setParameter(ps,each.getValue(),mdMap.get(each.getKey()).invoke(target, args));
        }

        for (String k : keys) {
            setParameter(ps,i,mdMap.get(mm(k, "get")).invoke(target, args));
            i++;
        }

        int c = ps.executeUpdate();

        chk.close();
        rsChk.close();
        ps.close();
        returnConnection(conn);
        return c;
    }

    public static int insert(String tableName, String[] columNames, Object[] values) throws SQLException, Exception {
        return update("insert into " + tableName + "(" + Util.join(columNames, ",") + ") values(" + Util.joinString("?", columNames.length, ",") + ")", values);
    }

    /**
     * 通过sql检索数据库，将每一条记录当做指定type对象的属性并通过反射的方法设置进去，最后将所有的type对象保存到链表中
     * @param sql SQL语句
     * @param type 对应的对象类型
     * @return 保存指定的type对象的链表
     * @throws Exception
     */
    public static List select(String sql, Class type) throws Exception {
        return select(sql, null, type);
    }

    /**
     * 将执行sql后返回的结果用wrapper封装到对象中后返回
     * @param sql SQL语句
     * @param wrapper 包装器
     * @return 对象链表
     * @throws Exception 
     */
    public static List select(String sql, ObjectWrapper wrapper) throws Exception {
        return select(sql, null, wrapper);
    }

    /**
     * 通过preparedSql检索数据库，将每一条记录当做指定type对象的属性并通过反射的方法设置进去，最后将所有的type对象保存到链表中
     * @param preparedSql PreparedStatement的SQL语句
     * @param values 用来设置PreparedStatement参数的值,个数要跟？号的个数一致
     * @param type 对应的对象类型
     * @return 保存指定的type对象的链表
     * @throws Exception
     */
    public static List select(String preparedSql, Object[] values, Class type) throws Exception {
        return select(preparedSql, values, new DefaultObjectWrapper(type));
    }

    /**
     * 通过preparedSql检索数据库，将每一条记录用wrapper封装到对象中，最后将所有的对象保存到链表中返回
     * @param preparedSql PreparedStatement的SQL语句
     * @param values 用来设置PreparedStatement参数的值,个数要跟？号的个数一致
     * @param wrapper 包装器
     * @return 保存指定的type对象的链表
     * @throws Exception 
     */
    public static List select(String preparedSql, Object[] values, ObjectWrapper wrapper) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(preparedSql);

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                setParameter(ps,i+1,values[i]);
            }
        }

        ResultSet rs = ps.executeQuery();
        List list = wrapper.wrap(rs);
        ps.close();
        rs.close();
        returnConnection(conn);
        return list;
    }

    /**
     *
     * @param list 要存储对象的链表，里面的对象必须是同一类的，如果没有指定表名就使用list里面对象所属类的类名
     * @param expKey 无需插入的列名，如自动增长的id；多个可用逗号隔开.允许为null
     * @return 更新行数
     * @throws Exception
     */
    public static int store(List list, String expKey) throws Exception {
        if (list.isEmpty()) {
            return 0;
        }
        return store(list, list.get(0).getClass().getSimpleName(), expKey);
    }

    /**
     *
     * @param list 要存储对象的链表，里面的对象必须是同一类的
     * @param table 表名
     * @param expKey 无需插入的列名，如自动增长的id；多个可用逗号隔开.允许为null
     * @return 更新行数
     * @throws Exception
     */
    public static int store(List list, String table, String expKey) throws Exception {
        if (list.isEmpty()) {
            return 0;
        }
        Class c = list.get(0).getClass();
        Method[] ms = c.getMethods();
        HashMap<String, Method> msMap = new HashMap<String, Method>();
        HashMap<String, Integer> colNameToIndex = new HashMap<String, Integer>();

        String[] expNames = null;
        String[] sn = null;
        if (expKey != null) {
            expNames = expKey.split(",");
            sn = new String[expNames.length];
            for (int j = 0; j < expNames.length; j++) {
                sn[j] = mm(expNames[j], "get");
            }
        }

        for (Method m : ms) {//取所有的get方法
            if (m.getParameterTypes().length==0 && m.getName().startsWith("get") && !Util.contains(sn, m.getName())) {
                msMap.put(m.getName(), m);
            }
        }//end for

        Connection conn = getConnection();
        PreparedStatement test = conn.prepareStatement("select top 0 * from " + table);
        ResultSet rs = test.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();
        String names = "";
        for (int k = 0, count = 1; k < meta.getColumnCount(); k++) {//取所有的列名
            String n = meta.getColumnName(k + 1);
            if (Util.contains(expNames, n)) {
                continue;
            }
            colNameToIndex.put(n, Integer.valueOf(count));
            count++;
            names += n + ",";
        }
        if (names.endsWith(",")) {
            names = names.substring(0, names.length() - 1);
        }

        String sql = "insert into " + table + "(" + names + ") values(" + Util.joinString("?", colNameToIndex.size(), ",") + ")";
        PreparedStatement ps = conn.prepareStatement(sql);

        Iterator it = list.iterator();
        Object[] o = new Object[]{};
        while (it.hasNext()) {
            Object t = it.next();
            Iterator<Entry<String, Integer>> ns = colNameToIndex.entrySet().iterator();
            while (ns.hasNext()) {
                Entry<String, Integer> each = ns.next();
                Method m = msMap.get(mm(each.getKey(), "get"));

                if (m == null) {
                    ps.setObject(each.getValue(), null);
                    continue;
                }
                setParameter(ps,each.getValue(),m.invoke(t, o));
            }//end colNameToIndex while

            ps.addBatch();
        }//end list while

        int result = Util.sum(ps.executeBatch());
        returnConnection(conn);
        ps.close();
        return result;
    }

    private static String mm(String name, String prefix) {
        String pre = name.substring(0, 1);
        return (prefix + pre.toUpperCase() + name.substring(1));
    }

    public static void returnConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Results query(String sql) throws SQLException, Exception {
        return query(sql, null);
    }

    /**
     *
     * @param preparedSql PreparedStatement的SQL语句
     * @param values 用来设置PreparedStatement参数的值,个数要跟？号的个数一致
     * @return 保存查询结果集的Results对象
     * @throws SQLException
     */
    public Results query(String preparedSql, Object[] values) throws Exception {
        MyResults myRs = new MyResults();
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(preparedSql);

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                setParameter(ps,i+1,values[i]);
            }
        }

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();

        int colsCount = meta.getColumnCount();
        for (int i = 1; i <= colsCount; i++) {
            myRs.addColumnName(meta.getColumnName(i), i);
        }

        while (rs.next()) {
            HashMap<String, Object> map = myRs.newResult();
            map.put("row", rs.getRow());
            for (int i = 1; i <= colsCount; i++) {
                map.put(meta.getColumnName(i), rs.getObject(i));
            }
        }

        ps.close();
        rs.close();
        returnConnection(conn);

        return myRs;
    }

    private class MyResults extends LinkedList<Result> implements Results {

        private HashMap<Integer, String> columnNames;

        public MyResults() {
            columnNames = new HashMap<Integer, String>();
        }

        public void addColumnName(String name, int columnIndex) {
            columnNames.put(Integer.valueOf(columnIndex), name);
        }

        public HashMap<String, Object> newResult() {
            MyResult r = new MyResult();
            add(r);
            r.setRow(size());
            return r;
        }

        public int getColumnCount() {
            return columnNames.size();
        }

        public String[] getColumnNames() {
            String[] names = new String[columnNames.keySet().size()];
            Iterator<Map.Entry<Integer, String>> i = columnNames.entrySet().iterator();
            int j = 0;
            while (i.hasNext()) {
                names[j] = i.next().getValue();
                j++;
            }
            return names;
        }

        public String getColumnName(int index) {
            return getColumnNames()[index];
        }

        public int getSize() {
            return size();
        }

        public LinkedList<Result> getRows() {
            return this;
        }

        private class MyResult extends HashMap<String, Object> implements Result {

            private int row;

            public MyResult() {
                row = 0;
            }

            public HashMap<String, Object> getValues() {
                return this;
            }

            @Override
            public String toString() {
                Iterator<Entry<String, Object>> en = entrySet().iterator();
                StringBuilder b = new StringBuilder("{\"row\":\"");
                b.append(row).append("\",");
                while (en.hasNext()) {
                    Entry<String, Object> e = en.next();
                    b.append("\"").append(e.getKey()).append("\":\"").append(e.getValue()).append("\",");
                }
                String s = b.toString();
                if (s.endsWith(",")) {
                    s = s.substring(0, s.length() - 1);
                }
                return s + "}";
            }

            public String toString(String separator) {
                return Util.join(this.values().iterator(), separator);
            }

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public boolean getBoolean(int index) {
                return (Boolean) get(columnNames.get(Integer.valueOf(index)));
            }

            public byte getByte(int index) {
                return (Byte) get(columnNames.get(Integer.valueOf(index)));
            }

            public Date getDate(int index) {
                return (Date) get(columnNames.get(Integer.valueOf(index)));
            }

            public Time getTime(int index) {
                return (Time) get(columnNames.get(Integer.valueOf(index)));
            }

            public long getLong(int index) {
                return (Long) get(columnNames.get(Integer.valueOf(index)));
            }

            public double getDouble(int index) {
                return (Double) get(columnNames.get(Integer.valueOf(index)));
            }

            public float getFloat(int index) {
                return (Float) get(columnNames.get(Integer.valueOf(index)));
            }

            public int getInt(int index) {
                return (Integer) get(columnNames.get(Integer.valueOf(index)));
            }

            public Object getObject(int index) {
                return get(columnNames.get(Integer.valueOf(index)));
            }

            public String getString(int index) {
                return getString(columnNames.get(Integer.valueOf(index)));
            }

            public Timestamp getTimestamp(int index) {
                return getTimestamp(columnNames.get(Integer.valueOf(index)));
            }

            public boolean getBoolean(String name) {
                return (Boolean) get(name);
            }

            public byte getByte(String name) {
                return (Byte) get(name);
            }

            public Date getDate(String name) {
                return (Date) get(name);
            }

            public Time getTime(String name) {
                return (Time) get(name);
            }

            public long getLong(String name) {
                return (Long) get(name);
            }

            public double getDouble(String name) {
                return (Double) get(name);
            }

            public float getFloat(String name) {
                return (Float) get(name);
            }

            public int getInt(String name) {
                return (Integer) get(name);
            }

            public Object getObject(String name) {
                return get(name);
            }

            public String getString(String name) {
                return String.valueOf(get(name));
            }

            public Timestamp getTimestamp(String name) {
                return (Timestamp) get(name);
            }
        }//end class MyResult
    }//end class MyResults
}
