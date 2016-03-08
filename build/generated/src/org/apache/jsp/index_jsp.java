package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import pj.custom.tags.Property;
import java.util.HashMap;
import java.util.List;
import pj.test.bean.Question;
import java.util.Enumeration;
import pj.test.bean.Person;
import java.util.LinkedList;
import pj.page.ext.Paging;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import pj.db.util.DBUtility;
import java.sql.Connection;
import pj.db.util.Util;
import pj.db.util.Result;
import java.util.Iterator;
import pj.db.util.Results;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(1);
    _jspx_dependants.add("/WEB-INF/pj.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_p_property_field_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_p_property_field_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_p_property_field_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("    \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    ");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <form action=\"ws.upload\" method=\"post\">\n");
      out.write("            <input type=\"text\" name=\"name\" />\n");
      out.write("            <textarea name=\"content\"></textarea>\n");
      out.write("            <input type=\"submit\" value=\"submit\"/>\n");
      out.write("        </form>\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      pj.test.bean.Person p = null;
      synchronized (_jspx_page_context) {
        p = (pj.test.bean.Person) _jspx_page_context.getAttribute("p", PageContext.PAGE_SCOPE);
        if (p == null){
          p = new pj.test.bean.Person();
          _jspx_page_context.setAttribute("p", p, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\n");
      out.write("        ");



            Person pe = new Person();
            Property.setProperty(Person.class, pe, "parentAge", 6);

            request.setAttribute(pj.test.bean.Common.USER_KEY, pe);
        
      out.write("\n");
      out.write("        ");
      if (_jspx_meth_p_property_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_p_property_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  p:property
    pj.custom.tags.PropertyTag _jspx_th_p_property_0 = (pj.custom.tags.PropertyTag) _jspx_tagPool_p_property_field_nobody.get(pj.custom.tags.PropertyTag.class);
    _jspx_th_p_property_0.setPageContext(_jspx_page_context);
    _jspx_th_p_property_0.setParent(null);
    _jspx_th_p_property_0.setField("{pj:test:bean:Common.USER_KEY}.parentAge");
    int _jspx_eval_p_property_0 = _jspx_th_p_property_0.doStartTag();
    if (_jspx_th_p_property_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_p_property_field_nobody.reuse(_jspx_th_p_property_0);
      return true;
    }
    _jspx_tagPool_p_property_field_nobody.reuse(_jspx_th_p_property_0);
    return false;
  }
}
