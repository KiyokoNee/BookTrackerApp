<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="icon" href="/assets/stackedBooks.png">
<title>BT - ${book.title}</title>
</head>
<body class="container">
	<div class="text-center mt-5 py-4 px-5 bg-warning bg-gradient bg-opacity-50 rounded-top shadow-lg ">
		<h1><a href="/" class="display-1 fw-bold text-decoration-none text-dark">Books Tracker</a></h1>
		<a href="/search?query=${searchQuery}" class="btn btn-sm btn-dark my-2" > Back to Search Results</a>
	</div>

	<div class="w-75 mx-auto my-5 p-5 border border-4 rounded shadow">
		<div class="row mb-4">
			<div class="col-sm-4" >
				<div class="alert alert-danger fs-4 fw-bold text-center p-0" role="alert">
				  Borrowed
				</div>
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
					<p class="h4 col-8 text-muted"><c:out value="${book.title}" /></p>
				</div>
				<div class="row my-3 mx-5">
					<p class="h3 col-sm">Author(s):</p>
					<c:choose>
						<c:when test="${not empty authors }">
							<p class="h4 col-8 text-muted">
								<c:forEach var = "author" items="${authors}">
									<c:out value="${author} " />
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
						<c:when test="${not empty book.description.value }">
							<p class="fs-6 text-muted "><c:out value="${book.description.value}" escapeXml="false" /></p>
						</c:when>
						<c:otherwise>
							<p class="fs-6 font-monospace text-muted">No Description Available...</p>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="my-1 mx-5">
					<p class="h5 ">Genres:</p>
					<c:choose>
						<c:when test="${not empty book.subjects}">
							<p class="fs-6 text-muted">
								<c:forEach var = "subject" items="${book.subjects}">
									<c:out value="${subject} " />
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
						<c:when test="${not empty book.number_of_pages}">
							<p class="h6 text-muted"><c:out value="${book.number_of_pages }"/></p>
						</c:when>
						<c:otherwise>
							<p class="fs-6 font-monospace text-muted">No Page Information Available...</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div>
			<div class="card w-75 mx-auto">
			  <div class="card-body">
			    <p class="card-title h4 mb-3">Currently Borrowing: </p>
			    <p class="card-subtitle mb-3 text-muted h6">Pages Read: </p>
			    <p class="card-subtitle mb-2 text-muted h6">Return Due Date: </p>
			  </div>
			</div>
		</div>
		<form method="POST" action="/borrow/${bookKey}" class="mt-4">
			<button type="submit" class="btn btn-lg btn-dark">Borrow</button>
		</form>
	</div>
</body>
</html>