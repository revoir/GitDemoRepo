<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Country List</h3>

	<p style="color: red;">${errorString}</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>List of Languages</th>

		</tr>
		<c:forEach items="${LanguageList}" var="language">
			<tr>
				<td>${language}</td>

			</tr>
		</c:forEach>
	</table>
	<form method="POST"
		action="${pageContext.request.contextPath}/languageView">
		<table border="0">
			<tr>
				<td>Enter Language you want to search :</td>
				<td><input type="text" name="language_Name"
					value="${user.user_name}" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Search" /></td>
			</tr>
		</table>
	</form>



	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>