<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reading info</title>
</head>
<body>

    <form:form action="saveReading" modelAttribute="reading">

        <form:hidden path="id"/>

        Transfered data <form:input path="meterDataData"/>
        <br><br>
        Date of transfer <form:input path="meterDataDate"/>
        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/readings'"/>

    </form:form>

</body>
</html>
