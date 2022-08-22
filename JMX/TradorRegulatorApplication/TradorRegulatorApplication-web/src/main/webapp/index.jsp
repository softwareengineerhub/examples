<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <p>
        <form action="Admin">
            <input type="hidden" name="operation" value="resume"/>
            <input type="submit" value="resume"/>
        </form>
    </p>
    <p>
         <form action="Admin">
            <input type="hidden" name="operation" value="suspend"/>
            <input type="submit" value="suspend"/>
        </form>
    </p>
    </body>
</html>
