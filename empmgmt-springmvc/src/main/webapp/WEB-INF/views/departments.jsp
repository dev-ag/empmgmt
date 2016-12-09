<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Employee Portal : Departments</title>
	<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<ol class="breadcrumb">
		  <li><a href="welcome">Home</a></li>
		  <li class="active"><a href="#">Manage departments</a></li>
		</ol>
		<c:if test="${param.added}">
			<div class="alert alert-success" role="alert">Department was
				added successfully.</div>
		</c:if>
		<c:if test="${param.updated}">
			<div class="alert alert-success" role="alert">Department was
				updated successfully.</div>
		</c:if>
		<c:if test="${param.deleted}">
			<div class="alert alert-success" role="alert">The department was deleted successfully.</div>
		</c:if>
		
		<c:if test="${not empty errors}">
		<div class="col-sm-6 alert alert-danger" role="alert">
			<ul>
				<c:forEach var="error" items="${errors}" varStatus="counter">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
		</c:if>
		<!-- Add form -->
		<div class="col-sm-12">
			<form:form class="form-inline" action="${pageContext.request.contextPath}/departments/add" method="POST" modelAttribute="department">
				<div class="form-group">
					<input type="hidden" name="id" value="${department.id}"></input>
					<label
						for="name">Name</label> <input type="text" class="form-control"
						id="name" name="name" placeholder="Enter department name"
						value="${department.name}">
				</div>
				<button type="submit" class="btn btn-success">
					<c:out value="${btnLabel}" />
				</button>
			</form:form>
		</div>
		
		<!-- Listing -->
		<div class="col-sm-12">
		<h3>Listing departments:</h3>
		<c:if test="${not empty departments}">
			<table class="table">
				<tr>
					<th>S.No.</th>
					<th>Name</th>
					<th>Action</th>
				</tr>
				<c:forEach var="department" items="${departments}"
					varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${department.name}</td>
						<td><a
							href="${pageContext.request.contextPath}/departments/edit?id=${department.id}">Edit</a>
							/ <a href="${pageContext.request.contextPath}/departments/delete?id=${department.id}">Delete</a></td>
					</tr>
				</c:forEach>
				<tr></tr>
			</table>
		</c:if>
		<c:if test="${empty departments}">
			<div>
				No departments to show. Please add a department.
			</div>
		</c:if>
		</div>
	</div>
</body>
</html>