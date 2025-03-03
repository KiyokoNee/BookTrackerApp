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
	<p>Adjusted Query: <c:out value="${adjustedQuery}"></c:out></p>
	<p>URI Call: <c:out value="${uriCall}"></c:out></p>
	<p>Clean Key (1st Item): <c:out value="${cleanKey}"></c:out></p>
	<c:forEach var="book" items="${books}">
		<p><c:out value="${book}"></c:out></p>
	</c:forEach>
	<p><c:out value="${book.title}"></c:out></p>
</body>
</html>