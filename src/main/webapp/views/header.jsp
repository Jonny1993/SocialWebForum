<%@page import="dao.User"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
	<div style="float: left;">
		<c:if test="${not empty currentUser}">
			<a href="${pageContext.request.contextPath}/dashboard">Home</a> | 
		<a href="${pageContext.request.contextPath}/create_log">Create Log</a> | 
		<a href="${pageContext.request.contextPath}/my_logs">My Logs</a> | 
		<a href="${pageContext.request.contextPath}/my_messages">My
				Messages</a>
		</c:if>
	</div>

	<div style="float: right;">
		<c:choose>
			<c:when test="${empty currentUser}">
				<a href="${pageContext.request.contextPath}/login">Login</a>
			</c:when>
			<c:otherwise>
			Logged in as: ${currentUser.name} | 
		<a href="${pageContext.request.contextPath}/logout">Logout</a>
			</c:otherwise>
		</c:choose>
	</div>

</div>

<br />
<br />
<div>

	<header style="background-color: gray; color: white; height: 50px">
		<h1 style="text-align: center;">Social Web Forum</h1>
	</header>

</div>