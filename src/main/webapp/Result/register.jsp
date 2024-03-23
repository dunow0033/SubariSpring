<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Subari User Registration</title>
</head>
<body>
<form:form action="RegistrationResult" method="post" modelAttribute="userobj">
<h1 style="color: red;">Please Register as a Subari User Here!!</h1>
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
</html>