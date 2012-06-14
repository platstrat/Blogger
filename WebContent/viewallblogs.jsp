<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title=" - All Blogs">
	<c:forEach var="blog" items="${blogs }">
		<div class="blog_summary">
			Name: ${blog.name } 
			Blog - Last Edited On: ${blog.edited }
			<layouts:all_comments blog="${blog}">
			</layouts:all_comments>
		</div>
	</c:forEach>
</layouts:layout_main>