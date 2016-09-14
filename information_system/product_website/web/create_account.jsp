<%-- 
    Document   : create_account
    Created on : 31/08/2015, 2:34:27 PM
    Author     : milba845
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>create account</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_menu.jspf" %>
        <h1>Create Account</h1>
        <form action="Create_acc_servlet" method="POST">
            <p><label for="txtUser">User Name:</label>
            <input id="txtUser" name="userName" type="text">
            </p>

            <p><label for="txtName">Name:</label>
            <input id="txtName" name="name" type="text">  
            </p>
            
            <p><label for="txtEmail">Email:</label>
            <input id="txtEmail" name="email" type="text">
            </p>
            
            <p><label for="txtAddress">Address:</label>
            <input id="txtAddress" name="address" type="text">
            </p>
            
            <p><label for="txtCreditCard">Credit Card</label>
            <input id="txtCreditCard" name="creditCard" type="text">
            </p>
            
            <p><label for="txtPassword">Password:</label>
            <input id="txtPassword" name="password" type="text">
            </p>

            <p><button type="submit">Submit</button> </p>
        </form>
    </body>
</html>
