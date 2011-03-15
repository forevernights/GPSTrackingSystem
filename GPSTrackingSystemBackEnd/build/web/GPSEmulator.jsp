<%-- 
    Document   : GPSEmulator
    Created on : Jun 4, 2010, 5:44:57 AM
    Author     : DarkMoon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="locationTrackingJackson" method="POST">
            <input type="text" name="vehicleID" value="1" />
            <input type="text" name="lng" value="13.232323" />
            <input type="text" name="lat" value="203.849456" />
            <input type="submit" value="Send" />
        </form>
    </body>
</html>
