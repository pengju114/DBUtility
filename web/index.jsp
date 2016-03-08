
<%@page import="pj.custom.tags.Property"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="pj.test.bean.Question"%>
<%@page import="java.util.Enumeration"%>
<%@page import="pj.test.bean.Person"%>
<%@page import="java.util.LinkedList"%>
<%@page import="pj.page.ext.Paging"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="pj.db.util.DBUtility"%>
<%@page import="java.sql.Connection"%>
<%@page import="pj.db.util.Util"%>
<%@page import="pj.db.util.Result"%>
<%@page import="java.util.Iterator"%>
<%@page import="pj.db.util.Results"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://pengju/tag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%--<p:filter charset="" object="" type="" var="" />
    <p:invoke method="" object="" type="" var="" />
    <p:out value="" defaultValue="" />
    <p:param name="" value="" />
<p:property field="" object="" type="" var="" />--%>
    <body>
        <form action="ws.upload" method="post">
            <input type="text" name="name" />
            <textarea name="content"></textarea>
            <input type="submit" value="submit"/>
        </form>
        <%--<jsp:useBean id="db" class="pj.db.util.DBUtility" scope="request" />--%>
        <%--

       List<Question> qs=DBUtility.select("select * from Question where id=? or id=?",new Object[]{3,5}, Question.class);
       
       for(Question q:qs){
          out.write(q.getTitle()+"<br />");
          //for(String n:names)out.write("<td>"+r.getString(n)+"</td>");
          //out.write("</tr>");
           //out.write(r.toString());
          q.setMoney(q.getMoney()+10);
          DBUtility.update(q, "id");
       }
      DBUtility.update("delete from Question where id>?", new Object[]{31});
       //DBUtility.update("update Question set content='new content' where id=?", new Object[]{2});
       //out.write("</table>");
        //out.write(paging.toString());

        --%>

        <%--
       

        LinkedList<Person> l=new LinkedList<Person>();
        Person p1=new Person();
        p1.setAge(67);
        p1.setName("John");
        p1.setSex("男");

        Person p2=new Person();
        p2.setAge(49);
        p2.setName("PENGJU");
        p2.setSex("男");

         Person p3=new Person();
         p3.setAge(88);
         p3.setName("Merry");
         p3.setSex("女");

         l.add(p1);
         l.add(p2);
         l.add(p3);

        <% request.setAttribute("attr", "man"); %>
        
        <p:property field="pj:test:bean:Person.STATIC_ATTR_PRI" /><br />
        <p:property field="p.name" />
        <p:property field="p.{attr}.parentName" />
         int c=DBUtility.store(l);
         if(c>0)out.write("Succ");
         else out.write("Fail"); <applet height="300" width="400" code="pj.test.test.ChatApplet"></applet>
        --%>
        <jsp:useBean id="p" class="pj.test.bean.Person" />
        <%


            Person pe = new Person();
            Property.setProperty(Person.class, pe, "parentAge", 6);

            request.setAttribute(pj.test.bean.Common.USER_KEY, pe);
        %>
        <p:property field="{pj:test:bean:Common.USER_KEY}.parentAge" />


    </body>
</html>
