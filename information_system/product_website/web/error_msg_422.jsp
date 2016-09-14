<%-- 
    Document   : error_msg_422
    Created on : 21/09/2015, 5:47:25 PM
    Author     : milba845
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>422</title>
    </head>
    <body>
        <h1>422 ERROR!</h1>
        <p><%=request.getAttribute("javax.servlet.error.message")%></p>
    </body>
</html>
