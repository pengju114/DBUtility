
package pj.db.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 *
 * @author pengju 2011-1-9 12:51
 */
public interface Result {
    public HashMap<String,Object> getValues();
    @Override
    public String toString();
    
    /**
     * 将结果用指定的separator组合并返回
     * @param separator 用来分隔每个结果的字符串
     * @return
     */
    public String toString(String separator);
    
    /**
     * 
     * @return 取当前行下标,从1开始
     */
    public int getRow();

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public boolean getBoolean(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public byte getByte(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public Date getDate(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public Time getTime(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public Timestamp getTimestamp(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public long getLong(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public double getDouble(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public float getFloat(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public int getInt(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public Object getObject(int index);

    /**
     *
     * @param index 从1开始
     * @return 指定列的值
     */
    public String getString(int index);


    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public boolean getBoolean(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public byte getByte(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public Date getDate(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public Time getTime(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public Timestamp getTimestamp(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public long getLong(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public double getDouble(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public float getFloat(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public int getInt(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public Object getObject(String name);

    /**
     * 取指定列name的值
     * @param name 列名,区分大小写
     * @return 指定列的值
     */
    public String getString(String name);

}
