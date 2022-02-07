<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - Web Forum</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div style="text-align: center;">
		<fieldset>
			<legend>Login Form</legend>
			<c:if test="${not empty wrongCredentials}">
				<p class="error">
					<c:out value="${wrongCredentials}" />
				</p>
				<c:remove var="wrongCredentials" scope="session" />
			</c:if>
			<form:form action="login_action" modelAttribute="user">
				<form:input type="text" path="email" placeholder="Email"
					autocomplete="off" />
				<br>
				<form:errors path="email" cssClass="error" />
				<form:password path="password" placeholder="Password" />
				<form:errors path="password" cssClass="error" />
				<div style="display: block;"><input type="submit" value="Login"/>
				| <a href="register">Register</a></div>
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