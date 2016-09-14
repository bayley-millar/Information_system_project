<%-- 
    Document   : log_in
    Created on : 1/09/2015, 5:14:04 PM
    Author     : milba845
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>log in Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_menu.jspf" %>
        <h1>Log In</h1>
        <p>Please log in to continue</p>
        <p>login details</p>
        <form action="LogInServlet">
            <label for="txtUser">User Name:</label>
                <input id="txtUser" name="userName" type="text">
            
            <label for="txtPassword">Password:</label>
                <input id="txtPassword" name="password" type="text">
            
            <input type="submit" value="Submit">
        </form>
    <a href="create_account.jsp">Create Account</a>
    </body>
</html>
