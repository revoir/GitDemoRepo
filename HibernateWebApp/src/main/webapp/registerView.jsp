<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Register Information</h3>


	<form method="POST"
		action="${pageContext.request.contextPath}/register">
		<table border="0">
			<tr>
				<td>Country Name</td>
				<td><input type="text" name="name" value="${user.name}" /></td>
			</tr>
			<tr>
				<td>Language</td>
				<td><input type="text" name="language" value="${user.language}" />
				</td>
			</tr>
			<tr>
				<td>Capital</td>
				<td><input type="text" name="city" value="${user.city}" /></td>
			</tr>
			<tr>
				<td>Sport</td>
				<td><input type="text" name="sport" value="${user.sport}" /></td>
			</tr>



			<tr>
				<td colspan="2"><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
