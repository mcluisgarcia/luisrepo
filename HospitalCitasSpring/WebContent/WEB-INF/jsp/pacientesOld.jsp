<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
<title>Citas Pacientes</title>
</head>
<spring:url value="/resources/default/css/bootstrap.css" var="boots" />
<spring:url value="/resources/default/css/jquery-ui.min.css" var="jquery" />
<spring:url value="/resources/default/css/jquery-ui.theme.css" var="jquerytheme" />
<link href="${boots}" rel="stylesheet" />
<link href="${jquery}" rel="stylesheet" />
<link href="${jquerytheme}" rel="stylesheet" />

<body>
<spring:url value="/resources/default/js/jquery.js" var="jqueryjs" />
<spring:url value="/resources/default/js/jquery-ui.min.js" var="jqueryjstheme" />
<spring:url value="/resources/default/js/bootstrap.min.js" var="bootsjs" />
<spring:url value="/resources/default/js/login.js" var="loginjs" />
<script src="${jqueryjs}"></script>
<script src="${jqueryjstheme}"></script>
<script src="${bootsjs}"></script>
<script src="${loginjs}"></script>



<div class="container">
		<div class="row col-xs-12" >
			<div class="jumbotron" style=" background-repeat:no-repeat; background-image: url(resources/default/img/downloads-bg.jpg); background-position: center; background-size:cover; padding: 10% ">
								
			</div>
		</div>
		
		<div class="page-header">
			<h1>Programación de Citas</h1>
		</div>
		
		<div class="panel panel panel-default row col-xs-12 col-md-7">
			<div class="panel-heading">
				<h3><img class="img-circle" style="width: 10%; height: 20%" alt=""
					src="resources/default/img/3d_medical_symbol_new__clear_background__by_benbobby-d58szba.png">Datos del paciente</h3></div>
			
			<form:form method="post" modelAttribute="citas" class="panel-body">
				<form:errors path="*" cssClass="error" element="msg" />
				
					<div class="form-group">
						<label for="nombre">Paciente</label> 
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						    </div>
							<form:input path="nombre" value="${nombre}" required="required" type="text" class="form-control" id="nombre" placeholder="Nombre" name="nombre"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="edad">Edad</label> 
						<div class="input-group">
							<form:input path="edad" maxlength="2" max="99" value="${edad}" required="required" type="number" class="form-control" id="edad" placeholder="Edad" name="edad"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="sexo">Sexo</label> 
						<div class="input-group">
							<form:radiobuttons items="${sexos}" path="sexo" required="required"  id="sexo" name="sexo"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="telefono">Telefono</label> 
						<div class="input-group">
							<form:input path="telefono" value="${telefono}" required="required" type="tel" class="form-control" id="telefono" placeholder="__-__-__" name="telefono"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="sintomas">Sintomas</label> 
						<div class="input-group">
							<form:textarea path="sintomas" rows="10" cols="100" value="${sintomas}" required="required" class="form-control" id="sintomas" placeholder="Principales Sintomas" name="sintomas"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="doctor">Doctor</label> 
						<div class="input-group">
							<form:select path="doctorEmail" required="required" onchange="setDoctor(this)">
								<form:option value="NONE" label="--- Seleccione un Doctor ---" />
					  			<form:options items="${doctores}" />
							</form:select>
						</div>
					</div>
					<script>
						function setDoctor(docs){
							$.ajax({
								  type: "POST",
							      url: "pacientes/"+docs.value+"/",
							      //data: "doctor="+docs.value,
							      success: function(response,data){
							    	  
							          $("#fechaCita").html(response);
							          console.log(response);
							    	 
							      },
							      error: function(error){
							    	  $("#info").text("No se logró gaurdar la información "+error);
							    	  $("#msgDlg").dialog("open");
							      }
								
							});
						}
					</script>
					<div class="form-group">
						<label for="fecha">Fecha Cita</label> 
						<div class="input-group">
							<form:select path="fechaCita" required="required" id="fechaCita">
								<form:option value="" label="--- Seleccione una Fecha ---" />
					  			<form:options items="${horarios}" />
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<input type="submit" value="Guardar" />
					</div>
			</form:form>
		
		</div>
</div>
</body>
</html>