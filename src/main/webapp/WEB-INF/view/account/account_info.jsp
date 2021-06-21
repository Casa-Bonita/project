<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <td><form:label path="accountContract.number">Contract number WITHOUT the account</form:label></td>
                <td><form:select path="accountContract.number" items="${contractMap}"/></td>
            </tr>

        </table>

        <br><br>

        <c:if test="${contractMap.size() eq 0}">

            <p> You can't add a new Account because there are no free Contracts </p>
            <br><br>
            <input type="button" value="Cancel"
                   onClick="window.location.href='/accounts'"/>
        </c:if>

        <c:if test="${contractMap.size() ne 0}">
            <input type="submit" value="OK">

            <input type="button" value="Cancel"
                   onClick="window.location.href='/accounts'"/>
        </c:if>

    </form:form>

</body>
</html>
