<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
	<h2 style="color: red;">Event Creation</h2>
	<cite>for admin use only!</cite>
	
	<br>
	<br>

		<form:form modelAttribute="eventAttribute" action="eventAdd.html" method="POST">
			<table>
				 			<form:hidden path="eventId"/>

				<tr>
					<td>Event Name</td>
					<td><form:input path="eventName"/></td>
				</tr>

				<tr>
					<td>Description</td>
					<td><form:input path="description" /></td>
				</tr>
				<tr>
					<td>Duration</td>
					<td><form:input path="duration" /></td>
				</tr>
				<tr>
					<td>Event Type</td>
					<td><form:input path="eventType" /></td>
				</tr>
				<tr>
					<td>Schedule</td>
					<td><form:input path="schedule"  placeholder="yyyy/MM/dd" /></td>
				</tr>
				<tr>
					<td>Ticket Price</td>
					<td><form:input path="ticketPrice" /></td>
				</tr>
				<tr>
					<td>Venue</td>
					<td><form:input path="place" /></td>
				</tr>
				<tr>
					<td>Maximum Seat</td>
					<td><form:input path="maxSeat" /></td>
				</tr>
				<tr>
					<td>Seat Available</td>
					<td><form:input path="seatsAvailable"/> </td>
				</tr>
				<tr><td colspan="2" align="right"><input type="submit" value="Create"/> </td></tr>
			</table>

		</form:form>
	</center>
</body>
</html>