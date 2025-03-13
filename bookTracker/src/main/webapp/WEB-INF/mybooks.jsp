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
<title>BT- My Books</title>
</head>
<body class="container">
	<div class="row justify-content-md-center  align-items-center mt-5 py-4 px-5 bg-warning bg-gradient bg-opacity-50 rounded-top shadow-lg">
		<form:form class="col col-lg-2" action="/logout" method="post">
			<button type="submit" class="btn btn-danger btn-lg" >Logout</button>
		</form:form>
		<div class="col-md-auto offset-1 text-center">
			<h1 class="display-1 fw-bold" >Books Tracker</h1>
			<h2 class="display-3 fw-bold alert alert-success font-monospace py-0" >My Books</h2>
		</div>
		<a class="col col-lg-2 offset-1 btn btn-dark btn-lg my-auto" href="/dashboard" >Dashboard</a>
	</div>
	<div class="my-5 py-5 px-5 bg-warning bg-gradient bg-opacity-50 rounded-top shadow-lg">
		<h3 class="h2 fw-bold">Currently Borrowing</h3>
		<hr>
		<div class="row row-cols-1 row-cols-sm-2 g-3 ">
			<c:if test="${borrowedBooks.size() == 0}">
				<h4 class="alert alert-danger">---- No Books are being Borrowed at this time ----</h4>
			</c:if>
			<c:forEach var = "book" items="${borrowedBooks}">
				<div class="col">
					<div class="card h-100">
					    <div class="card-body px-4 d-flex flex-column justify-content-between ">
					    	<div class="">
							    <h4 class="card-title h2"><c:out value="${book.title}" /></h4>
							    <h5 class="card-subtitle mb-2 text-muted h5">
							    	<c:choose>
							    		<c:when test="${not empty book.authors}">
									    	<c:forEach var = "author" items="${book.authors}">
									    		- <c:out value="${author.name}"/> - 
									    	</c:forEach>
							    		</c:when>
							    		<c:otherwise>
											<p class="font-monospace text-muted fs-6">No Author Information Currently Available...</p>				    		
							    		</c:otherwise>
							    	</c:choose>
							    </h5>
							    <hr>
					    	</div>
						    <div class="d-flex flex-column justify-content-between h-100">
						    	<div>
							    	<p class="fw-bold m-0 ">Number of Pages: 
							    		<c:choose>
								    		<c:when test="${not empty book.totalPages}">
										    	<c:out value="${book.totalPages}" />
								    		</c:when>
								    		<c:otherwise>
												<p class="font-monospace text-muted">No Page Information Currently Available...</p>					    		
								    		</c:otherwise>
								    	</c:choose>
							    	</p>
							    	<p class="fw-bold" >Pages Read: <c:out value="${book.pagesRead}" /></p>
						    	</div>
							    <div class="d-flex justify-content-between ">
							    	<a class="btn btn-sm btn-dark w-25 " href="/book/${book.bookKey}/edit" >Edit</a>
									<form:form class="w-25 " action="/book/${book.bookKey}/delete" method="post">
										<input type="hidden" name="_method" value="delete" >
										<button type="submit" class="btn btn-danger btn-sm w-100" >Return</button>
									</form:form>
						    	</div>
						    </div>
					    </div>
					    <fmt:parseDate var="returnByDate" value="${book.returnBy}" pattern="yyyy-MM-dd" type="date" />
						<fmt:parseDate var="currentDate" value="${today}" pattern="yyyy-MM-dd" type="date" />	
						<div class="card-footer text-muted">Return Date: <c:out value="${book.returnBy}" />
							<c:if test="${returnByDate.before(currentDate) }">
								<span class="alert alert-danger p-1 fw-bold font-monospace"> --- OVERDUE ---</span>
							</c:if>
						</div>			    
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	
</body>
</html>