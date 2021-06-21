<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meter info</title>
</head>
<body>

    <form:form action="saveMeter" method="POST" modelAttribute="meter">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="number">Meter number</form:label></td>
                <td><form:input path="number"/></td>
            </tr>

            <tr>
                <td><form:label path="meterPlace.number">Place number WITHOUT the meter</form:label></td>
                <td><form:select path="meterPlace.number" items="${placeMap}"/></td>
            </tr>

        </table>

        <br><br>

        <c:if test="${placeMap.size() eq 0}">

            <p> You can't add a new Meter because there are no free Places </p>
            <br><br>
            <input type="button" value="Cancel"
                   onClick="window.location.href='/meters'"/>
        </c:if>

        <c:if test="${placeMap.size() ne 0}">
            <input type="submit" value="OK">

            <input type="button" value="Cancel"
                   onClick="window.location.href='/meters'"/>
        </c:if>

    </form:form>

</body>
</html>
