<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment info</title>
</head>
<body>

    <form:form action="savePayment" method="POST" modelAttribute="payment">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="account.number">Account number</form:label></td>
                <td><form:select path="account.number" items="${accountMap}"/></td>
            </tr>

            <tr>
                <td><form:label path="amount">Payment amount</form:label></td>
                <td><form:input path="amount"/></td>
            </tr>

            <tr>
                <td><form:label path="date">Payment date</form:label></td>
                <td><form:input path="date"/></td>
            </tr>

            <tr>
                <td><form:label path="purpose">Payment purpose</form:label></td>
                <td><form:input path="purpose"/></td>
            </tr>

        </table>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/payments'"/>

    </form:form>

</body>
</html>
