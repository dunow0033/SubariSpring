<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Admin - Delete User Page</title>
</head>
<body>
<h1 style="color: red;">Admin - Delete User Page</h1>

<form:form action="adminDeleteResult" method="post" modelAttribute="userobj">
<div class="center" id="deleteUserList">
	<p>
		<c:forEach items="${userList}" var="user">
			 <div style="display: inline-block; margin-bottom: 10px;">
            	<input type="checkbox" name="userId" value="${user.userid}" id="delete_${user.userid}" />
            	<label for="delete_${user.userid}">Delete</label><br>
            	<c:out value="${user.name}" />
        	</div>
		</c:forEach>
	</p>
	<input type="submit" value="Delete" />
</div>
</form:form>

</body>
</html>