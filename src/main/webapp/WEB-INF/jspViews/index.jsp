<%@page import="org.apache.catalina.Session"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Welcome to Festival Event Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">

</head>
<style>
.error {
	color: red;
	font-size: 13px;
	font-weight: bold;
}
</style>

<body>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	

	<form:form method="post" name="logForm" action="searchVisitor.html" modelAttribute="vLogin"
		onsubmit="return validateForm()">
		<table width="80%" align="center" border="2" bordercolor="#339999">
			<tr>
				<td align="Center">
					<div id="header">
						&nbsp;
						<div align="center">Festival Event Registration System</div>
					</div> <!-- header end --> <br />
					<table>
						<tr>
							<!--content begin -->
							<td colspan="2" align="center">
								<div id="content">
									<h3>Portal Login Page</h3>
								</div>
							</td>
						</tr>
						<tr><div class="error">${isRegistered}</div></tr>
						<tr><div class="error">${message}</div></tr>
						<tr>
							<td><div align="right">User Name</div></td>
							<td><form:input type="text" path="userName" name="userName" /></td>
							<td><div align="left"><form:errors path="userName" cssClass="error" /></div></td>
						</tr>
						<tr>
							<td><div align="right">Password</div></td>
							<td><form:input type="password" path="passWord" name="passWord"/></td>
							<td><div align="left"><form:errors path="passWord" cssClass="error" /></div></td>
							
						</tr>
						<tr>
							<td colspan="2" align="center"><input type='submit'
								value=" Login "></input> <br /></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td>
								<div id="content">New Visitor:</div>
								<div id="content">
									<a href="registration.html">Register here</a>
								</div>
							</td>
						</tr>
					</table> <br />
				</td>
			</tr>
		</table>

	</form:form>

</body>

</html>
