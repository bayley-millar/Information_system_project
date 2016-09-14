<%-- 
    Document   : checkout
    Created on : 1/09/2015, 5:26:49 PM
    Author     : milba845
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>checkout</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_menu.jspf" %>
        <h1>Shopping Cart</h1>
        
        <table border="1" style="width:100%">
            <tr>
                <td>Product</td>
                <td>Price</td> 
                <td>Quantity</td>
                <td>Total</td>
            </tr>
            
            <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
            </tr>
            
            <p><button type="checkout">Checkout Order</button> </p>
    </body>
</html>
