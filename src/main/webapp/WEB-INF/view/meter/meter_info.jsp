<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meter info</title>
</head>
<body>

    <form:form action="saveMeter" modelAttribute="meter">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="number">Meter number</form:label></td>
                <td><form:input path="number"/></td>
            </tr>

            <tr>
                <td><form:label path="meterPlace.name">Linked place name</form:label></td>
                <td><form:input path="meterPlace.name"/></td>
            </tr>

        </table>

        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/meters'"/>

    </form:form>

</body>
</html>
