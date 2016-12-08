<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
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
<title>Employee Portal : <c:out value="${param.action}" />
	Department
</title>
</head>
<body class="container">
	<div>
		<h2>
			<c:out value="${param.action}" />
			a department
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
		<form:form class="form-inline" action="add" method="POST" modelAttribute="department">
			<div class="form-group">
				<!--  <input type="hidden" name="id" value="${department.id}"></input> -->
				<label
					for="name">Name</label> <input type="text" class="form-control"
					id="name" name="name" placeholder="Enter department name"
					value="${department.name}">
			</div>
			<button type="submit" class="btn btn-success">
				<c:out value="${param.action}" />
				Department
			</button>
		</form:form>
	</div>
</body>
</html>