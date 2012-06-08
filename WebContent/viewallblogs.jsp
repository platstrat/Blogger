<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title=" - All Blogs">
	<c:forEach var="blog" items="${all_blogs }">
		<div class="blog_summary">
			(${blog.created }) ${blog.name } - ${block.comments } Comment(s) - Last Edited On: ${blog.edited }
		</div>
	</c:forEach>
</layouts:layout_main>