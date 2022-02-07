<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Logs - Web Forum</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<h2>My Logs</h2>
		<p id="name"></p>
		<div>

			<table border="1" class="center">
				<tr>
					<th>Title</th>
					<th>Date Posted</th>
				</tr>
				<c:forEach items="${logsList}" var="log" varStatus="count">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/view_mylog?nr=${count.index}">${log.title}</a></td>
						<td>${log.datePosted}</td>
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