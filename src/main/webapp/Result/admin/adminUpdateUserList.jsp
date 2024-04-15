<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Admin - Update User Select Page</title>
</head>
<body>
<h1 style="color: red;">Admin - Update User Select Page</h1>

<form:form action="/adminUpdateUserForm" method="post">
<div class="center" id="updateUserList">
	<p>
		<c:forEach items="${userList}" var="user">
			 <div style="margin-bottom: 10px;">
            	<input type="radio" name="userid" value="${user.userid}" id="update_${user.userid}" />
            	<c:out value="${user.name}" />
        	</div>
		</c:forEach>
	</p>
	<input type="submit" value="Update" />
</div>
</form:form>

</body>
</html>