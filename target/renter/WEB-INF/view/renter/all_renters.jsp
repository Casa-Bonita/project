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
            <th>Director Name</th>
            <th>Contact Name</th>
            <th>Phone Number</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="rt" items="${rentersList}">

            <c:url var="updateButton" value="/updateRenter">
                <c:param name="rentId" value="${rt.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteRenter">
                <c:param name="rentId" value="${rt.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}" />
                    <c:out value="${count}" />
                </td>
                <td>${rt.name}</td>
                <td>${rt.ogrn}</td>
                <td>${rt.inn}</td>
                <td>${rt.registrDate}</td>
                <td>${rt.address}</td>
                <td>${rt.directorName}</td>
                <td>${rt.contactName}</td>
                <td>${rt.phoneNumber}</td>
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

    <input type="button" value="Add new Renter"
           onclick="window.location.href = 'addNewRenter'"/>

    <input type="button" value="Cancel"
           onclick="window.location.href='/'"/>

</body>
</html>
