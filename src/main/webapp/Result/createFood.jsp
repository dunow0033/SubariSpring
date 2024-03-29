<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Admin - Create Food Page</title>
</head>
<body>
<form:form action="adminCreateFoodResult" method="post" modelAttribute="foodobj">
<h1 style="color: red;">Admin - Create Food Page</h1>
<table>
	<tr>
		<td>Name of Food</td>
		<td><form:input path="name" /></td>
	</tr>
	<tr>
		<td>Price of Food</td>
		<td><form:input path="price" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Add Food" /></td>
	</tr>
</table>

</form:form>

</body>
</html>