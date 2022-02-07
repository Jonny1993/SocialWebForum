<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Web Forum</title>
    </head>
<body>
<jsp:include page="views/header.jsp"/>
<h1 style="text-align: center;">Welcome to the Forum</h1>
<h3 style="text-align: center;">Please <a href="${pageContext.request.contextPath}/login">login</a> to continue</h3>
</body>
</html>
