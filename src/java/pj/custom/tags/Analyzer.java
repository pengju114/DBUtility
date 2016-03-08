/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package pj.custom.tags;

import java.util.regex.Pattern;

/**
 *2011-10-23
 * @author PENGJU
 */
public interface Analyzer {
    public Pattern myElPattern=Pattern.compile("\\{((\\w+(:\\w+)+)\\.)?(\\w+(\\.\\w+)*)\\}");
    public Pattern simplePattern=Pattern.compile("((\\w+(:\\w+)+)\\.)?(\\w+(\\.\\w+)*)");
    
    public void replaceEl();
    
    public Object eval();
}
