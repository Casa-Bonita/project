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
            <th>Contract number</th>
            <th>Payment date</th>
            <th>Payment amount</th>
            <th>Payer</th>
            <th>Payment purpose</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="pmnt" items="${allPayments}">
            <c:url var="updateButton" value="/updatePayment">
                <c:param name="paymId" value="${pmnt.id}"/>
            </c:url>
            <c:url var="deleteButton" value="/deletePayment">
                <c:param name="paymId" value="${pmnt.id}"/>
            </c:url>

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}"/>
                    <c:out value="${count}"/>
                </td>
                <td>${pmnt.contractNumber}</td>
                <td>${pmnt.paymentDate}</td>
                <td>${pmnt.paymentAmount}</td>
                <td>${pmnt.payer}</td>
                <td>${pmnt.paymentPurpose}</td>
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

    <input type="button" value="Add new payment"
           onClick="window.location.href='addNewPayment'"/>

    <input type="button" value="Back"
           onClick="window.location.href='/'"/>


</body>
</html>
