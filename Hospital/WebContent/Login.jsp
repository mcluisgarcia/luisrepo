<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login </title>
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
<script type="text/javascript" src="js/login.js"></script>

	<div class="container">
		<div class="row col-xs-12" >
			<div class="jumbotron" style=" background-repeat:no-repeat; background-image: url(img/downloads-bg.jpg); background-position: center; background-size:cover; padding: 10% ">
								
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 col-md-5 center-block">
				<div class="panel panel-default">
				    <div class="panel-heading">Login</div>
				
					<form method="get" name="formulario" action="login" class="panel-body">
						<div class="form-group">
							<label for="email">Email address</label> 
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							    </div>
								<input required="required" type="email" class="form-control" id="email" placeholder="Email" name="email">
							</div>
						</div>

						<div class="form-group">
							<label for="password">Password</label>
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
							    </div>
								<input required="required" type="password" class="form-control" id="password"
									placeholder="Password" name="password">
							</div>
						</div>
				</div>


				<div class="from-group">
					<a id="registro" class="btn btn-default" href="#" role="button">Registro</a>
					<input type="submit" value="Enviar" class="btn btn-default">
				</div>
				
				<!-- <button type="submit" class="btn btn-default">Submit</button> -->
				
				</form>
				</div>
				<div class="col-xs-12 col-md-7 center-block">
					<h1 align="center">Zona restringida</h1>
					<div >
						<img alt="" src="img/jumbotronbackground.jpg" >
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
<div id="dialog-form" title="Registro de Doctores">  
 
  <form method="get" name="agregarDr" action="login">
   
    	<div class="form-group">
      		<label for="nameD">Nombre</label>
      		<input placeholder="Nombre" type="text" required="required" name="nombreD" id="nombreD" class="form-control">
      	</div>
      	<div class="form-group">
      		<label for="emailD">Email</label>      
      		<input placeholder="aaaa@aaaa.com" type="email" required="required" name="emailD" id="emailD"  class="form-control">
      	</div>
      	<div class="form-group">
      		<label for="passwordD">Password</label>
      		<input placeholder="Password" type="password" required="required" name="passwordD" id="passwordD" class="form-control">
 		</div>
      	<div class="form-group">
      		<label for="edad">Edad</label>
      		<input placeholder="Edad" type="number" name="edad" required="required" id="edad" class="form-control">
      	</div>
      	<div class="form-group">
      		<label for="sexo">Sexo</label><br>
      			
				  <input type="radio" name="sexo" id="sexo" value="hombre" class="btn btn-default" checked="checked">Hombre
				  <input type="radio" name="sexo" id="sexo" value="mujer" class="btn btn-default">Mujer				  
				
      	</div>
      	<div class="form-group">
      		<label for="ife">Identificación</label>
      		<input placeholder="INE" required="required" type="text" name="ife" id="ife" value="" class="form-control">
      	</div>
      	<div class="form-group">
      		<label for="especialidad">Especialidad</label>
      		<input required="required" placeholder="Especialidad médica" type="text" name="especialidad" id="especialidad" class="form-control">      
      	</div>
      	
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" name="add" id="agregar" value="Agregar" tabindex="-1" style="position:absolute; top:-1000px">
      </form>
</div>
</body>
</html>