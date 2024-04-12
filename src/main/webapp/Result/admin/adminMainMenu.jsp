<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin -- Main Menu</title>
<style>
	.center {
		margin: auto;
		text-align: center;
		margin-bottom: 20px;
	}
</style>
</head>
<body>

<h1 style="margin:auto; text-align: center; color: red; font-style: italic; font-weight: bold;">Administrative Functions</h1>

<div class="center">
	<select onchange="window.location.href=this.value;">
		<option>Choose an Option</option>
		<option value="/adminViewUserOptions">View User Options</option>
		<option value="/adminTodaysBills">View Today's Bills</option>
		<option value="/adminViewCurrentMonthTotalSale">View Current Month Total Sale</option>
		<option value="/adminViewFoodOptions">View Food Options</option>
	</select>
</div>

</body>
</html>