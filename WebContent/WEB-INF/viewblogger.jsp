<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title=" - Edit User">
	<div hidden="hidden">
		${user.bloggerId }</div>
	<h1>User information:</h1>
	Display Name: ${user.username }<br/>
	Email: ${user.email }
	Blogs:
	<c:forEach var="blog" items="${user.blogs }">
	<a href="${pageContext.request.contextPath }/ViewBlog/${blog.id }">${blog.name }</a>
	</c:forEach>
	<a href="${pageContext.request.contextPath }/CreateBlog">New Blog</a>
	</layouts:layout_main>
	<form method="POST" action="${pageContext.request.contextPath}/UpdateBlogger/${user.bloggerId}">
	<c:choose>
		<c:when test="${user.username == null}"> 
			<c:if test="${error != null }">
				 Errors: ${error}
			</c:if>
		</c:when>
	<c:otherwise>
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
		<input type="submit" value="Update User" name="change"/>
		<input type="submit" value="Delete User" name="delete"/>
	</div>
	</c:otherwise>
	</c:choose>
</form>