<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<title>Save Travels</title>
</head>
<body class="">
	<div class="d-flex flex-column w-50 m-auto p-5">
		<h1><a href="/expenses" class="text-decoration-none">Save Travels</a></h1>
		<table class="table table-borderless border border-dark border-3 text-center">
		    <thead>
		        <tr class="table table-info">
		            <th class="border-start border-dark border-3" scope="col">Expense</th>
		            <th class="border-start border-dark border-3" scope="col">Vendor</th>
		            <th class="border-start border-dark border-3" scope="col">Amount</th>
		            <th class="border-start border-dark border-3" scope="col" colspan="2">Actions</th>
		        </tr>
		    </thead>
		    <tbody class="align-baseline">
		    	<c:forEach var="travel" items="" varStatus="status">
			    	<tr class="">
			    		<td class="border-start border-dark border-3"><a href="/expenses/" ><c:out value=""/></a></td>
			    		<td class="border-start border-dark border-3"><c:out value=""/></td>
			    		<td class="border-start border-dark border-3"><fmt:formatNumber value="" minFractionDigits="2" maxFractionDigits="2" /></td>
			    		<td class="border-start border-dark border-3"><a href="">edit</a></td>
			    		<td class="">
							<form action="/expenses/edit/" method="post" class="">
								<input type="hidden" name="_method" value="delete">
								<button type="submit" class="btn btn-outline-danger btn-sm">Delete</button>
							</form>
						</td>
			    	</tr>
		    	</c:forEach>
		    </tbody>
		</table>
	</div>  
</body>
</html>