<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin -- Menu of User Options</title>
<style>
	.center {
		margin: auto;
		text-align: center;
		margin-bottom: 20px;
	}
</style>
<script>
window.onload = function() {
	document.getElementById("userList").style.display = "none";
};

function toggleUserList() {
	var selectElement = document.getElementById("optionSelect");
	var userListElement = document.getElementById("userList");
	
	if(selectElement.value === "adminViewUsers") {
		userListElement.style.display = "block";
	} else {
		userListElement.style.display = "none";
	}
}
</script>
</head>
<body>

<h1 style="margin:auto; text-align: center; color: red; font-style: italic; font-weight: bold;">Menu of User Options</h1>

<div class="center">
	<select id="optionSelect" onchange="toggleUserList();window.location.href=this.value;">
		<option>Choose an Option</option>
		<option value="adminViewUsers">View Users</option>
		<option value="/adminCreateUser">Create User</option>
		<option value="/adminUpdateUser">Update User</option>
		<option value="/adminDeleteUser">Delete User</option>
	</select>
</div>

<div class="center" id="userList">
	<p>
		<c:forEach items="${userList}" var="user">
			<c:out value="${user}" /><br>
		</c:forEach>
	</p>
</div>

</body>
</html>