<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title=" - Edit User">
	<div hidden="hidden">
		${user.bloggerId }</div>
	<h1>User information:</h1>
	User Name: ${user.username }
	Password: ${user.password }
	Email: ${user.email }
	<a href="/users/edit/${user.bloggerId }">Edit</a>
</layouts:layout_main>