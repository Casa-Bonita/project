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
        <th>Electricmeter number</th>
        <th>Transfer date</th>
        <th>Transfered data</th>
    </tr>

    <c:forEach var="rdng" items="${allMeterDatas}">

        <c:url var="updateButton" value="/updateReading">
            <c:param name="readId" value="${rdng.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteReading">
            <c:param name="readId" value="${rdng.id}"/>
        </c:url>

        <tr>
            <td>
                <c:set var="count" scope="session" value="${count + 1}"/>
                <c:out value="${count}"/>
            </td>
            <td>${rdng.readingElectricmeterNumber}</td>
            <td>${rdng.readingTransferDate}</td>
            <td>${rdng.readingTransferedData}</td>
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

<input type="button" value="Add new meterData"
       onClick="window.location.href='addNewReading'"/>

<input type="button" value="Back"
       onClick="window.location.href='/'"/>

</body>
</html>
