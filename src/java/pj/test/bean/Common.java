/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pj.test.bean;

public class Common {
    
    public static String USER_KEY="currentUser";
    protected int id;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public static String format(String time){
        if(time==null)return "";
        return time.replaceFirst("\\.\\d+$","");
    }
}
