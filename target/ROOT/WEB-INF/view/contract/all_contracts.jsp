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
            <th>Fare</th>
            <th>Start date</th>
            <th>Finish date</th>
            <th>Payment day</th>
            <th>Place</th>
            <th>Renter</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="ct" items="${contractsList}">

            <c:url var="updateButton" value="/updateContract">
                <c:param name="contrId" value="${ct.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteContract">
                <c:param name="contrId" value="${ct.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${ct.number}</td>
                <td>${ct.date}</td>
                <td>${ct.fare}</td>
                <td>${ct.startDate}</td>
                <td>${ct.finishDate}</td>
                <td>${ct.paymentDay}</td>
                <td>${ct.contractPlace.name}</td>
                <td>${ct.renter.name}</td>
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

    <input type="button" value="Add new Contract"
           onClick="window.location.href='addNewContract'"/>

    <input type="button" value="Cancel"
           onClick="window.location.href='/'"/>



</body>
</html>
