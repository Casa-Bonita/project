<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All meters</title>
</head>
<body>

<h2>All Meters</h2>

<c:set var="count" scope="session" value="${0}"/>

    <table>
        <tr>
            <th>Number</th>
            <th>Meter number</th>
            <th>Linked place</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="me" items="${metersList}">

            <c:url var="updateButton" value="/updateMeter">
                <c:param name="metId" value="${me.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteMeter">
                <c:param name="metId" value="${me.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${me.number}</td>
                <td>${me.meterPlace.name}</td>
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

    <input type="button" value="Add new Meter"
           onClick="window.location.href='addNewMeter'"/>

    <input type="button" value="Cancel"
           onClick="window.location.href='/'"/>

</body>
</html>
