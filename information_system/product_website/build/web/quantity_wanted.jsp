<%-- 
    Document   : quantity_wanted
    Created on : 1/09/2015, 5:21:20 PM
    Author     : milba845
--%>

<%@page import="domain.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>quantity wanted</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_menu.jspf" %>
        <h1>How many do you want to buy?</h1>
        <%Product product = (Product) session.getAttribute("product");%>
        <p>You selected: <%=product.getName()%></p>

        <p>Quantity to buy</p>
        <p><label for="quantity">How many?</label></p>
        
        <form action="Add_to_cart_servlet" method="post">
        <input id="quantity" name="quantity" type="text">
        <input type="submit" value="Add to cart">
        </form>

    </body>
</html>
