package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import domain.Order;
import domain.Customer;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/jspf/navigation_menu.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Home</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");


    Customer customer = (Customer) session.getAttribute("customer");
    Order order = (Order) session.getAttribute("order");
    if (order == null) {
        session.setAttribute("order", new Order(customer));
    }
    if (customer != null) {

      out.write("\n");
      out.write("<a href=\"home.jsp\">Home</a>\n");
      out.write("<a href=\"view_products.jsp\">Browse Products</a>\n");
      out.write("<a href=\"checkout.jsp\">View Cart</a>\n");
      out.write("<a href=\"Log_out_servlet\">Log out</a>\n");
      out.write("\n");
      out.write("\n");
} else {

      out.write("\n");
      out.write("\n");
      out.write("<a href=\"home.jsp\">Home</a>\n");
      out.write("<a href=\"view_products.jsp\">Browse Products</a>\n");
      out.write("<a href=\"checkout.jsp\">View Cart</a>\n");
      out.write("<a href=\"log_in.jsp\">Log in</a>\n");
}
      out.write('\n');
      out.write("\n");
      out.write("        <h1>Welcome to the Flanges and Widgets Shop</h1>\n");
      out.write("        <p>We sell the biggest range of flanges, widgets, and doohickies\n");
      out.write("        at the best prices!</p>\n");
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
}
