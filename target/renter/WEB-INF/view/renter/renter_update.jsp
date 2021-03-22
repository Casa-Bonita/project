<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Renter info update</title>
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
        Registration date <form:input path="renterRegistrDate"/>
        <br><br>
        Renter address <form:input path="renterAddress"/>
        <br><br>
        Director name <form:input path="renterDirector"/>
        <br><br>
        Contact name <form:input path="renterContactName"/>
        <br><br>
        Phone number<form:input path="renterPhone"/>
        <br><br>

        <form:hidden path="renterContract" value="${renterContract}" />
        <c:param name="renterContract" value="${renterContract}"/>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onclick="window.location.href='/renters'"/>

    </form:form>

</body>
</html>