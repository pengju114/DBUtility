
package pj.db.util;

import java.util.LinkedList;

/**
 *
 * @author pengju 2011-1-9 12:52
 */
public interface Results {
    /**
     * 取指定行
     * @param index 行下标,从0开始
     * @return 指定行的Result对象
     */
    public Result get(int index);

    /**
     *
     * @return 列数
     */
    public int getColumnCount();

    /**
     *
     * @return 列名数组
     */
    public String[] getColumnNames();

    /**
     * 取指定列的列名
     * @param index 列下标,从0开始
     * @return 列名
     */
    public String getColumnName(int index);

    /**
     *
     * @return 结果总数
     */
    public int getSize();

    /**
     *
     * @return 结果集
     */
    public LinkedList<Result> getRows();
}
