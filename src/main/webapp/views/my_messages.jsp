<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Messages - Web Forum</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<h2>Inbox</h2>
		<c:if test="${not empty messageSent}">
			<c:out value="${messageSent}"/>
			<br>
			<br>
			<c:remove var="messageSent" scope="session" />
		</c:if>
		<div>
			<table border="1" class="center">
				<tr>
					<th>Sent by</th>
					<th>Title</th>
					<th>Date Sent</th>
				</tr>
				<c:forEach items="${message_list}" var="msg" varStatus="position">
					<tr>
						<td>${msg.sender.name}</td>
						<td><a
							href="${pageContext.request.contextPath}/view_message?nr=${position.index}">${msg.title}</a></td>
						<td>${msg.dateReceived}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
<style>
.container {
	margin: auto;
	text-align: center;
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
</style>
</html>