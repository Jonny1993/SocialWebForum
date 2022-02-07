<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - Web Forum</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div style="text-align: center;">
		<fieldset>
			<legend>Registration Form</legend>
			<c:if test="${not empty alreadyExists}">
				<p class="error">
					<c:out value="${alreadyExists}" />
				</p>
				<c:remove var="alreadyExists" scope="session" />
			</c:if>
			<form:form action="register_action" modelAttribute="user">
				<form:input type="text" path="name" placeholder="Full Name" />
				<br>
				<form:errors path="name" cssClass="error" />
				<form:input type="text" path="email" placeholder="Email"
					autocomplete="off" />
				<br>
				<form:errors path="email" cssClass="error" />
				<form:password path="password" placeholder="Password" />
				<form:errors path="password" cssClass="error" />
				<input style="display:inherit; margin: auto;" type="submit" value="Register" style="margin-right: 5px;" />
			</form:form>
		</fieldset>
	</div>
</body>
<style>
.error {
	color: red;
	text-align: center;
	display: block;
}
</style>
</html>