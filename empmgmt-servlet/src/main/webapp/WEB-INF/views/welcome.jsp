<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal</title>
</head>
<body>
	<div style="background-color: olive;" align="center">
		<h2>
			<br>Employee &amp; Department Portal
		</h2>
		<hr>
	</div>
	<p>Welcome to the Portal! It allows you manage employee's
		information and department allocation.</p>

	<p>Select following to access the employee OR department
		information:</p>
	<form action="/welcome.do" method="post">
		<input type="submit" value="Employee" name=employee> <input
			type="button" value="Department" name=department>
	</form>

</body>
</html>