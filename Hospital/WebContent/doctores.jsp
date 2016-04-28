<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido </title>
</head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--  <link href="css/grayscale.css" rel="stylesheet">-->
<link href="css/jquery-ui.min.css" rel="stylesheet">
<link href="css/jquery-ui.theme.css" rel="stylesheet">
<link href="css/jquery-ui.structure.css" rel="stylesheet">

<body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- <script type="text/javascript" src="js/grayscale.js"></script>-->


<div class="container">
		<div class="row col-xs-12" >
			<div class="jumbotron" style=" background-repeat:no-repeat; background-image: url(img/downloads-bg.jpg); background-position: center; background-size:cover; padding: 10% ">						
				<h1 ></h1>							
			</div>
		</div>
		<div>
			<h1><img class="img-circle" style="width: 10%; height: 10%" alt="" src="img/3d_medical_symbol_new__clear_background__by_benbobby-d58szba.png">
		<% out.print(request.getAttribute("resultado")); %></h1>
			
		</div>
			
		
</div>

</body>
</html>