<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Comment</title>
</head>
<body>
 <div id="form_errors">
  <c:if test="${errors != null }">
   The following errors have occurred:
   <c:forEach var="error" items="${errors }">
    <div class="form_error">* ${error }</div>
   </c:forEach>
  </c:if>
 </div>
 <form action="${pageContext.request.contextPath }/CreateComment">
  Comment Content:
  <textarea rows="2" cols="30" name="content" ></textarea>
  <input type ="submit" value ="Add Comment">
 </form>
</body>
</html>