<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account info</title>
</head>
<body>

    <form:form action="saveAccount" method="POST" modelAttribute="account">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="number">Account number</form:label></td>
                <td><form:input path="number"/></td>
            </tr>

            <tr>
                <td><form:label path="accountContract.number">Linked contract</form:label></td>
                <td><form:select path="accountContract.number" items="${contractMap}"/></td>
            </tr>

        </table>

        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/accounts'"/>

    </form:form>

</body>
</html>
