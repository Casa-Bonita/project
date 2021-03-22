<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contract info</title>
</head>
<body>

    <form:form action="saveContract" modelAttribute="contract">

        <form:hidden path="id"/>

        Contract number <form:input path="contractNumber"/>
        <br><br>
        Contract date <form:input path="contractDate"/>
        <br><br>
        Fare <form:input path="contractFare"/>
        <br><br>
        Contact start date <form:input path="contractStart"/>
        <br><br>
        Contact finish date <form:input path="contractFinish"/>
        <br><br>
        Contact payment day <form:input path="contractPaymentDay"/>
        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/contracts'"/>

    </form:form>

</body>
</html>
