<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>Employee Portal:<c:out value="${param.action}" />
	Employee
</title>
</head>
<body class="container">
	<div>
		<h2>
			<c:out value="${param.action}" />
			Employee
		</h2>
	</div>
	<c:if test="${not empty errors}">
		<div class="col-sm-6 alert alert-danger" role="alert">
			<ul>
				<c:forEach var="error" items="${errors}" varStatus="counter">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="col-sm-8">
		<form action="employees.html?action=${param.action}" method="post"
			class="form-horizontal">
			<div>
				<input type=hidden name="id" value="${employee.id}" />
				<div class="form-group">
					<label class="control-label col-sm-2" for="firstname">First
						Name</label> <input type="text" name="firstname"
						value="${employee.firstName}" required>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="lastname">Last
						Name</label> <input type="text" name="lastname"
						value="${employee.lastName}" required>
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
					<label class="control-label col-sm-2" for="salary">Salary</label> <input
						type="text" name="salary" value="${employee.salary}" required>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="fulltime">Full
						Time</label> <select name="fulltime" value="${employee.fullTime}" required>
						<option value="true"
							${employee.fullTime ? 'selected="selected"' : ''}>Yes</option>
						<option value="false"
							${employee.fullTime==false ? 'selected="selected"' : ''}>No</option>
					</select>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="joindate">Join
						Date</label> <input type="date" name="joindate" placeholder="yyyy-mm-dd"
						value="${employee.joinDate}" required>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="department">Department</label>
					<select name="department"
						value="${employee.department.name} required">
						<c:forEach var="department" items="${departments}">
							<option value="${department.name}"
								${department.name==employee.department.name ? 'selected="selected"' : ''}>${department.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">
							<c:out value="${param.action}" />
							Employee
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>