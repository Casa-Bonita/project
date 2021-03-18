<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All renters</title>
</head>
<body>

<h2>All Renters</h2>

<c:set var="count" scope="session" value="${0}" />

    <table>
        <tr>
            <th>Number</th>
            <th>Name</th>
            <th>OGRN</th>
            <th>INN</th>
            <th>Registration Date</th>
            <th>Address</th>
            <th>Director</th>
            <th>Contact Name</th>
            <th>Phone</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="rntr" items="${allRenters}">

            <c:url var="updateButton" value="/updateRenter">
                <c:param name="rentId" value="${rntr.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteRenter">
                <c:param name="rentId" value="${rntr.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}" />
                    <c:out value="${count}" />
                </td>
                <td>${rntr.renterName}</td>
                <td>${rntr.renterOGRN}</td>
                <td>${rntr.renterINN}</td>
                <td>${rntr.renterRegistrDate}</td>
                <td>${rntr.renterAddress}</td>
                <td>${rntr.renterDirector}</td>
                <td>${rntr.renterContactName}</td>
                <td>${rntr.renterPhone}</td>
                <td>
                    <input type="button" value="Update"
                           onClick="window.location.href= '${updateButton}'"/>

                    <input type="button" value="Delete"
                           onClick="window.location.href= '${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br><br><br>

    <input type="button" value="Add new renter"
           onclick="window.location.href = 'addNewRenter'"/>

    <input type="button" value="Back"
           onclick="window.location.href='/'"/>

</body>
</html>
