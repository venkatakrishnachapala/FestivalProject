<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstlcore"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register For Event</title>
<link href="<jstlcore:url value="/resources/css/styles.css"/>"
	rel="stylesheet">
</head>

<body>
	<center>
		<table>
			<br />
			<br />
			<br />

			<br />
			<tr>
				<div id="header">Festival Event Registration System</div>
			</tr>
			<tr align="center">Confirm Booking
			</tr>
			<tr>
				<td>
					<p class="style1">
						Event Name :
						<jstlcore:out value="${event.eventName}"></jstlcore:out>
					</p>
					<p class="style1">
						Venue :
						<jstlcore:out value="${event.place}"></jstlcore:out>
					</p>
					<p class="style1">
						Duration :
						<jstlcore:out value="${event.duration}"></jstlcore:out>
					</p>
					<p class="style1">
						Schedule :
						<jstlcore:out value="${event.schedule}"></jstlcore:out>
					</p>
					
				</td>
			</tr>
			<tr>
				<td>
				<form action="confirmSeats.html" method="post">
					<input type="hidden" name="visitorId" value="${visitorId}"/>
					<input type="hidden" name="eventId" value="${event.eventId}"/>
					No of Seats:&nbsp; <input type="number" name="noOfSeats" size="2"/><br>
					<div colspan="3" align="right"><input type="submit" value="Get Seats"></div><br><br />
				</form>
				
				</td>
			</tr>
		</table>
	</center>
</body>
</html>