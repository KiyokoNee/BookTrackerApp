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
<title>BT - Edit ${oldBook.title}</title>
</head>
<body class="container">
	<div class="row justify-content-md-center  align-items-center mt-5 py-4 px-5 bg-warning bg-gradient bg-opacity-50 rounded-top shadow-lg">
		<form:form class="col col-lg-2" action="/logout" method="post">
			<button type="submit" class="btn btn-danger btn-lg" >Logout</button>
		</form:form>
		<div class="col-md-auto offset-1 text-center">
			<h1 class="display-1 fw-bold" >Books Tracker</h1>
			<h2 class="display-3 fw-bold alert alert-success font-monospace py-0" >Edit Book</h2>
		</div>
		<a class="col col-lg-2 offset-1 btn btn-dark btn-lg my-auto" href="/mybooks" >My Books</a>
	</div>
	<div class="w-75 mx-auto my-5 p-5 border border-4 rounded shadow">
		<div class="row mb-4">
			<div class="col-sm-4" >
				<c:choose>
					<c:when test="${not empty imgURL }">
						<img class="img-thumbnail w-100" src="${imgURL}" alt="cover-art" onload="if (this.naturalWidth === 1 && this.naturalHeight === 1) this.src='/assets/coverPlaceholder.png';">
					</c:when>
					<c:otherwise>
						<img class="img-thumbnail w-100" src="/assets/coverTemp.jpg" alt="cover-art">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col">
				<div class="row my-3 mx-5">
					<p class="h3 col-sm">Title:</p>
					<p class="h4 col-8 text-muted"><c:out value="${oldBook.title}" /></p>
				</div>
				<div class="row my-3 mx-5">
					<p class="h3 col-sm">Author(s):</p>
					<c:choose>
						<c:when test="${not empty authors }">
							<p class="h4 col-8 text-muted">
								<c:forEach var = "author" items="${authors}">
									<c:out value="${author.name} " />
								</c:forEach>
							</p>
						</c:when>
						<c:otherwise>
							<p class="fs-6 font-monospace text-muted">No Author Information Available...</p>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="my-3 mx-5">
					<p class="h5 ">Description:</p>
					<c:choose>
						<c:when test="${not empty oldBook.description}">
							<p class="fs-6 text-muted "><c:out value="${oldBook.description}" escapeXml="false" /></p>
						</c:when>
						<c:otherwise>
							<p class="fs-6 font-monospace text-muted">No Description Available...</p>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="my-1 mx-5">
					<p class="h5 ">Genres:</p>
					<c:choose>
						<c:when test="${not empty oldBook.subjects}">
							<p class="fs-6 text-muted">
								<c:forEach var = "subjectElem" items="${oldBook.subjects}">
									<c:out value="${subjectElem.subject} " />
								</c:forEach>
							</p>
						</c:when>
						<c:otherwise>
							<p class="fs-6 font-monospace text-muted">No Genres Available...</p>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="my-1 mx-5">
					<p class="h6 ">Number of Pages:</p>
					<c:choose>
						<c:when test="${not empty oldBook.totalPages}">
							<p class="h6 text-muted"><c:out value="${oldBook.totalPages }"/></p>
						</c:when>
						<c:otherwise>
							<p class="fs-6 font-monospace text-muted">No Page Information Available...</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<hr>
		<form:form class="d-flex justify-content-between mt-5 px-5 align-items-end" action="/book/${bookKey}/edit" method="post" modelAttribute="oldBook">
			<input type="hidden" name="_method" value="put" >
	
	        <form:errors path="pagesRead" class="text-danger fw-bold text-opacity-75" />
	        <div class="">
	        	<form:label path="pagesRead" class="form-label p-0 fw-bold">Pages Read:</form:label >
				<div class="input-group p-0">
				  <span class="input-group-text"></span>
				  <form:input path="pagesRead" type="number" class="form-control" aria-label="pagesRead" />
				</div>
	        </div>
	
	        <form:errors path="returnBy" class="text-danger fw-bold text-opacity-75" />
			<div class="">
				<p class="fw-bold m-0">Current Due Date: <span class="text-info  font-monospace"><c:out value="${ oldBook.returnBy}"/> </span></p>
				<form:label path="returnBy" class="form-label fw-bold" >Extend Due Date: </form:label>
				<form:input path="returnBy" type="date" class="form-control" value="${oldBook.returnBy}" />
			</div>
	
		    <button type="submit" class="btn btn-warning btn-lg" >Update</button>
		</form:form>
	</div>
</body>