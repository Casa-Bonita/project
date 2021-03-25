<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Renter info update</title>
</head>
<body>

    <form:form action="saveRenter" modelAttribute="renter">

        <form:hidden path="id" value="id"/>

        <table>
            <tr>
                <td><form:label path="name">Renter name</form:label></td>
                <td><form:input path="name"/>
                        <%--        <form:errors path="renterName"/>--%>
                </td>
            </tr>

            <tr>
                <td><form:label path="ogrn">Renter OGRN</form:label></td>
                <td><form:input path="ogrn"/>
                        <%--        <form:errors path="renterOGRN"/>--%>
                </td>
            </tr>

            <tr>
                <td><form:label path="inn">Renter INN</form:label></td>
                <td><form:input path="inn"/>
                        <%--        <form:errors path="renterINN"/>--%>
                </td>
            </tr>

            <tr>
                <td><form:label path="registrDate">Registration date</form:label></td>
                <td><form:input path="registrDate"/>
                        <%--        <form:errors path="renterRegistrDate"/>--%>
                </td>
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
                        <%--        <form:errors path="renterPhone"/>--%>
                </td>
            </tr>

        </table>

        <form:hidden path="renterContract" value="renterContract"/>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onclick="window.location.href='/renters'"/>

    </form:form>

</body>
</html>