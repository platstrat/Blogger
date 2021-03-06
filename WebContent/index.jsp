<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title="- Home">
</layouts:layout_main>
	<div id="recent_blogs">
		<c:forEach var="blog" items="${blogs }">
		<!-- Change to be the last 5-10 created blogs -->
			<div class="blog_summary">
				Created on:${blog.created}
				<h3>Blog:</h3>${blog.name } 
 				<div>${block.comments }</div>
				<br />Comment(s) - Last Edited On: ${blog.edited }
			</div>
		</c:forEach>
	</div>
	<div id="top_blogs">
		<c:forEach var="blog" items="${blogs }">
		<!-- Change to be the last 5-10 created blogs -->
			<div class="blog_summary">
				Created on:${blog.created}
				<h3>Blog:</h3>${blog.name } 
 				<div>${block.comments }</div>
				<br />Comment(s) - Last Edited On: ${blog.edited }
			</div>
		</c:forEach>
	</div>
