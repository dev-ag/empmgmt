<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<c:if test="{employees!=null}"></c:if>
	<c:out value="ABCD"></c:out>
		<table>
			<tr>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Age</th>
			</tr>
			<c:forEach var="employee" items="${employees}">
				<tr>
					<td><c:out value="${employee.size}" /></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>