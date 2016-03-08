package pj.db.util;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author pengju 2011-7-13 22:16
 */
public class DefaultObjectWrapper implements ObjectWrapper {

    private Class clazz;

    public DefaultObjectWrapper(Class clazz) {
        this.clazz = clazz;
    }

    public List wrap(ResultSet rs) throws Exception{
        LinkedList list = new LinkedList();
        ResultSetMetaData meta = rs.getMetaData();

        Method[] ms = clazz.getMethods();
        HashMap<String, Method> msMap = new HashMap<String, Method>();
        for (Method myMd : ms) {
            if (myMd.getParameterTypes().length==1 && myMd.getName().startsWith("set")) {
                msMap.put(myMd.getName(), myMd);//搜索setter方法
            }
        }
        
        String s = null;
        String n = null;
        
        HashMap<String,String> nts=new HashMap<String,String>();
        for(int i=1;i<=meta.getColumnCount();i++){
            n=meta.getColumnName(i);
            s=toSetter(n);
            if(msMap.containsKey(s)){
                nts.put(n, s);
            }
        }

        if(nts.isEmpty()){//属性没有对应列
            return list;
        }
        Set<Map.Entry<String,String>> set=nts.entrySet();
        
        while (rs.next()) {
            Object t = clazz.newInstance();//指定类型必须有无参构造函数
            for(Map.Entry<String,String> e:set){
                msMap.get(e.getValue()).invoke(t,rs.getObject(e.getKey()));
            }
            list.add(t);
        }//end while
        
        return list;
    }

    private String toSetter(String colName) {
        return "set"+colName.substring(0,1).toUpperCase()+colName.substring(1);
    }
}
