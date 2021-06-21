<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <th valign=middle align=center><a href="/places">Place</a></th>
            <th valign=middle align=center><a href="/renters">Renter</a></th>
            <th valign=middle align=center><a href="/contracts">Contract</a></th>
            <th valign=middle align=center><a href="/meters">Meter</a></th>
            <th valign=middle align=center><a href="/readings">Last meter data</a></th>
            <th valign=middle align=center><a href="/accounts">Account number</a></th>
            <th valign=middle align=center><a href="/payments">Total payments</a></th>
        </tr>

        <c:forEach var="sl" items="${summaryList}">

            <tr>
                <td valign=middle align=center>
                    <c:set var="count" scope="session" value="${count + 1}" />
                    <c:out value="${count}" />
                </td>
                <td valign=middle align=center>${sl.placeName}</td>
                <td valign=middle align=center>${sl.renterName}</td>
                <td valign=middle align=center>${sl.contractNumber}</td>
                <td valign=middle align=center>${sl.meterNumber}</td>
                <td valign=middle align=center>${sl.lastMeterData}</td>
                <td valign=middle align=center>${sl.accountNumber}</td>
                <td valign=middle align=center>${sl.totalPayments}</td>
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
