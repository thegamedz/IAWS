<%@page import="com.iaws.project.TisseoHandler"%>
<%@page import="com.iaws.project.VeloArret"%>
<%@page import="com.iaws.project.JcdecoHandler"%>
<%@page import="com.iaws.project.RestWSConsumer"%>
<%@page import="com.iaws.project.parametres.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.iaws.project.Ligne"%>
<%@page import="com.iaws.project.Arret"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.iaws.project.DatabaseHandler" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%!
	TisseoHandler tisseo = new TisseoHandler();
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
    <script src="js/showstuff.js" type="text/javascript"></script>
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
						<h2><i class="icon-user"></i> Arrets</h2>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>Nom</th>	
								  <th>Destination</th>
								  <th>ProchainDepart</th>
							  </tr>
						  </thead>   
						  <tbody>
							
	<%
	String idLigne = request.getParameter("idLigne");
	if (!tisseo.listeLignes.containsKey(idLigne))
	{
		out.println("Ligne innexistante!");
	} else {
	Ligne ligne = tisseo.listeLignes.get(idLigne);
	
	
	/* récupérer la liste des arrets de la ligne */
	ArrayList<Arret> arrets = ligne.getArrets();
	for (int i = 0 ; i < arrets.size(); i++)
	{
		Arret arret = arrets.get(i);
	out.println("<tr>");
		/* Nom */
		out.println("<td>");
			out.println("<h3 onclick=\"showStuff('time"+i+"');\">");
			out.println(arret.getNom());
			out.println("</h3>");
		out.println("</td>");
		
		/* Destination */
		out.println("<td>");
			out.println("<h3 onclick=\"showStuff('time"+i+"');\">");
			out.println(arret.getNomDestination(idLigne));
			out.println("</h3>");
		out.println("</td>");
		
		/* Prochaine arrivee */
		out.println("<td>");
			out.println("<p id=\"time"+i+"\" style=\"display: none;\" >"
					+tisseo.getTimeOfStopPoint(arret.getId())+"</p>");
		out.println("</td>");
	out.println("</tr>");
	}
	
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