<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Portal : <c:out value= "${param.action}"/> Department</title>
</head>
<body>
	<div><strong><c:out value= "${param.action}"/></strong> a department</div>
	<form action="departments.html?action=${param.action}" method="POST">
		<input type="hidden" name="id" value="${department.id}"></input>
		<input type="text" name="name" value="${department.name}"></input>
		<input type="submit" value="${param.action} Department" name="addDepartmentBtn">
	</form>
</body>
</html>