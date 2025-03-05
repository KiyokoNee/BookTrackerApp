<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<h1>Test</h1>
	<form method="POST" action="/api/search">
		<label>Search books:</label>
		<input name="bookQuery" />
		<button type="submit">Search</button>
	</form>
	<form method="POST" action="/api/">
		<label>Retrieve Book:</label>
		<input name="bookKey" />
		<button type="submit">Search</button>
	</form>
	<h2>Search Book Test</h2>
	<p>Adjusted Query: <c:out value="${adjustedQuery}"></c:out></p>
	<p>URI Call: <c:out value="${uriCall}"></c:out></p>
	<p>Clean Key (1st Item): <c:out value="${cleanKey}"></c:out></p>
	<h3>Search Results</h3>
	<c:forEach var="book" items="${books}">
	<div class="card">
		<p>Work Title: <c:out value="${book.title}"></c:out></p>
		<p>Work Key: <c:out value="${book.key}"></c:out></p>
		<p>Book Title: <c:out value="${book.editions.docs.get(0).title}"></c:out></p>
		<c:if test="${not empty book.editions.docs.get(0).subtitle }">
			<p>Book Subtitle: <c:out value="${book.editions.docs.get(0).subtitle}"></c:out></p>
		</c:if>
		<p>Book Key: <c:out value="${book.editions.docs.get(0).key}"></c:out></p>
		<p>Book Description: <c:out value="${book.editions.docs.get(0).description}"></c:out></p>
	</div>
	</c:forEach>
	<h2>Book Details Test</h2>
	<c:if test="${not empty book}">
	<div class="card">
		<img src="${imgURL}" />
		<p>Title: <c:out value="${book.title}"></c:out></p>
		<c:if test="${not empty book.subtitle }">
			<p>Subtitle: <c:out value="${book.editions.docs.get(0).subtitle}"></c:out></p>
		</c:if>
		<p>Description: <c:out value="${book.description.value}"></c:out></p>
		<p>Author(s)</p>
		<c:forEach var = "author" items="${authors}">
			<p><c:out value="${author}"></c:out></p>
		</c:forEach>
		<p>Subject(s)</p>
		<c:forEach var = "subject" items="${book.subjects}">
			<p><c:out value="${subject}"></c:out></p>
		</c:forEach>
		<p>Number of Pages: <c:out value="${book.number_of_pages }"></c:out></p>
	</div>
	</c:if>
</body>
</html>