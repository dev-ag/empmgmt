<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employee Portal : Employees</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>Employee Portal</title>
</head>
<body>
	<div class="container">
		<div style="margin-top: 50px">
			<a href="employees.html?action=add" class="btn btn-primary active"
				role="button">Add an employee</a> <a href="welcome.html"
				class="btn btn-primary active" role="button">Back to Home</a>
		</div>
		<h2>Listing employees:</h2>
		<c:if test="${param.added}">
			<div class="alert alert-success" role="alert">Employee was
				added successfully.</div>
		</c:if>
		<c:if test="${param.updated}">
			<div class="alert alert-success" role="alert">Employee was
				updated successfully.</div>
		</c:if>
		<c:if test="${param.deleted}">
			<div class="alert alert-success" role="alert">The delete
				operation was successful.</div>
		</c:if>
		<c:if test="${not empty employees}">
			<table class="table">
				<tr>
					<th>S.No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Age</th>
					<th>Salary</th>
					<th>Full Time</th>
					<th>Joining Date</th>
					<th>Department</th>
					<th>Action</th>
				</tr>
				<c:forEach var="employee" items="${employees}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<td>${employee.email}</td>
						<td>${employee.age}</td>
						<td>${employee.salary}</td>
						<td><c:choose>
								<c:when test="${employee.fullTime}">Full Time</c:when>
								<c:otherwise>Part Time</c:otherwise>
							</c:choose></td>
						<td>${employee.joinDate}</td>
						<td>${employee.department.name}</td>
						<td><a href="employees.html?action=edit&id=${employee.id}">Edit</a>
							/ <a href="employees.html?action=delete&id=${employee.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty employees}">
			<div>
				No employees to show. Please <a href="employees.html?action=add">add</a>
				an employee.
			</div>
		</c:if>
	</div>
</body>
</html>