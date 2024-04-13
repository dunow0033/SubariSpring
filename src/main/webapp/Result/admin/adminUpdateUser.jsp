<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Admin - Update User Page</title>
</head>
<body>
<form:form action="AdminUpdateUserResult" method="post" modelAttribute="userobj">
<h1 style="color: red;">Admin - Update User Page</h1>
<table>
	<tr>
		<td>First Name and Last Name</td>
		<td><form:input path="name" /></td>
	</tr>
	<tr>
		<td>Username</td>
		<td><form:input path="username" /></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><form:input path="password" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Save Changes" /></td>
	</tr>
</table>

</form:form>

</body>
</html>


<!-- <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Admin - Create User Page</title>
</head>
<body>
<form:form action="AdminRegistrationResult" method="post" modelAttribute="userobj">
<h1 style="color: red;">Admin - Create User Page</h1>
<table>
	<tr>
		<td>First Name and Last Name</td>
		<td><form:input path="name" /></td>
	</tr>
	<tr>
		<td>Username</td>
		<td><form:input path="username" /></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><form:input path="password" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Register" /></td>
	</tr>
</table>

</form:form>

</body>
</html>-->