<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Log - Web Forum</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<h2>Create Log</h2>
		<div>
			<form:form action="create_log_action" modelAttribute="newLog">
				<table border="1">
					<tr>
						<th>Title:</th>
						<td><form:input path="title" type="text" placeholder="Title" />
							<form:errors path="title" cssClass="error" /></td>
					</tr>
					<tr>
						<th>Content:</th>
						<td><form:textarea path="content" rows="4" cols="50"
								placeholder="Content"/></td>
					</tr>
				</table>
				<br>
				<input type="submit" name="Post" value="Post" />
			</form:form>
		</div>
	</div>
</body>
<style>
.container {
	margin: auto;
	text-align: left;
}

.inline {
	display: inline-block;
	margin-right: 5px;
}

.center {
	margin-left: auto;
	margin-right: auto;
	cellspacing:
}

th, td {
	padding: 15px;
	text-align: left;
}

a.button {
	-webkit-appearance: button;
	-moz-appearance: button;
	appearance: button;
	text-decoration: none;
	color: initial;
}
.error {
	color: red;
	text-align: center;
	display: inline;
}
</style>
</html>