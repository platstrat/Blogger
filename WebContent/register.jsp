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
	<form method="POST" action="${pageContext.request.contextPath }/Register">
		<div class="form_left">
			Username:</div>
		<div class="form_right">
			<input type="text" name="username" /></div>
		<div class="form_left">
			Password:</div>
		<div class="form_right">
			<input type="password" name="password" /></div>
		<div class="form_left">
			Email:</div>
		<div class="form_right">
			<input type="text" name="email" /></div>
		<div class="form_submit">
			<input type="submit" /></div>
	</form>
</layouts:layout_main>