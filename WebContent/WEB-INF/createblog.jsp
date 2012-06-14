<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title=" - Create Blog">
	<div id="form_errors">
		<c:if test="${errors != null }">
			The following errors have occurred:
			<c:forEach var="error" items="${errors }">
				<div class="form_error">* ${error }</div>
			</c:forEach>
		</c:if>
	</div>
	<form method="POST" action="${pageContext.request.contextPath }/CreateBlog">
		<c:if test="${name == null }">
			<div class="form_error">REQUIRED</div></c:if>
		<div class="form_left">
			Name:</div>
		<div class="form_right">
			<input type="text" name="name" />
			</div>
		<c:if test="${type == null}">
			<div class="form_error">REQUIRED</div></c:if>
		<div class="form_left">
			Type:</div>
		<div class="form_right">
			<select name="type">
    <option value="Art">Art</option>
    <option value="Business">Business</option>
    <option value="Education">Education</option>
    <option value="Electronics">Electronics</option>
    <option value="Film">Film</option>
      <option value="Health">Health</option>
      <option value="Life">Life</option>
      <option value="Games">Games</option>
      <option value="Literature">Literature</option>
      <option value="Money">Money</option>
      <option value="Politics">Politics</option>
      <option value="Romance">Romance</option>
      <option value="Sports">Sports</option>
      <option value="Technology">Technology</option>
      <option value="Other">Other</option>
    </select></div>
		<div class="form_left">
			Tags:</div>
		<div class="form_right">
			<input type="text" name="tags" /></div>
		<div class="form_note">
			Separate tags with a ','</div>
		<div class="form_left">
			Contents:</div>
		<div class="form_right">
			<textarea rows="2" cols="30" name="content" ></textarea></div>
		<div class="form_submit">
			<input type="submit" value="Create Blog" /></div>
	</form>
</layouts:layout_main>