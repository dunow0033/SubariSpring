<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin - View Today's Bills</title>
</head>
<body>

<h1 style="color: red; font-style: italic; font-weight: bold;">Today's Bills</h1>

<ol>
	<c:forEach items="${todaysBills}" var="bill">
		<li>${bill.username} -- ${bill.totalAmount}</li>
	</c:forEach>
</ol>

</body>
</html>