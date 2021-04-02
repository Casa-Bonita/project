<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start page</title>
</head>
<body>

<h2>Start page</h2>

    <c:set var="count" scope="session" value="${0}" />

    <table>
        <tr>
            <th>Number</th>
            <th>Place</th>
            <th>Renter</th>
            <th>Contract</th>
            <th>Finish date</th>
            <th>Meter</th>
            <th>Last meter data</th>
            <th>Account number</th>
            <th>Total payments under the Contract</th>
        </tr>

        <c:forEach var="sl" items="${summaryList}">

            <tr>
                <td>
                    <c:set var="count" scope="session" value="${count + 1}" />
                    <c:out value="${count}" />
                </td>
                <td>${sl.placeName}</td>
                <td>${sl.renterName}</td>
                <td>${sl.contractNumber}</td>
                <td>${sl.contractFinishDate}</td>
                <td>${sl.meterNumber}</td>
                <td>${sl.lastMeterData}</td>
                <td>${sl.accountNumber}</td>
                <td>${sl.totalPayments}</td>
            </tr>
        </c:forEach>
    </table>

<br><br><br>

<input type="button" value="View all Renters"
       onclick="window.location.href='/renters'"/>

<input type="button" value="View all Contracts"
       onclick="window.location.href='/contracts'"/>

<input type="button" value="View all Places"
       onclick="window.location.href='/places'"/>

<br><br><br>

<input type="button" value="Accounts"
       onClick="window.location.href='/accounts'"/>

<input type="button" value="Meters"
       onClick="window.location.href='/meters'"/>

<br><br><br>

<input type="button" value="Payment history"
       onClick="window.location.href='/payments'"/>

<input type="button" value="Reading history"
       onClick="window.location.href='/readings'"/>

</body>
</html>
