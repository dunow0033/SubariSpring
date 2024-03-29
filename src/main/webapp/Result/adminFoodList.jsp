<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin - List of Users</title>
</head>
<body>

<ol>
	<c:forEach items="${foodList}" var="food">
		<li>${food.name} -- ${food.price}</li>
	</c:forEach>
</ol>

</body>
</html>