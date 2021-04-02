<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All accounts</title>
</head>
<body>

<h2>All Accounts</h2>

<c:set var="count" scope="session" value="${0}"/>

    <table>
        <tr>
            <th>Number</th>
            <th>Account number</th>
            <th>Linked contract</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="ac" items="${accountsList}">

            <c:url var="updateButton" value="/updateAccount">
                <c:param name="accId" value="${ac.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteAccount">
                <c:param name="accId" value="${ac.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${ac.number}</td>
                <td>${ac.accountContract.number}</td>
                <td>
                    <input type="button" value="Update"
                           onClick="window.location.href='${updateButton}'"/>

                    <input type="button" value="Delete"
                           onClick="window.location.href='${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br><br><br>

    <input type="button" value="Add new Account"
           onClick="window.location.href='addNewAccount'"/>

    <input type="button" value="Cancel"
           onClick="window.location.href='/'"/>

</body>
</html>
