<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="user" required="true" %>
<div id="comments">
	<c:forEach var="comment" items="${blog.comments}">
	<c:url value="/ViewComment" var="get">
		<c:param name="id" value="${comment.id }" />
	</c:url>
	Commented by:
		<c:choose>
			<c:when test="${user == comment.blogger}">
			<a href="${get}">${comment.blogger } </a></c:when>
			<c:otherwise>${comment.blogger }</c:otherwise>
		</c:choose>	 
		<br />${comment.content }
		<br />
	</c:forEach>
</div>