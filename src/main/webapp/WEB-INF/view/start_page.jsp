<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start page</title>
</head>
<body>

<h2>Start page</h2>

<br><br><br>

<input type="button" value="View all Renters"
       onclick="window.location.href='/renters'"/>

<input type="button" value="View all Contracts"
       onclick="window.location.href='/contracts'"/>

<input type="button" value="View all Places"
       onclick="window.location.href='/places'"/>

<br><br><br>

<input type="button" value="Payment history"
       onClick="window.location.href='/payments'"/>

<input type="button" value="Reading history"
       onClick="window.location.href='/readings'"/>

</body>
</html>
