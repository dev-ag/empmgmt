<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Portal</title>
</head>
<body>
	<div style="background-color: olive;" align="center">
		<h2>
			<br>Employee &amp; Department Portal
		</h2>
		<hr>
	</div>
	Select from following to add or update the employees:
	<form action="/employees.html" method="get">
		<input type="submit" value="Add" name="add"><br>
		<input type="submit" value="Delete" name="delete"><br>
		<input type="submit" value="Update" name="update">
		<!-- TODO: Render the employees list here. Since I am not able to now, rendering it in a new jsp employeelist.jsp.-->
	</form>
</body>
</html>