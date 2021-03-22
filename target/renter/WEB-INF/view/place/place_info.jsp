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

        Place number <form:input path="placeNumber"/>
        <br><br>
        Place name <form:input path="placeName"/>
        <br><br>
        Place square <form:input path="placeSquare"/>
        <br><br>
        Place floor <form:input path="placeFloor"/>
        <br><br>
        Place type <form:input path="placeType"/>
        <br><br>

        <input type="submit" value="OK">

        <input type="button" value="Cancel"
               onClick="window.location.href='/places'"/>

    </form:form>

</body>
</html>
