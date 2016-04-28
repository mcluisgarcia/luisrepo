<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sesiones</title>
</head>
<body>
<script type="text/javascript" src="jquery.js"></script>
<form method="get" name="formulario" action="res" >
<p>Ingresa un color favorito</p>
<input type="text" name="colorF" >
<input type="submit" name="" value="Aceptar">
</form>
<h1>
<p>Sesion = <%  out.println(request.getAttribute("sesionId")); %></p>

<p>Lista de Colores favoritos: </p>
<ul>
<% if( request.getAttribute("ColoresF") !=null ){ ArrayList<String> lista = (ArrayList)request.getAttribute("ColoresF"); %>
<% for(String s : lista){  out.println("<li>Color favorito anterior: "+s+"</li>"); 	} }%>
	
</ul> 

<form method="get" name="salir" action="res" >

<input type="submit" value="Salir" name="limpia" >
</form>


</h1>
</body>
</html>