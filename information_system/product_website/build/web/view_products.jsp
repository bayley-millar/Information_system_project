<%-- 
    Document   : view_products
    Created on : 1/09/2015, 4:16:50 PM
    Author     : milba845
--%>

<%@page import="java.util.Collection"%>
<%@page import="dao.DatabaseProductManager"%>
<%@page import="domain.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>view products</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_menu.jspf" %>
        <h1>View Products</h1>
        <%--------------------------------------------------------------- 
        do a category filter with references
        --------------------------------------------------------------%>
        <a href="view_products.jsp">All</a>
        <%
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
        for(String cat:categories){%>
            <a href="view_products.jsp?category=<%= cat%>"><%= cat %></a>
            <%}%>
        <table border="1" style="width:100%">
            <tr>
                <th>ID</th>
                <th>Name</th> 
                <th>Description</th>
                <th>Category</th>
                <th>price</th>
                <th>Quantity</th>
            </tr>
            <%for(Product product : products) {%>
            <tr>
                <td><%=product.getProductID()%></td>
                <td><%=product.getName()%></td>
                <td><%=product.getDescription()%></td>
                <td><%=product.getCategory()%></td>
                <td><%=product.getPrice()%></td>
                <td><%=product.getQuantityInStock()%></td>
                <td><form action="BuyServlet"><input type="hidden" name="productID"value=<%=product.getProductID()%>><input type="submit" value="Buy"></form></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
