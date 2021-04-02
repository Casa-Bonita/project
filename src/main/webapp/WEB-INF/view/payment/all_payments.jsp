<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All payments</title>
</head>
<body>

<h2>All Payments</h2>

<c:set var="count" scope="session" value="${0}"/>

    <table>
        <tr>
            <th>Number</th>
            <th>Payment account</th>
            <th>Payment amount</th>
            <th>Payment date</th>
            <th>Payment purpose</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="pm" items="${paymentsList}">
            <c:url var="updateButton" value="/updatePayment">
                <c:param name="paymId" value="${pm.id}"/>
            </c:url>
            <c:url var="deleteButton" value="/deletePayment">
                <c:param name="paymId" value="${pm.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${pm.account.number}</td>
                <td>${pm.amount}</td>
                <td>${pm.date}</td>
                <td>${pm.purpose}</td>
                <td>
                    <input type="button" value="Update"
                           onClick="window.location.href='${updateButton}'"/>
                    <input type="button" value="Delete"
                          onClick="window.location.href='${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br><br><br>

    <input type="button" value="Add new Payment"
           onClick="window.location.href='addNewPayment'"/>

    <input type="button" value="Cancel"
           onClick="window.location.href='/'"/>


</body>
</html>
