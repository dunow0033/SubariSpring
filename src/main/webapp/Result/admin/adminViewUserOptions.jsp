<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin -- Menu of User Options</title>
<style>
	.center {
		margin: auto;
		width:60%;
		text-align: center;
	}
</style>
</head>
<body>

<h1 style="margin:auto; width: 50%; color: red; font-style: italic; font-weight: bold;">Admin -- Menu of User Options</h1>

<div class="center">
	<select>
		<option value="/adminViewUserNames">View User Names</option>
		<option value="/adminTodaysBills">View Today's Bills</option>
		<option value="/adminViewCurrentMonthTotalSale">View Current Month Total Sale</option>
		<option value="/adminViewFoodOptions">View Food Options</option>
	</select>
</div>

<p id="content">

</p>

</body>
</html>