/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.db.util;

import java.sql.ResultSet;
import java.util.List;

/**
 *将一个结果集封装到对象中，并将对象放到链表中
 * @author pengju 2011-7-13
 */
public interface ObjectWrapper {
    public List wrap(ResultSet rs)throws Exception;
}
