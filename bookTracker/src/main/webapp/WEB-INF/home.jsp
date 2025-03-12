<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="icon" href="/assets/stackedBooks.png">
<title>Books Tracker</title>
</head>
<body class="container">
	<div class="row justify-content-md-center mt-5 py-4 px-5 bg-warning bg-gradient bg-opacity-50 rounded-top shadow-lg">
		<div class="col col-lg-2" >
			<p class="fs-5 m-0">Don't have a Login?</p>
			<a class="btn btn-dark btn-lg w-100" href="/register" >Register</a>
		</div>
		<h1 class="col-md-auto offset-1" ><a href="/" class="display-1 fw-bold text-decoration-none text-dark">Books Tracker</a></h1>
		<a class="col col-lg-2 offset-1 btn btn-dark btn-lg my-auto" href="/login" >Login</a>
	</div>
	<img class="border border-secondary border-5 rounded-bottom w-100 shadow-lg" src="${pageContext.request.contextPath}/assets/bookshelf2.jpg" alt="bookshelf"> 
	<div class="mt-5 mx-auto">
		<form class="row w-75 mx-auto mb-3" method="GET" action="/search">
			<div class="col-10">
				<label class="form-label text-start fs-4" >Search our Books in Store!</label>
				<input name="query" type="text" class="form-control"  />
			</div>
			<button type="submit" class="btn btn-dark col align-self-end" > Search</button>
		</form>
		<div class="border border-1 border-secondary bg-secondary bg-gradient bg-opacity-25 shadow">
			<div class="row row-cols-1 row cols-sm-2 g-3 p-5">
				<c:forEach var="book" items="${books}">
					<div class="col w-25 h-100">
						<div class="card">
						  <div class="card-body">
						    <h5 class="card-title"><c:out value="${book.editions.docs.get(0).title}" /></h5>
						    <h6 class="card-subtitle mb-2 text-muted"><c:out value="${book.title}" /></h6>
						    <p class="card-text text-truncate"><c:out value="${book.editions.docs.get(0).description}" /></p>
		  					<c:if test="${not empty book.editions.docs.get(0).subtitle }">
								<p class="text-truncate" >Notes: <c:out value="${book.editions.docs.get(0).subtitle}" /></p>
							</c:if>
						    <a href="/book/${book.editions.docs.get(0).key.trim().replaceAll('/books/', '')}/details" class="btn btn-dark btn-sm">More Details</a>
						  </div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>