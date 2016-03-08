/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pj.test.bean;

/**
 *
 * @author Administrator
 */
public class Person extends Man{
    
    private static Man sMan=new Man();
    
    private Man man=new Man();
    private String name="name";
    private int age;
    private String sex;
    
    protected String protectString;
    
    public String publicString;
    
    private static String STATIC_ATTR_PRI="private static not final";
    public static String STATIC_ATTR_PUB="public static not final";
    
    public static final String S_ATTR_FINAL_PUB="final pub";
    
    private static final String S_ATTR_FINAL_PRI="final pub";
    
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString(){
        return "Name="+getName()+";Age="+getAge()+";Sex="+sex;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public static String exec(){
        return "------";
            }

}
