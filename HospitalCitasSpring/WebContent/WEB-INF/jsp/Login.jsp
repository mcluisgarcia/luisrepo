<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Login</title>
</head>
<spring:url value="/resources/default/css/bootstrap.css" var="boots" />
<spring:url value="/resources/default/css/jquery-ui.min.css"
	var="jquery" />
<spring:url value="/resources/default/css/jquery-ui.theme.css"
	var="jquerytheme" />
<link href="${boots}" rel="stylesheet" />
<link href="${jquery}" rel="stylesheet" />
<link href="${jquerytheme}" rel="stylesheet" />

<body>
	<spring:url value="/resources/default/js/jquery.js" var="jqueryjs" />
	<spring:url value="/resources/default/js/jquery-ui.min.js"
		var="jqueryjstheme" />
	<spring:url value="/resources/default/js/bootstrap.min.js"
		var="bootsjs" />
	<spring:url value="/resources/default/js/login.js" var="loginjs" />
	<script src="${jqueryjs}"></script>
	<script src="${jqueryjstheme}"></script>
	<script src="${bootsjs}"></script>
	<script src="${loginjs}"></script>
	<%
		String command = request.getParameter("deny");
			if(command!="" && command!=null){
	%>
	<script>
		$(function() {
			$("#msgDlg").dialog('open');
		});
	</script>
	<%
		}
	%>

	<div class="container">
		<div class="row col-xs-12">
			<div class="jumbotron"
				style="background-repeat: no-repeat; background-image: url(resources/default/img/downloads-bg.jpg); background-position: center; background-size: cover; padding: 10%">

			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 col-md-5 center-block">
				<div class="panel panel-default">
					<div class="panel-heading">Login</div>

					<form:form method="post" modelAttribute="login" class="panel-body">
						<form:errors path="*" cssClass="error" element="msg" />
						<div class="form-group">
							<label for="email">Email address</label>
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</div>
								<form:input path="email" value="${email}" required="required"
									type="email" class="form-control" id="email"
									placeholder="Email" name="email" />
							</div>
						</div>

						<div class="form-group">
							<label for="password">Password</label>
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
								</div>
								<form:input path="password" value="${password}"
									required="required" type="password" class="form-control"
									id="password" placeholder="Password" name="password" />
							</div>
						</div>


						<div class="from-group">
							<a id="citas" class="btn btn-default" href="pacientes"
								role="button">Citas</a> <a id="registro" class="btn btn-default"
								href="#" role="button">Registro</a> <input type="submit"
								value="Enviar" class="btn btn-default">
						</div>

					</form:form>
				</div>
			</div>
			<div class="col-xs-12 col-md-7 center-block">
				<h1 align="center">Zona restringida</h1>
				<div>
					<img alt="" src="resources/default/img/jumbotronbackground.jpg">
				</div>
			</div>
		</div>
	</div>

	<div id="dialog-form" title="Registro de Doctores">

		<form method="post" id="frmDoc" name="agregarDr" action="login#">

			<div class="form-group">
				<label for="nameD">Nombre</label> <input placeholder="Nombre"
					type="text" required="required" name="nombre" id="nombre"
					class="form-control">
			</div>
			<div class="form-group">
				<label for="emailD">Email</label> <input placeholder="aaaa@aaaa.com"
					type="email" required="required" name="email" id="email"
					class="form-control">
			</div>
			<div class="form-group">
				<label for="passwordD">Password</label> <input
					placeholder="Password" type="password" required="required"
					name="password" id="password" class="form-control">
			</div>
			<div class="form-group">
				<label for="edad">Edad</label> <input placeholder="Edad"
					type="number" name="edad" required="required" id="edad"
					class="form-control">
			</div>
			<div class="form-group">
				<label for="sexo">Sexo</label><br> <input type="radio"
					name="sexo" id="sexo" value="hombre" class="btn btn-default"
					checked="checked">Hombre <input type="radio" name="sexo"
					id="sexo" value="mujer" class="btn btn-default">Mujer

			</div>
			<div class="form-group">
				<label for="identificacion">Identificación</label> <input
					placeholder="INE" required="required" type="text"
					name="identificacion" id="identificacion" value=""
					class="form-control">
			</div>
			<div class="form-group">
				<label for="especialidad">Especialidad</label> <input
					required="required" placeholder="Especialidad médica" type="text"
					name="especialidad" id="especialidad" class="form-control">
			</div>

			<!-- Allow form submission with keyboard without duplicating the dialog button -->
			<input type="submit" name="add" id="registrarDr" value="Agregar"
				tabindex="-1" style="position: absolute; top: -1000px">
		</form>

		<div id="msgDlg" title="Login incorrecto"
			class="ui-state-error ui-corner-all">
			<p>
				<span class="ui-icon ui-icon-circle-check"
					style="float: left; margin: 0 7px 50px 0;"> </span>
			<div id="info">Usuario y/o password incorrecto</div>
			</p>

		</div>


	</div>

	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">"${noticias}"</div>
	</nav>
</body>
</html>