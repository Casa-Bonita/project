<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reading info</title>
</head>
<body>

    <form:form action="saveReading" modelAttribute="reading">

        <form:hidden path="id"/>

        <table>
            <tr>
                <td><form:label path="meter">Meter number</form:label></td>
                <td><form:input path="meter"/></td>
            </tr>

            <tr>
                <td><form:label path="transferData">Transfered data</form:label></td>
                <td><form:input path="transferData"/></td>
            </tr>

            <tr>
                <td><form:label path="transferDate">Date of transfer</form:label></td>
                <td><form:input path="transferDate"/></td>
            </tr>

        </table>

        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/readings'"/>

    </form:form>

</body>
</html>
