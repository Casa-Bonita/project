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
        Payment amount <form:input path="paymentAmount"/>
        <br><br>
        Payment date <form:input path="paymentDate"/>
        <br><br>
        Payment purpose <form:input path="paymentPurpose"/>
        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/payments'"/>

    </form:form>

</body>
</html>
