<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title="- View Blog">
	<div hidden="hidden">
		${id }</div>
	<h1>${name }</h1>
	Type: ${type }
	Tags: ${tags }
	Rating: ${average}
	Content:
	<textarea>${Content }</textarea>
	<!-- <a href="/blogs/edit/${id }">Edit</a> -->
</layouts:layout_main>
<form method="post" action="/UpdateRating">
	Rate blog: 
	<select name="rating">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	<input type="submit" value="Add rating">
</form>
<layouts:all_comments user="${user}">
</layouts:all_comments>