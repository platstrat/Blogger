<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
   	<table>
   		<tr>
			<th>ID</th>
			<th>Food</th>
			<th>Amount</th>
			<th>Preparation</th>
		</tr>
   		<c:forEach var="ingredient" items="${ingredients }">
   			<tr>
				<c:url value="/GetIngredient" var="get">
					<c:param name="id" value="${ingredient.id }" />
				</c:url>
				<td><a href="${get}">${ingredient.id }</a></td>
				<td>${ingredient.foodName }</td>
				<td>${ingredient.preparation }</td>
				<td>${ingredient.amount}</td>
			</tr>
   		</c:forEach>
   	</table>
</div>
<div>
		<h1>Add an ingredient:</h1>
		<c:url var="new" value="/NewIngredient" />
		<form method="post" action="${new }">
			<table style="text-align: left; vertical-align: top;">
				<c:if test="${errors != null }">
					<tr>
						<td rowspan="${errors.size() }">Errors:</td>
						<td>${errors[0] }</td>
					</tr>
					<c:forEach var="error" begin="1" items="${errors }">
						<tr>
							<td>${error }</td>
						</tr>
					</c:forEach>
				</c:if>
				<tr>
					<td>Ingredient Name<br /></td>
					<td><input type="text" name="foodName"></td>
				</tr>
				<tr>
					<td>Amount<br />&nbsp;</td>
					<td><input type="text" name="amount"></td>
				</tr>
				<tr>
					<td>Preparation<br /></td>
					<td><input type="text" name="preparation"><br /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input type="submit" value="New Ingredient"></td>
				</tr>
			</table>
		</form>
	</div>
	<a href="Home">Home</a>
</body>
</html>