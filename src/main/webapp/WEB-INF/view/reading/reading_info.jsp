<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reading info</title>
</head>
<body>

    <form:form action="saveReading" modelAttribute="meterData">

        <form:hidden path="id"/>

        Electricmeter number <form:input path="readingElectricmeterNumber"/>
        <br><br>
        Transfer date <form:input path="readingTransferDate"/>
        <br><br>
        Transfered data <form:input path="readingTransferedData"/>
        <br><br>

        <input type="button" value="Cancel"
               onClick="window.location.href='/readings'"/>

        <input type="submit" value="OK">

    </form:form>

</body>
</html>
