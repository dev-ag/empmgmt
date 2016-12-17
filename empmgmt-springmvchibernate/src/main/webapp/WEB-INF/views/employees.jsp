<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employee Portal : Employees</title>
<!-- set the variable context to the value of request.getContextPath -->
<c:set var="context" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="${context}/resources/bootstrap/css/bootstrap.min.css">
<script src="${context}/resources/bootstrap/js/bootstrap.min.js"></script>
<title>Employee Portal</title>
</head>
<body>
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="${context}/welcome">Home</a></li>
			<li class="active"><a href="#">Manage the employees</a></li>
		</ol>
		<!-- Display the Add/Edit/Delete message -->
		<c:if test="${param.added}">
			<div class="alert alert-success" role="alert">Employee was
				added successfully.</div>
		</c:if>
		<c:if test="${param.updated}">
			<div class="alert alert-success" role="alert">Employee was
				updated successfully.</div>
		</c:if>
		<c:if test="${param.deleted}">
			<div class="alert alert-success" role="alert">Employee was
				deleted successfully.</div>
		</c:if>
		<!-- Display errors -->
		<c:if test="${not empty errors}">
			<div class="col-sm-6 alert alert-danger" role="alert">
				<ul>
					<c:forEach var="error" items="${errors}" varStatus="counter">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<!-- Display Add/Edit Form -->
		<div class="col-sm-12">
			<form:form action="${context}/employees/add" method="post"
				modelAttribute="employee">
				<div>
					<form:input type="hidden" path="id" value="${employee.id}" />
					<div class="form-group">
						<label class="control-label col-sm-2" for="firstName">First
							Name</label> <input type="text" name="firstName"
							value="${employee.firstName}" required />
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="lastName">Last
							Name</label> <input type="text" name="lastName"
							value="${employee.lastName}" required />
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email</label> <input
							type="email" name="email" value="${employee.email}" required>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="age">Age</label> <input
							type="number" name="age" min=16 value="${employee.age}" required>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="salary">Salary</label>
						<input type="text" name="salary" value="${employee.salary}"
							required>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="fullTime">Full
							Time</label> <select name="fullTime" value="${employee.fullTime}"
							required>
							<option value="true"
								${employee.fullTime ? 'selected="selected"' : ''}>Yes</option>
							<option value="false"
								${employee.fullTime==false ? 'selected="selected"' : ''}>No</option>
						</select>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="joinDate">Join
							Date</label> <input type="date" name="joinDate" placeholder="yyyy-mm-dd"
							value="${employee.joinDate}" required>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="department">Department</label>
						<form:select path="department.name"
							value="${employee.department.name} required">
							<c:forEach var="department" items="${departments}">
								<option value="${department.name}"
									${department.name==employee.department.name ? 'selected="selected"' : ''}>${department.name}</option>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-success">
								<c:out value="${btnLabel}" />
							</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>

		<hr>
		<!-- Listing -->
		<div class="col-sm-12">
			<h3>Listing employees:</h3>
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
							<td><a href="${context}/employees/edit?id=${employee.id}">Edit</a>
								/ <a href="${context}/employees/delete?id=${employee.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty employees}">
				<div>No employees to show. Please add an employee. an
					employee.</div>
			</c:if>
		</div>
	</div>
</body>
</html>