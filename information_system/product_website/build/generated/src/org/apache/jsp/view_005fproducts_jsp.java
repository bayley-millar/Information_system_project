package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Collection;
import dao.DatabaseProductManager;
import domain.Product;
import domain.Order;
import domain.Customer;

public final class view_005fproducts_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>view products</title>\n");
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
      out.write("        <h1>View Products</h1>\n");
      out.write("        ");
      out.write("\n");
      out.write("        <a href=\"view_products.jsp\">All</a>\n");
      out.write("        ");

        Collection<String> categories = new DatabaseProductManager().returnAllCategories();
        // get category from request
        String category = (String)request.getParameter("category");
        // if cat is null, or ALL the get all
        Collection<Product> products = null;
        if(category == null){
             products = new DatabaseProductManager().returnAllProducts();
            
        }else{
            products = new DatabaseProductManager().filterByCategory(category);
        }
        for(String cat:categories){
      out.write("\n");
      out.write("            <a href=\"view_products.jsp?category=");
      out.print( cat);
      out.write('"');
      out.write('>');
      out.print( cat );
      out.write("</a>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        <table border=\"1\" style=\"width:100%\">\n");
      out.write("            <tr>\n");
      out.write("                <th>ID</th>\n");
      out.write("                <th>Name</th> \n");
      out.write("                <th>Description</th>\n");
      out.write("                <th>Category</th>\n");
      out.write("                <th>price</th>\n");
      out.write("                <th>Quantity</th>\n");
      out.write("            </tr>\n");
      out.write("            ");
for(Product product : products) {
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(product.getProductID());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(product.getName());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(product.getDescription());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(product.getCategory());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(product.getPrice());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(product.getQuantityInStock());
      out.write("</td>\n");
      out.write("                <td><form action=\"BuyServlet\"><input type=\"hidden\" name=\"productID\"value=");
      out.print(product.getProductID());
      out.write("><input type=\"submit\" value=\"Buy\"></form></td>\n");
      out.write("            </tr>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </table>\n");
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
