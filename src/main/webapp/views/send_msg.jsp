<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function retrieveEmail() {
		let senderIndex = sessionStorage.getItem('senderIndex');
		if (senderIndex != null) {
			document.getElementById("mySelect").selectedIndex = senderIndex;
		}
	}
	
	function deleteCookie(){
		document.cookie = "receiverId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	}

	function setReceiverIndex() {
		let receiver = document.getElementById("mySelect");
		document.cookie = "receiverId=" + receiver.value + ";" + ";path=/";
		console.log("Its working");
	}
</script>
<head>
<meta charset="ISO-8859-1">
<title>Send Message - Web Forum</title>
</head>
<body onload="retrieveEmail(); deleteCookie();">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<h2>Send Message</h2>
		<div>
			<form:form action="send_msg_action" modelAttribute="msg">
				<table border="1">
					<tr>
						<th>To:</th>
						<td><select id="mySelect">
								<c:forEach items="${usersList}" var="user" varStatus="count">
									<option value="${count.index}"><c:out
											value="${user.name} (${user.email})" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>Title:</th>
						<td><c:choose>
								<c:when test="${not empty messageTitle}">
									<form:input type="text" path="title" placeholder="Title"
										value="Re:${messageTitle}" />
									<form:errors path="title" cssClass="error" />
									<c:remove var="messageTitle" scope="session" />
								</c:when>
								<c:otherwise>
									<form:input type="text" path="title" placeholder="Title" />
									<form:errors path="title" cssClass="error" />
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<th>Content:</th>
						<td><form:textarea path="content" rows="4" cols="50"
								placeholder="Content"></form:textarea></td>
					</tr>
				</table>
				<br>
				<input type="submit" name="send" value="Send"
					onclick="setReceiverIndex();" />
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