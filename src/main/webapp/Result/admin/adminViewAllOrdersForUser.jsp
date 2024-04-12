<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin - View ${username}'s Orders</title>
</head>
<body>

<h1 style="margin:auto; width: 50%; color: red; font-style: italic; font-weight: bold;">View All of ${username}'s Orders</h1>

<ol>
	<c:forEach items="${viewAllOrdersForUser}" var="order">
		 <li>
            Food: ${order.food.name}<br>
            Quantity: ${order.quantity}<br>
            Total Price: ${order.quantity * order.food.price}<br>
        </li>
	</c:forEach>
</ol>

</body>
</html>