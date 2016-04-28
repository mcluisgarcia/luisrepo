<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form method="get" name="formulario" action="res" >
<p>Ingresa el numero 1</p>
<input type="text" name="n1" >
<p>Ingresa el numero 2</p>
<input type="text" name="n2" >
<input type="submit" name="sumar" value="suma">
</form>
<h1>
<p>Suma = <%  out.println(request.getAttribute("resultado")); %></p>
</h1>
</body>
</html>