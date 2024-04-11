<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Subari Login Page</title>
</head>
<body>
<form:form action="LoginResult" method="post" modelAttribute="userobj">
<h1 style="color: red;">Welcome to Subari!!</h1>
<table>
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
		<td><input type="submit" value="Login" />
		<a href="/register"><input style="float: right;" type="submit" value="Register" /></a></td>
	</tr>
</table>

</form:form>

</body>
</html>