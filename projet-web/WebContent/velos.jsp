<%@page import="com.iaws.project.VeloArret"%>
<%@page import="com.iaws.project.JcdecoHandler"%>
<%@page import="com.iaws.project.RestWSConsumer"%>
<%@page import="com.iaws.project.parametres.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%! RestWSConsumer rest = new RestWSConsumer(); 
	JcdecoHandler jc = new JcdecoHandler();
	VeloArret v_arret = new VeloArret();
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
						<h2><i class="icon-user"></i> Velo Arrêt</h2>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>Nom</th>
								  <th>Adresse</th>
								  <th>Vélo_Dispo</th>
							  </tr>
						  </thead>   
						  <tbody>
							
	<%
	for(int i=0;i<jc.getVelosArret().size();i++){
		v_arret=jc.getVelosArret().get(i);
		out.println("<tr>");
		out.println("<td>"+v_arret.getName()+"</td>");
		out.println("<td class=\"center\">"+v_arret.getAdress()+"</td>");
		out.println("<td class=\"center\">");
		out.println("<span class=\"label label-success\">"+v_arret.getAvailable_bikes()+"</span>");
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