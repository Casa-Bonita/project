<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Renter info</title>
</head>
<body>

    <form:form action="saveRenter" method="POST" modelAttribute="renter">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="name">Renter name</form:label></td>
                <td><form:input path="name"/>
                <form:errors path="name"/></td>
            </tr>

            <tr>
                <td><form:label path="ogrn">Renter OGRN</form:label></td>
                <td><form:input path="ogrn"/>
                <form:errors path="ogrn"/></td>
            </tr>

            <tr>
                <td><form:label path="inn">Renter INN</form:label></td>
                <td><form:input path="inn"/>
                <form:errors path="inn"/></td>
            </tr>

            <tr>
                <td><form:label path="registrDate">Registration date</form:label></td>
                <td><form:input path="registrDate"/>
                <form:errors path="registrDate"/></td>
            </tr>

            <tr>
                <td><form:label path="address">Renter address</form:label></td>
                <td><form:input path="address"/></td>
            </tr>

            <tr>
                <td><form:label path="directorName">Director name</form:label></td>
                <td><form:input path="directorName"/></td>
            </tr>

            <tr>
                <td><form:label path="contactName">Contact name</form:label></td>
                <td><form:input path="contactName"/></td>
            </tr>

            <tr>
                <td><form:label path="phoneNumber">Phone number</form:label></td>
                <td><form:input path="phoneNumber"/>
                <form:errors path="phoneNumber"/></td>
            </tr>

        </table>

        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onclick="window.location.href='/renters'"/>

    </form:form>

</body>
</html>
