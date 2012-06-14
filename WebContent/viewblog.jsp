<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title="- View Blog">
	<div hidden="hidden">
		${blog.id }</div>
	<h1>${blog.name }</h1>
	Type: ${blog.type }
	Tags: <c:forEach var="tag" items="${blog.tags }">
	${tag }
	</c:forEach>
	Rating: ${blog.average}
	Content:
	<textarea>${blog.content }</textarea>
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
<c:choose>
	<c:when test="${blog.blogger.username == user.username}"> 
	<form method="post" action ="/UpdateBlog">
		<c:param name="id" value="${blog.id }" />
		<div class="form_left">Name:</div>
		<div class="form_right">
			<input type="text" name="name" /> 
		</div> 
		<div class="form_left">Type:</div>
		<div class="form_right"> 
			<select name="type">
			<option value=null>...</option> 
			<c:forEach var="option" items="${options }">
				<option value="${option }">${option }</option>\ 
				</c:forEach>
			</select></div>
		<div class="form_left">Tags:</div>
		<div class="form_right">
			<input type="text" name="tags" />
		</div>
		<div class="form_note">Separate tags with a ','</div>
		<div class="form_left"> Contents:</div>
		<div class="form_right">
			<input type="text" multiple="multiple" name="content" /></div> 
		<div class="form_submit"><input type="submit" value="Update Blog" /></div>
		<input type="submit" value="Change Blog" name="change">
    <input type="submit" value="Delete Blog" name="delete">
	</form>
	</c:when>
</c:choose>


