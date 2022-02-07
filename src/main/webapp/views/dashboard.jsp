<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard - Web Forum</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<h2>
			Welcome <c:out value="${currentUser.name} " />
		</h2>
		<p>Users' List</p>
		<div>
			<table border="1" class="center">
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Send Message</th>
				</tr>
				<c:forEach items="${users_list}" var="user" varStatus="count">
					<tr>
					<c:set var="name" value="${user.name}"/>
						<td><a href="${pageContext.request.contextPath}/view_user?nr=${count.index}" onclick="sessionStorage.setItem('name', '${user.name}');">${user.name}</a></td>
						<td>${user.email}</td>
						<td><input type="button" value="Message" 
						onclick="location.href='send_msg'; sessionStorage.setItem('senderIndex', '${count.index}');"></td>
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