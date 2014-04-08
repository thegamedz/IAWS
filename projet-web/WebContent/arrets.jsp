<%@page import="com.iaws.project.TisseoHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%!
	TisseoHandler tisseo = new TisseoHandler();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                      "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <style type="text/css"><%@include file="/css/stops.css" %></style>
      <script src="js/showstuff.js" type="text/javascript"></script>
   </head>
   <body>
   <%= tisseo.getArretsToStringV1(request.getParameter("idLigne")) %>
   </body>
</html>