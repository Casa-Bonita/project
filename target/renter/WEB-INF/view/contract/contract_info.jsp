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
        Renter <form:input path="contractRenter"/>
        <br><br>
        Status <form:input path="contractStatus"/>
        <br><br>
        Place number <form:input path="contractPlaceNumber"/>
        <br><br>
        Fare <form:input path="contractFare"/>
        <br><br>
        Contact start date <form:input path="contractStart"/>
        <br><br>
        Contact finish date <form:input path="contractEnd"/>
        <br><br>
        Contact payment day <form:input path="contractPaymentDay"/>
        <br><br>

        <input type="button" value="Cancel"
               onClick="window.location.href='/contracts'"/>

        <input type="submit" value="OK">

    </form:form>

</body>
</html>
