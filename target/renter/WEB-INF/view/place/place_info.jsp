<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place info</title>
</head>
<body>

    <form:form action="savePlace" modelAttribute="place">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="number">Place number</form:label></td>
                <td><form:input path="number"/></td>
            </tr>

            <tr>
                <td><form:label path="name">Place name</form:label></td>
                <td><form:input path="name"/></td>
            </tr>

            <tr>
                <td><form:label path="square">Place square</form:label></td>
                <td><form:input path="square"/></td>
            </tr>

            <tr>
                <td><form:label path="floor">Place floor</form:label></td>
                <td><form:input path="floor"/></td>
            </tr>

            <tr>
                <td><form:label path="type">Place type</form:label></td>
                <td><form:input path="type"/></td>
            </tr>

        </table>

        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/places'"/>

    </form:form>

</body>
</html>
