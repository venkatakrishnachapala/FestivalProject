<%@ include file="/include.jsp"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstlcore"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Welcome to Festival Event Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="<jstlcore:url value="/resources/css/style.css" />" rel="stylesheet">

<style>
.error {
	color: red;
	font-size: 13px;
	font-weight: bold;
}
</style>
</head>

<body>

	<table width="80%" align="center" border="2">
		<tr>
			<td>
				<div id="header">
					&nbsp;
					<div align="center">Festival Registration System</div>
				</div>

				<table>
					<tr>
						<td width="100%">
							<table align="right" cellpadding="2">
								<tr>
									<td width="90">
										<div id="menu" align="center">
											<a href="<jstlcore:url value="/logOut.html"/>"> Logout </a>
										</div>
									</td>
									<td width="160">
										<div id="menu" align="center">
											<a
												href="<jstlcore:url value="changePWD.html?uName=${visitor.userName}"/>">
												Change_Password </a>
										</div>
									</td>
									<td width="160">
										<div id="menu" align="center">
											<a href="<jstlcore:url value="catalog.html"/>">
												Event_Catalog </a>
										</div>
									</td>
									<td width="90">
										<div id="menu" align="center">
											<a href="<jstlcore:url value="about.html"/>"> About</a><br />
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="1000">
							<table cellpadding="5" width="100%">
								<tr>
									<td>
										<div align="center" id="content">
											<h3>
												Festival Portal Page: Welcome <span
													style="background-color: yellow;"><jstlcore:out
														value="${visitor.firstName}"></jstlcore:out></span> to your
												portal page.....
											</h3>
										</div>
								<tr class="error"><div id="m">${message}</div></tr>
								<table cellpadding="4" border="1">
									<tr>
										<td width="1000">
											<div id="coursesbody" align="left">
												<p>
													<strong>Your personal information</strong> is below. To
													change your information,<a
														href="<jstlcore:url value="updateDetail.html?vName=${visitor.userName}"/>">click
														here </a>

												</p>
												
												<p class="style1">
													Name :
													<jstlcore:out value="${visitor.firstName}"></jstlcore:out>&nbsp; <jstlcore:out value="${visitor.lastName}"></jstlcore:out>
												</p>
												<p class="style1">
													Visitor ID:
													<jstlcore:out value="${visitor.id}"></jstlcore:out>
												</p>
												<p class="style1">
													Email:
													<jstlcore:out value="${visitor.email}"></jstlcore:out>
												</p>
												<p class="style1">
													Phone Number:
													<jstlcore:out value="${visitor.phoneNumber}"></jstlcore:out>
												</p>
												<p class="style1">
													Address:
													<jstlcore:out value="${visitor.address}"></jstlcore:out>
												</p>
											</div>
										</td>
									</tr>
								</table>
								<br />
								<div align="center">
									<img src="/resources/images/greenhorizontalline.jsp" height="5"
										width="100%" />
								</div>
								<div id="content" align="center">
									<p class="content">
										<strong>To release a ticket you have for an up-coming
											attraction, please find the attraction below, and click the
											Release link.</strong>
									</p>
									<table width="96%" border="1" align="center">
										<tr bgcolor="#66CC99">
											<th scope="col">Confirmation #</th>
											<th scope="col">Event Id</th>
											<th scope="col">Event name</th>
											<th scope="col">Description</th>
											<th scope="col">Venue</th>
											<th scope="col">Duration</th>
											<th scope="col">Event type</th>
											<th scope="col">Schedule</th>
											<th scope="col">Action</th>
										</tr>
									<tr align="center">${messageEvent}</tr>
										<jstlcore:forEach items="${regEvents}" var="regEvent">
											<tr align="center">
												<td><jstlcore:out
														value="${fn:substring(visitor.lastName,0,4)}${fn:substring(visitor.firstName,0,3)}-${regEvent.eventId}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${regEvent.eventId}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${regEvent.eventName}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${regEvent.description}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${regEvent.place}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${regEvent.duration}"></jstlcore:out></td>
												<td align="center"><jstlcore:out
														value="${regEvent.eventType}"></jstlcore:out></td>
														<td align="center"><jstlcore:out
														value="${regEvent.schedule}"></jstlcore:out></td>
												<td width="100px" bgcolor="#CCCC99"><a
													href="<jstlcore:url value="eventunreg.html?eventId=${regEvent.eventId}&visitorId=${visitor.id}"/>" onclick="if(!(confirm('Are you sure you want to cancel the tickets?'))) return false">Cancel Tickets</a>

												</td>
											</tr>
										</jstlcore:forEach>
										<tr>
											<td colspan="9" align="center"
												style="font-weight: bold; color: red;">${RegError}</td>
										</tr>
									</table>

								</div>
								</td>
								</tr>
							</table>
						</td>

					</tr>

				</table>




				<div align="center">
					<img src="images/greenhorizontalline.jpg" height="5" width="100%" />
				</div> <br />
				<div id="content" align="center">
					<p class="content">
						<strong>To get a ticket for an up-coming attraction,
							please find the attraction below, and click the Register link.</strong>
					</p>
					<table class="content" width="96%" border="1" align="center">
						<tr bgcolor="#669966">
							<th scope="col">Event Id</th>
							<th scope="col">Event name</th>
							<th scope="col">Description</th>
							<th scope="col">Venue</th>
							<th scope="col">Duration</th>
							<th scope="col">Event type</th>
							<th scope="col">Schedule</th>
							<th scope="col">Ticket Price</th>
							<th scope="col">Available Seats</th>
						</tr>
						<jstlcore:forEach items="${allEvents}" var="event">
							<tr>
								<td><jstlcore:out value="${event.eventId}"></jstlcore:out></td>
							<td><jstlcore:out value="${event.eventName}"></jstlcore:out></td>
							<td><jstlcore:out value="${event.description}"></jstlcore:out></td>
							<td><jstlcore:out value="${event.place}"></jstlcore:out></td>
							<td align="center"><jstlcore:out value="${event.duration}"></jstlcore:out></td>
							<td><jstlcore:out value="${event.eventType}"></jstlcore:out></td>
							<td><jstlcore:out value="${event.schedule}"></jstlcore:out></td>
							<td><jstlcore:out value="${event.ticketPrice}"></jstlcore:out></td>
								<jstlcore:choose>
									<jstlcore:when test="${event.seatsAvailable != 0}">
										<td width="100px" bgcolor="#CCCC99" align="center"><jstlcore:out
												value="${event.seatsAvailable}"></jstlcore:out><br> <strong>
												seats left.</strong> <a
											href="<jstlcore:url value="/visitor/eventreg.html?eventId=${event.eventId}&visitorId=${visitor.id}"/>">Register</a>
										</td>
									</jstlcore:when>
									<jstlcore:otherwise>
										<td width="100px" bgcolor="#CCCC99" align="center"><strong>No
												seats left</strong></td>
									</jstlcore:otherwise>
								</jstlcore:choose>
							</tr>
						</jstlcore:forEach>


					</table>

				</div>
			</td>
		</tr>
	</table>


</body>

</html>
