<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/bootstrap/css/bootstrap.min.css">
	<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>
<title>Employee Portal</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
		  <h2>Employee management portal</h2>
		  <p>Welcome to the Employee management Portal! It allows you manage employee's
			information and department allocation.</p>
		  <p><a class="btn btn-primary btn-lg" href="employees.html" role="button">Manage Employees</a> <a href="departments.html" class="btn btn-primary btn-lg" role="button">Manage Departments</a></p>
		</div>
	</div>
</body>
</html>