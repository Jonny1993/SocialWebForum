<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Log - Web Forum</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	<div class="container">
		<h2><c:out value="${theLog.title}"/></h2>
		<p><c:out value="Created on ${theLog.datePosted}" /></p>
		<div>
			<table border="1">
				<tr>
					<th>Content:</th>
					<td>${theLog.content}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
<style>
.container {
	margin: auto;
	text-align: left;
}

.inline{
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
</style>
</html>