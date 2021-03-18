<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Renter info</title>
</head>
<body>

    <form:form action="saveRenter" modelAttribute="renter">

        <form:hidden path="id"/>

        Renter name <form:input path="renterName"/>
        <br><br>
        Renter OGRN <form:input path="renterOGRN"/>
        <br><br>
        Renter INN <form:input path="renterINN"/>
        <br><br>
        Registration Date <form:input path="renterRegistrDate"/>
        <br><br>
        Renter address <form:input path="renterAddress"/>
        <br><br>
        Director <form:input path="renterDirector"/>
        <br><br>
        Contact Name <form:input path="renterContactName"/>
        <br><br>
        Phone <form:input path="renterPhone"/>
        <br><br>
        Contract Id <form:input path="renterContract"/>
        <br><br>

        <input type="button" value="Cancel"
               onclick="window.location.href='/renters'"/>

        <input type="submit" value="OK">

    </form:form>

</body>
</html>
