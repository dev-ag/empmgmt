<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employee Portal : Departments</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<div style="margin-top: 50px">
			<a href="departments.html?action=add" class="btn btn-primary active"
				role="button">Add a department</a> <a href="welcome.html"
				class="btn btn-primary active" role="button">Back to Home</a>
		</div>
		<br />
		<h2>Listing departments:</h2>
		<c:if test="${param.added}">
			<div class="alert alert-success" role="alert">Department was
				added successfully.</div>
		</c:if>
		<c:if test="${param.updated}">
			<div class="alert alert-success" role="alert">Department was
				updated successfully.</div>
		</c:if>
		<c:if test="${param.deleted}">
			<div class="alert alert-success" role="alert">The delete
				operation was successful.</div>
		</c:if>
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
							href="departments.html?action=edit&id=${department.id}">Edit</a>
							/ <a href="departments.html?action=delete&id=${department.id}">Delete</a></td>
					</tr>
				</c:forEach>
				<tr></tr>
			</table>
		</c:if>
		<c:if test="${empty departments}">
			<div>
				No departments to show. Please <a href="departments.html?action=add">add</a>
				a department.
			</div>
		</c:if>
	</div>
</body>
</html>