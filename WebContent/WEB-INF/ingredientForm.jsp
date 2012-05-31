<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="EditIngredient">
<input type="hidden" name="id" value="${ingredient.id }">
<table>
	<tr>
		<td>Ingredient Name</td>
		<td><input type="text" name="name" value="${ingredient.foodName }"></td>
	</tr>
	<tr>
		<td>Amount</td>
		<td><input type="text" name="amount" value="${ingredient.amount}"></td>
	</tr>
	<tr>
		<td>Preparation</td>
		<td><input type="text" name="preparation" value="${ingredient.preparation}"></td>
	</tr>
	<tr>
		
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Change Ingredient" name="change">
			<input type="submit" value="Delete Ingredient" name="delete">
		</td>
	</tr>
</table>
</form>
</body>
</html>