<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contract info</title>
</head>
<body>

    <form:form action="saveContract" modelAttribute="contract">

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
                <td><form:label path="contractPlace.id">Place (id) under contract</form:label></td>
                <td><form:input path="contractPlace.id"/></td>
            </tr>

            <tr>
                <td><form:label path="renter.id">Renter (id) under contract</form:label></td>
                <td><form:input path="renter.id"/></td>
            </tr>

        </table>

        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/contracts'"/>

    </form:form>

</body>
</html>
