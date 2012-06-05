<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title=" - Edit Blog">
	<div id="form_errors">
		<c:if test="${errors != null }">
			The following errors have occurred:
			<c:forEach var="error" items="${errors }">
				<div class="form_error">* ${error }</div>
			</c:forEach>
		</c:if>
	</div>
	<form method="POST" action="/UpdateBlog">
		<c:if test="${name == null }">
			<div class="form_error">REQUIRED</div></c:if>
		<div class="form_left">
			Name:</div>
		<div class="form_right">
			<input type="text" name="name" value="${name }" />
			</div>
		<c:if test="${type == null}">
			<div class="form_error">REQUIRED</div></c:if>
		<div class="form_left">
			Type:</div>
		<div class="form_right">
			<select name="type">
  				<option value=null>...</option>
  				<c:forEach var="option" items="${options }">
					<option value="${option }">${option }</option>
				</c:forEach></select></div>
		<div class="form_left">
			Tags:</div>
		<div class="form_right">
			<input type="text" name="tags" value="${tags }" /></div>
		<div class="form_note">
			Separate tags with a ','</div>
		<div class="form_left">
			Contents:</div>
		<div class="form_right">
			<input type="text" multiple="multiple" name="content" value="${content }" /></div>
		<div class="form_submit">
			<input type="submit" value="CreateBlog" /></div>
	</form>
</layouts:layout_main>