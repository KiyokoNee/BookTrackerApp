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
<title>BT - ${book.title}</title>
</head>
<body class="container">
	<div class="row justify-content-md-center  align-items-center mt-5 py-4 px-5 bg-warning bg-gradient bg-opacity-50 rounded-top shadow-lg">
		
		<form:form class="col col-lg-2" action="/logout" method="post">
			<button type="submit" class="btn btn-danger btn-lg" >Logout</button>
		</form:form>
		<div class="col-md-auto offset-1 text-center">
			<h1 class="display-1 fw-bold" >Books Tracker</h1>
			<a href="/search?query=${searchQuery}" class="btn btn-sm btn-dark my-2" > Back to Search Results</a>
		</div>
		<a class="col col-lg-2 offset-1 btn btn-dark btn-lg my-auto" href="/mybooks" >My Books</a>
	</div>

	<div class="w-75 mx-auto my-5 p-5 border border-4 rounded shadow">
		<div class="row mb-4">
			<div class="col-sm-4" >
				<c:if test="${loggedInUser != null}">
					<c:choose>
						<c:when test="${potentialBook == null }" >
							<div class="alert alert-success fs-4 fw-bold text-center p-0" role="alert">
								Available
							</div>
						</c:when>
						<c:otherwise>
							<div class="alert alert-danger fs-4 fw-bold text-center p-0" role="alert">
								Borrowed
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
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
						<c:when test="${not empty book.description}">
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
		<c:if test="${loggedInUser != null}">
			<hr>
			<div>
				<c:if test="${potentialBook != null }" >
					<div class="card w-75 mx-auto">
					  <div class="card-body">
					    <p class="card-title h4 mb-3">Currently Borrowing: <span class="text-secondary fw-bold font-monospace fs-5"><c:out value="${potentialBook.borrower.username}" /> </span></p>
					    <p class="card-subtitle text-muted h6">Pages Read: <c:out value="${potentialBook.pagesRead}" /> </p>
					    <p class="card-subtitle mb-2 text-muted h6">Return Due Date: 
					    	<span class="text-info"><c:out value="${potentialBook.returnBy}" /></span>
					    	<c:if test="${overdue}">
					    		<span class="badge bg-danger">OVERDUE</span>
					    	</c:if>
					    </p>
					  </div>
					</div>
				</c:if>
			</div>
			<c:if test="${potentialBook == null }" >
				<form method="POST" action="/borrow/${bookKey}" class="mt-4">
					<button type="submit" class="btn btn-lg btn-dark">Borrow</button>
				</form>
			</c:if>
			<c:if test="${potentialBook.borrower.id == loggedInUser }">
				<form:form class="text-end mt-4" action="/book/${potentialBook.bookKey}/delete" method="post">
					<input type="hidden" name="_method" value="delete" >
					<button type="submit" class="btn btn-danger btn" >Return</button>
				</form:form>
			</c:if>
		</c:if>
	</div>
</body>
</html>