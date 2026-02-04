<%-- 
    Document   : registration
    Created on : 28.01.2011, 18:03:24
    Author     : RSDENIS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="WEB-INF/jspf/denisHeader.jspf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTRATION PAGE</title>
    </head>
    <body>
        <h1>Registration</h1>
         <form method="GET" action="RegistrationServlet">
          login: <input type="text" name="user_login"><br>
          password: <input type="password" name="user_password"><br><br>
          <input type="submit" value="OK">
      </form>
    </body>
</html>
