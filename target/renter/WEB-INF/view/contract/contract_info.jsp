<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contract info</title>
</head>
<body>

    <form:form action="saveContract" method="POST" modelAttribute="contract">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="number">Contract number</form:label></td>
                <td><form:input path="number"/></td>
            </tr>

            <tr>
                <td><form:label path="date">Contract date</form:label></td>
                <td><form:input path="date"/></td>
            </tr>

            <tr>
                <td><form:label path="fare">Fare</form:label></td>
                <td><form:input path="fare"/></td>
            </tr>

            <tr>
                <td><form:label path="startDate">Contract start date</form:label></td>
                <td><form:input path="startDate"/></td>
            </tr>

            <tr>
                <td><form:label path="finishDate">Contract finish date</form:label></td>
                <td><form:input path="finishDate"/></td>
            </tr>

            <tr>
                <td><form:label path="paymentDay">Contract payment day</form:label></td>
                <td><form:input path="paymentDay"/></td>
            </tr>

            <tr>
                <td><form:label path="contractPlace.number">Place number WITHOUT the contract</form:label></td>
                <td><form:select path="contractPlace.number" items="${placeMap}"/></td>
            </tr>

            <tr>
                <td><form:label path="renter.name">Renter name under contract</form:label></td>
                <td><form:select path="renter.name" items="${renterMap}"/></td>
            </tr>

        </table>

        <br><br>

        <c:choose>
            <c:when test="${placeMap.size() eq 0}">

                <p> You can't add a new Contract because there are no free Places </p>
                <br><br>
                <input type="button" value="Cancel"
                       onClick="window.location.href='/contracts'"/>
            </c:when>

            <c:when test="${renterMap.size() eq 0}">

                <p> You can't add a new Contract because there are no Renters </p>
                <br><br>
                <input type="button" value="Cancel"
                       onClick="window.location.href='/contracts'"/>
            </c:when>

            <c:otherwise>
                <input type="submit" value="OK">

                <input type="button" value="Cancel"
                       onClick="window.location.href='/contracts'"/>
            </c:otherwise>
        </c:choose>

    </form:form>

</body>
</html>
