<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All places</title>
</head>
<body>

<h2>All Places</h2>

<c:set var="count" scope="session" value="${0}"/>

    <table>
        <tr>
            <th>Number</th>
            <th>Place number</th>
            <th>Place square</th>
            <th>Floor</th>
            <th>Type</th>
            <th>Electricmeter_reading</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="plc" items="${allPlaces}">

            <c:url var="updateButton" value="/updatePlace">
                <c:param name="plId" value="${plc.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deletePlace">
                <c:param name="plId" value="${plc.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${plc.placeNumber}</td>
                <td>${plc.placeSquare}</td>
                <td>${plc.placeFloor}</td>
                <td>${plc.placeType}</td>
                <td>
                    ${plc.placeReading}
<%--                    <c:forEach var="read" items="${allMeterDatas}">--%>
<%--                        <c:if test="${plc.placeReading==read.id}">--%>
<%--                            ${read.readingTransferedData}--%>
<%--&lt;%&ndash;                            <c:out value="${read.readingTransferedData}"/>&ndash;%&gt;--%>
<%--                        </c:if>--%>
<%--                    </c:forEach>--%>
                </td>
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

    <input type="button" value="Add new place"
           onClick="window.location.href='addNewPlace'"/>

<input type="button" value="Back"
           onClick="window.location.href='/'"/>

</body>
</html>
