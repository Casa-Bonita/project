<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Renter info</title>
</head>
<body>

    <form:form action="saveRenter" modelAttribute="renter">

        Renter name <form:input path="renterName"/>
        <form:errors path="renterName"/>
        <br><br>
        Renter OGRN <form:input path="renterOGRN"/>
        <form:errors path="renterOGRN"/>
        <br><br>
        Renter INN <form:input path="renterINN"/>
        <form:errors path="renterINN"/>
        <br><br>
        Registration date <form:input path="renterRegistrDate"/>
        <form:errors path="renterRegistrDate"/>
        <br><br>
        Renter address <form:input path="renterAddress"/>
        <br><br>
        Director name <form:input path="renterDirector"/>
        <br><br>
        Contact name <form:input path="renterContactName"/>
        <br><br>
        Phone number<form:input path="renterPhone"/>
        <form:errors path="renterPhone"/>
        <br><br>

        <c:param name="renterContract" value="${null}"/>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onclick="window.location.href='/renters'"/>

    </form:form>

</body>
</html>
