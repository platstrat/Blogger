<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recipe Form</title>
</head>
<body>
<form method="post" action="EditRecipe">
<input type="hidden" name="id" value="${recipe.id }">
<table>
	<tr>
		<td>Recipe Name</td>
		<td><input type="text" name="name" value="${recipe.recipeName }"></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><input type="text" name="description" value="${recipe.description}"></td>
	</tr>
	<tr>
		<td>Directions</td>
		<td><input type="text" name="directions" value="${recipe.directions }"></td>
	</tr>
	<tr>
		<td>Ingredients</td>
		<td>
		
		<c:forEach items="${ingredients }" var="ing">
			<c:set var="contains" value="false" />
			<c:forEach var="item" items="${recipe.ingredients}">
			  <c:if test="${item.id == ing.id}">
			    <c:set var="contains" value="true" />
			  </c:if>
			</c:forEach>
			<c:if test="${contains }">
				<input type="checkbox" value="${ing.id }" name="recipeIngredients" checked="checked">${ing.foodName }<br>
			</c:if>
			<c:if test="${!contains }">
				<input type="checkbox" value="${ing.id }" name="recipeIngredients">${ing.foodName }<br>
			</c:if>
		</c:forEach>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Change Recipe" name="change">
			<input type="submit" value="Delete Recipe" name="delete">
		</td>
	</tr>
</table>
</form>
<c:forEach items="${recipe.images }" var="image">
	<c:url value="/DeleteImage" var="delete">
		<c:param name="id" value="${image.id }" />
		<c:param name="recipeId" value="${recipe.id }"/>
	</c:url>
	<a href="${delete}">Delete Image ${image.id }</a>
</c:forEach>

<form method="post" action="AddImage" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${recipe.id }">
	<input type="hidden" name="name" value="${recipe.recipeName }">
	<input type="hidden" name="description" value="${recipe.description }">
	<input type="hidden" name="directions" value="${recipe.directions }">
	Uploaded File:<input type="file" name="fileName"><br>
    <input type="submit" value="Upload this file">
</form>
</body>
</html>