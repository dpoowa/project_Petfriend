<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<tiles:insertAttribute name="title" />
</title>
<link rel="stylesheet" href="/static/css/adminlayout.css">
</head>
<body>
<div id="container">
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="sidebar-left">
		<tiles:insertAttribute name="sidebar" />
	</div>
	<div id="content">
		<tiles:insertAttribute name="body" />
	</div>
</div>
</body>
</html>