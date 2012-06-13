<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ attribute name="title" required="true" %>
<title>Captain's Log${title}</title>
<base href="${pageContext.request.contextPath }" />
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="../js/site.js"></script>
</head>
<body>
	<div id="header">
		<div id="menu-left">
			<div class="menuitem-left">
				<a href="">Captain's Log</a>
			</div>
			<div class="menuitem-left">
				<!-- View all blogs page -->
				<a href="${pageContext.request.contextPath }/ShowAllBlogs">Blogs</a>
			</div>
			<div class="menuitem-right">
				<c:choose>
  					<c:when test="${user == null}">
						<a href="${pageContext.request.contextPath }/login.jsp">Login</a></c:when>
  					<c:otherwise>
  						${user.username}</c:otherwise>
				</c:choose>
			</div>
			<div class="menuitem-right">
				<c:choose>
  					<c:when test="${user == null}">
						<a href="${pageContext.request.contextPath }/register.jsp">Register</a></c:when>
  					<c:otherwise>
  						<a href="${pageContext.request.contextPath }/Logout">Logout</a></c:otherwise>
				</c:choose>
			</div>
		</div>
		<div id="menu-right">
		</div>
	</div>
	<div id="content">
		<jsp:doBody />
	</div>
	<div id="footer">
		<div class="menuitem-left">
			&copy; Neumont University 2012
		</div>
	</div>
</body>
</html>