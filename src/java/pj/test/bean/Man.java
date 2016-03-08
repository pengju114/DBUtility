/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.test.bean;

/**
 *
 * @author PENGJU
 */
public class Man {
    private String parentName="pName";
    private Integer parentAge;
    private String parentSex;
    
    private String parentProtectString;
    
    public String parentPublicString="pPubic";
    
    private static String PARENT_STATIC_ATTR_PRI="private static not final";
    public static String PARENT_STATIC_ATTR_PUB="public static not final";
    
    public static final String PARENT_S_ATTR_FINAL_PUB="final pub";
    
    private static final String PARENT_S_ATTR_FINAL_PRI="final pub";

    /**
     * @return the parentName
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * @param parentName the parentName to set
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * @return the parentProtectString
     */
    public String getParentProtectString() {
        return parentProtectString;
    }

    /**
     * @param parentProtectString the parentProtectString to set
     */
    public void setParentProtectString(String parentProtectString) {
        this.parentProtectString = parentProtectString;
    }

    /**
     * @return the parentAge
     */
    public Integer getParentAge() {
        return parentAge;
    }

    /**
     * @param parentAge the parentAge to set
     */
    public void setParentAge(Integer parentAge) {
        this.parentAge = parentAge;
    }

}
