<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Portal : <c:out value= "${action}"/> Department</title>
</head>
<body>
	<div><strong><c:out value= "${action}"/></strong> a department</div>
	<form action="departments.html" method="POST">
		<input type="text" name="name" value="${department.name}"></input>
		<input type="submit" value="Add Department" name="addDepartmentBtn">
	</form>
</body>
</html>