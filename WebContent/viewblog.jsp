<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layouts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<layouts:layout_main title="- View Blog">
	<div hidden="hidden">
		${id }</div>
	<h1>${name }</h1>
	Type: ${type }
	Tags: ${tags }
	Content:
	<textarea>${Content }</textarea>
	<!-- <a href="/blogs/edit/${id }">Edit</a> -->
</layouts:layout_main>