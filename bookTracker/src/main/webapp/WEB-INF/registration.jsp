<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="icon" href="/assets/stackedBooks.png">
<title>BT - User Registration</title>
</head>	
<body class="container">
	<div class="row justify-content-md-center mt-5 py-4 px-5 bg-warning bg-gradient bg-opacity-50 rounded-top shadow-lg">
		<a class="col col-lg-2 btn btn-dark btn-lg my-auto" href="/" >Back</a>
		<h1 class="col-md-auto offset-1" ><a href="/" class="display-1 fw-bold text-decoration-none text-dark">Books Tracker</a></h1>
		<a class="col col-lg-2 offset-1 btn btn-dark btn-lg my-auto" href="/login" >Login</a>
	</div>
	<form:form class="w-75 mx-auto p-5 text-center bg-warning bg-opacity-50 bg-gradient my-5 shadow rounded" action="/register" method="post" modelAttribute="newUser">
		<h1 class="">Register a New User</h1>
        <form:errors path="username" class="text-danger fw-bold text-opacity-75" />
		<div class="row mb-3 w-75 mx-auto">
			<form:label path="username" class="form-label text-start fs-5 fw-bold" >User Name:</form:label>
			<form:input path="username" type="text" class="form-control" />
		</div>
        <form:errors path="email" class="text-danger fw-bold text-opacity-75" />
		<div class="row mb-3 w-75 mx-auto">
			<form:label path="email" class="form-label text-start fs-5 fw-bold" >Email:</form:label>
			<form:input path="email" type="email" class="form-control" />
		</div>
        <form:errors path="password" class="text-danger fw-bold text-opacity-75" />
		<div class="row mb-3 w-75 mx-auto">
			<form:label path="password" class="form-label text-start fs-5 fw-bold" >Password:</form:label>
			<form:input path="password" type="password" class="form-control" />
		</div>
        <form:errors path="confirmPassword" class="text-danger fw-bold text-opacity-75" />
		<div class="row mb-3 w-75 mx-auto">
			<form:label path="confirmPassword" class="form-label text-start fs-5 fw-bold" >Confirm Password:</form:label>
			<form:input path="confirmPassword" type="password" class="form-control" />
		</div>
	    <button type="submit" class="mt-3 btn btn-primary w-75" >Register!</button>
	</form:form>
</body>