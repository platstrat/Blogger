<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ attribute name="title" required="true" %>
<title>Captain's Log${title}</title>
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="../js/site.js"></script>
</head>
<body>
	<div id="header">
		<div id="menu-left">
			<div class="menuitem-left">
				<!-- Link to home -->
				Captain's Log
			</div>
			<div class="menuitem-left">
				Blogs
			</div>
			<div class="menuitem-left">
				
			</div>
			<div class="menuitem-right">
				Username
			</div>
			<div class="menuitem-right">
				<!-- Create link/servlet -->
				Register/Logout
			</div>
		</div>
		<div id="menu-right">
		</div>
	</div>
	<div id="content" id="body">
		<jsp:doBody />
	</div>
	<div id="footer">
		<div class="menuitem-left">
			&copy; Neumont University 2012
		</div>
	</div>
</body>
</html>