
package pj.db.util;

import java.util.Iterator;

/**
 *
 * @author pengju 2011-1-9 14:20
 */
public class Util {
    public static String join(Object[] values,String seperator){
        StringBuilder buf=new StringBuilder();
        for(Object e:values){
            buf.append(e).append(seperator);
        }
        if(values.length>0)buf.replace(buf.length()- seperator.length(),buf.length(),"");
        return buf.toString();
    }

    public static String join(Iterator values,String seperator){
        StringBuilder buf=new StringBuilder();
        int count=0;
        while(values.hasNext()){
            buf.append(values.next()).append(seperator);
            count++;
        }
        if(count>0)buf.replace(buf.length()- seperator.length(),buf.length(),"");
        return buf.toString();
    }

    public static String joinString(String str,int count,String seperator){//产生count个用seperator隔开的str字符串
        String[] strs=new String[count];
        for(int i=0;i<strs.length;i++)strs[i]=str;
        return join(strs, seperator);
    }

    public static int sum(int[] v){
        int s=0,b=0,e=v.length-1;
        if(v.length<=0)return 0;
        if(v.length%2!=0)s+=v[v.length/2];
        while(b<e){
            s+=(v[b]+v[e]);
            b++;
            e--;
        }
        return s;
    }

    public static boolean contains(Object[] set,Object value){
        if(set!=null)for(Object each:set){
            if(each.equals(value))return true;
        }
        return false;
    }

}


