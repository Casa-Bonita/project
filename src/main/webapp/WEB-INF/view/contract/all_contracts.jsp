<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All contracts</title>
</head>
<body>

<h2>All Contracts</h2>

<c:set var="count" scope="session" value="${0}"/>

    <table>
        <tr>
            <th>Number</th>
            <th>Contract number</th>
            <th>Contract date</th>
            <th>Renter</th>
            <th>Status</th>
            <th>Place number</th>
            <th>Fare</th>
            <th>Start date</th>
            <th>Finish date</th>
            <th>Payment day</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="cntr" items="${allContracts}">

            <c:url var="updateButton" value="/updateContract">
                <c:param name="contrId" value="${cntr.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteContract">
                <c:param name="contrId" value="${cntr.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${cntr.contractNumber}</td>
                <td>${cntr.contractDate}</td>
                <td>${cntr.contractRenter}</td>
                <td>${cntr.contractStatus}</td>
                <td>${cntr.contractPlaceNumber}</td>
                <td>${cntr.contractFare}</td>
                <td>${cntr.contractStart}</td>
                <td>${cntr.contractEnd}</td>
                <td>${cntr.contractPaymentDay}</td>
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

    <input type="button" value="Add new contract"
           onClick="window.location.href='addNewContract'"/>

    <input type="button" value="Back"
           onClick="window.location.href='/'"/>



</body>
</html>
