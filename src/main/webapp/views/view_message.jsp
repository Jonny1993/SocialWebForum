<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function replyOnClick() {
		sessionStorage.setItem('senderIndex', ${usersList.indexOf(theMessage.sender)});
		window.location='send_msg';
	}
	
	function deleteCookie(){
		document.cookie = "receiverId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	}
</script>
<head>
<meta charset="ISO-8859-1">
<title>View Message - Web Forum</title>
</head>
<body onload="deleteCookie();">
	<jsp:include page="header.jsp"></jsp:include>
	<c:set scope="session" var="messageTitle" value="${theMessage.title}"/>
	<div class="container">
		<h2>
			<c:out
				value="Message from ${theMessage.sender.name} (${theMessage.sender.email})" />
		</h2>
		<p>
			<c:out value="Received on ${theMessage.dateReceived}"></c:out>
		</p>
		<div>
			<table border="1">
				<tr>
					<th>Title:</th>
					<td>${theMessage.title}</td>
				</tr>
				<tr>
					<th>Received on:</th>
					<td>${theMessage.dateReceived}</td>
				</tr>
				<tr>
					<th>Message:</th>
					<td>${theMessage.content}</td>
				</tr>
			</table>
			<br>
			<button onclick="replyOnClick();">Reply</button>
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
</style>
</html>