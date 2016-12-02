<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Portal : Departments</title>
</head>
<body>
	<a href="departments.html?action=add">Add a department</a>
	<div>Listing departments:</div>
	<c:if test="${param.added}">
		<div>Department was added successfully.</div>
	</c:if>
	<c:if test="${param.updated}">
		<div>Department was updated successfully.</div>
	</c:if>
	<c:if test="${param.deleted}">
		<div>The delete operation was successful.</div>
	</c:if>
	<c:if test="${not empty departments}">
		<table>
			<tr><th>S.No.</th><th>Name</th><th>Action</th></tr>
			<c:forEach var="department" items="${departments}" varStatus="counter">
				<tr>
				    <td>${counter.count}</td>
				    <td>${department.name}</td>
				    <td><a href="departments.html?action=edit&id=${department.id}">Edit</a> / <a href="departments.html?action=delete&id=${department.id}">Delete</a></td>
				</tr>
 			</c:forEach>
			<tr></tr>
		</table>
	</c:if>
	<c:if test="${empty departments}">
		<div>No departments to show. Please <a href="departments.html?action=add">add</a> a department.</div>
	</c:if>
</body>
</html>