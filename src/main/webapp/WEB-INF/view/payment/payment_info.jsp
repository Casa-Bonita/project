<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment info</title>
</head>
<body>

    <form:form action="savePayment" modelAttribute="payment">

        <form:hidden path="id"/>
        Contract number <form:input path="contractNumber"/>
        <br><br>
        Payment date <form:input path="paymentDate"/>
        <br><br>
        Payment amount <form:input path="paymentAmount"/>
        <br><br>
        Payer <form:input path="payer"/>
        <br><br>
        Payment purpose <form:input path="paymentPurpose"/>
        <br><br>

        <input type="button" value="Cancel"
               onClick="window.location.href='/payments'"/>

        <input type="submit" value="OK"/>

    </form:form>

</body>
</html>
