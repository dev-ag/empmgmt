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

	<form action="/employee.html" method="post">
		To add an employee, please fill in the employee details below : <br>
		<br>
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstname"></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastname"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="number" name="age" min=16></td>
			</tr>
			<tr>
				<td>Salary</td>
				<td><input type="text" name="salary"></td>
			</tr>
			<tr>
				<td>Full Time</td>
				<td><select name="fulltime">
						<option value="true" selected>Yes</option>
						<option value="false">No</option>
				</select></td>
			</tr>
			<tr>
				<td>Join Date</td>
				<td><input type="date" name="joindate"></td>
			</tr>
			<tr>
				<td>Department</td>
				<td><select name="department">
						<option value="IT Manager">IT Manager</option>
						<option value="Tech Lead">Tech Lead</option>
						<option value="Senior Developer">Senior Developer</option>
						<option value="Developer" selected>Developer</option>
						<option value="Intern">Intern</option>
				</select> <!-- TODO: Get the departments from database and then populate them here -->
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="Add" name="add"></td>
			</tr>
		</table>
	</form>
</body>
</html>