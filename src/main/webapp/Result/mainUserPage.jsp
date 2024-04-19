<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subari Main Page</title>
<style>
	.center {
		margin: auto;
		text-align: center;
		margin-bottom: 20px;
	}
</style>
</head>
<body>
<h1>Logged in!! Success!!</h1>

<p>Hello, ${name}, welcome!!!</p>

<h1 style="margin:auto; text-align: center; color: red; font-style: italic; font-weight: bold;">Options:</h1>

<div class="center">
	<select id="optionSelect" onchange="window.location.href=this.value;">
		<option>Choose an Option</option>
		<option value="/ViewFoodMenu?name=${name}">View Food Menu</option>
		<option value="/ViewOrderHistory">View Order History</option>
	</select>
</div>
</body>
</html>