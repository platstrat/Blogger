<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Recipes</title>
</head>
<body>
	<div>
    	<table>
    		<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Directions</th>
				<th>Ingredients</th>
				<th>Images</th>
			</tr>
    		<c:forEach var="recipe" items="${recipes }">
    			<tr>
					<c:url value="/GetRecipe" var="get">
						<c:param name="id" value="${recipe.id }" />
					</c:url>
					<td><a href="${get}">${recipe.id }</a></td>
					<td>${recipe.recipeName }</td>
					<td>${recipe.description }</td>
					<td>${recipe.directions }</td>
					<td><c:forEach var="ingredient" items="${recipe.ingredients }">
						<li>${ingredient.foodName }</li>
					</c:forEach></td>
					<td><c:forEach var="image" items="${recipe.images }">
						<c:url value="/images" var="images">
							<c:param name="id" value="${image.id }" />
						</c:url>
						<li><a href="${images}">${image.id }</a></li>
					</c:forEach></td>
				</tr>
    		</c:forEach>
    	</table>
    </div>
    <div>
		<h1>Add a recipe:</h1>
		<c:url var="new" value="/NewRecipe" />
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
					<td>Recipe Name<br /></td>
					<td><input type="text" name="recipeName"></td>
				</tr>
				<tr>
					<td>Description<br />&nbsp;</td>
					<td><input type="text" name="description"></td>
				</tr>
				<tr>
					<td>Directions<br /></td>
					<td><input type="text" name="directions"><br /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input type="submit" value="New Recipe"></td>
				</tr>
			</table>
		</form>
	</div>
	<a href="ingredients">Ingredient List</a>
</body>
</html>