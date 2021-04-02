<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All readings</title>
</head>
<body>

<h2>All Readings</h2>

    <c:set var="count" scope="session" value="${0}"/>

    <table>
        <tr>
            <th>Number</th>
            <th>Meter number</th>
            <th>Transfer data</th>
            <th>Date of transfer</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="re" items="${readingsList}">

            <c:url var="updateButton" value="/updateReading">
                <c:param name="readId" value="${re.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteReading">
                <c:param name="readId" value="${re.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${re.meter.number}</td>
                <td>${re.transferData}</td>
                <td>${re.transferDate}</td>
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

    <input type="button" value="Add new Reading"
           onClick="window.location.href='addNewReading'"/>

    <input type="button" value="Cancel"
           onClick="window.location.href='/'"/>

</body>
</html>
