<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.iaws.project.TisseoHandler"%>
<%!
	TisseoHandler tisseo = new TisseoHandler();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css"><%@include file="/css/lines.css" %></style>
<script src="js/formSubmit.js" type="text/javascript"></script>
</head>
<body>
<div class="top" >
	<h1 align="center"> Veuillez choisir une ligne</h1>
</div>
<%=  tisseo.getLinesToString()%>
</body>
</html>