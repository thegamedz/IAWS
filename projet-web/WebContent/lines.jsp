<%@page import="com.iaws.project.TisseoHandler"%>
<%@page import="com.iaws.project.VeloArret"%>
<%@page import="com.iaws.project.JcdecoHandler"%>
<%@page import="com.iaws.project.RestWSConsumer"%>
<%@page import="com.iaws.project.parametres.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.iaws.project.Ligne"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.iaws.project.DatabaseHandler" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%!
	TisseoHandler tisseo = new TisseoHandler();
%>
<%
	DatabaseHandler.createDB();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>IAWS Web Services</title>
    <style type="text/css">
    <%@include file="/css/main.css"%>
    <%@include file="/css/bootstrap-cerulean.css"%>
    <%@include file="/css/bootstrap-responsive.css"%>
	<%@include file="/css/charisma-app.css"%>
	<%@include file="/css/jquery-ui-1.8.21.custom.css"%>
	<%@include file='/css/fullcalendar.css'%>
	
    </style>
    <script src="js/formSubmit.js" type="text/javascript"></script>
</head>
<body>

<div id="header">
    <div id="logo">
     Gassama & Koumad
    </div>
    <div id="title">Tisseo and JCDeco <span>API</span>
    </div>
</div>
   <div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i> Lignes</h2>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable" id="existing">
						  <thead>
							  <tr>
								  <th>	<font color="red">Unlikes</font>
								  		<font color="yellow">/</font>
								  		<font color="green">Likes</font>
								  </th>	
								  <th>Nom</th>
								  <th>Terminus</th>
								  <th>Evaluer</th>
							  </tr>
						  </thead>   
						  <tbody>
							
	<%
	String idLigne = request.getParameter("idLigne");
	String type = request.getParameter("type");
	if ((idLigne != null) && (!idLigne.isEmpty()) && (type != null) && (!type.isEmpty())) {
		if (type.equals("like")) {
			DatabaseHandler.likeunlike(idLigne, true);
		} else if (type.equals("dislike")) {
			DatabaseHandler.likeunlike(idLigne, false);
		}
	}
	Iterator<Entry<String, Ligne>> it = tisseo.listeLignes.entrySet().iterator();
	log("Starting getting lines");
	/*
	 * onclick="post_to_url('arrets.jsp', { id:'lm'} , { submit: 'post' } );"
	 */
	while(it.hasNext())
	{	
		Map.Entry<String, Ligne> pairs = (Map.Entry<String, Ligne>) it.next();
		// Récupérer les likes et unlikes d'une ligne et dresser une note
		long likes = DatabaseHandler.getNbLikes(pairs.getKey());
		long unlikes = DatabaseHandler.getNbDislikes(pairs.getKey());
		
		out.println("<tr>");
		
		/* Note */
		out.println("<td><font color='green'>"+likes+"</font color='yellow'> / </font>"
				+ " <font color='red'>"+unlikes+"</font></td>");
		
		/* Nom */
		out.println("<td>");
			out.println("<h3>");
			out.println(pairs.getValue().getNomCourtDeLaLigne());
			out.println("</h3>");
		out.println("</td>");
		
		/* Terminus */
		out.println("<td>");
				out.println("<p onclick=\"post_to_url('arrets.jsp', { idLigne:'" 
									+ pairs.getValue().getId()+"'} , { submit: 'post' } );\">");
				out.println(pairs.getValue().getNomDeLaLigne()+"</p>");
				out.println("</p>");
		out.println("</td>");
		
		/* Like Unlike */
		out.println("<td>");

				out.println("<span><input type='button' value=\"Like\" onclick=\"post_to_url('lines.jsp', { idLigne:'" 
						+ pairs.getValue().getId()+"', type:'like'} , { submit: 'post' } );\"/>");
				out.println("<span><input type='button' value=\"Unlike\" onclick=\"post_to_url('lines.jsp', { idLigne:'" 
						+ pairs.getValue().getId()+"', type:'dislike'} , { submit: 'post' } );\"/>");
		out.println("</td>");
		out.println("</tr>");
	}
    %>

						</tbody>
					</table>
				</div>
			</div>
</div>
 <div id="footer">
        Iaws Project
    </div>

</body>
</html>