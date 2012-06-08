<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title="- View Blog">
	<div hidden="hidden">${blog.id }</div>
	<h1>${blog.name }</h1>
	<a href="/blogs/edit/${blog.id }"></a>
	Type: ${blog.type }
	Tags: ${blog.tags }
	Content:
	<textarea>${Content }</textarea>
	<div id="blog_comments">
		Comments:
		<c:url value="/ViewComment" var="get">
			<c:param name="id" value="${comment.id }" />
		</c:url>
		<c:forEach var="comment" items="${blog.comments}">
			<div class="blog_comment">
			<c:choose>
				<c:when test="${user == comment.blogger}">
					<a href="${get}">${comment.blogger } </a>
				</c:when>
				<c:otherwise>${comment.blogger }</c:otherwise>
			</c:choose>
			${comment.content }
			</div>
		</c:forEach>
	</div>
</layouts:layout_main>