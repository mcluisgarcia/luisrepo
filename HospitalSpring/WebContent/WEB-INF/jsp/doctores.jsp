<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
</head>
<spring:url value="/resources/default/css/bootstrap.css" var="boots" />
<spring:url value="/resources/default/css/jquery-ui.min.css"
	var="jquery" />
<spring:url value="/resources/default/css/jquery-ui.theme.css"
	var="jquerytheme" />
<spring:url value="/resources/default/css/ui.jqgrid-bootstrap.css"
	var="jqgrid" />
<spring:url value="/resources/default/css/ui.jqgrid-bootstrap-ui.css"
	var="jqgridui" />
<link href="${boots}" rel="stylesheet" />
<link href="${jquery}" rel="stylesheet" />
<link href="${jquerytheme}" rel="stylesheet" />
<link href="${jqgrid}" rel="stylesheet" />
<link href="${jqgridui}" rel="stylesheet" />

<body>
	<spring:url value="/resources/default/js/jquery.js" var="jqueryjs" />
	<spring:url value="/resources/default/js/jquery-ui.min.js"
		var="jqueryjstheme" />
	<spring:url value="/resources/default/js/bootstrap.min.js"
		var="bootsjs" />
	<spring:url value="/resources/default/js/grid.locale-es.js"
		var="gridlocalejs" />
	<spring:url value="/resources/default/js/jquery.jqGrid.min.js"
		var="jqgridjs" />
	<spring:url value="/resources/default/js/moment-with-locales.js"
		var="moment" />
	<spring:url value="/resources/default/js/timepicker.js" var="timepick" />
	<spring:url value="/resources/default/js/doctores.js" var="doctores" />

	<script src="${jqueryjs}"></script>
	<script src="${jqueryjstheme}"></script>
	<script src="${bootsjs}"></script>
	<script src="${gridlocalejs}"></script>
	<script src="${jqgridjs}"></script>
	<script src="${moment}"></script>
	<script src="${timepick}"></script>
	<script src="${doctores}"></script>

	<div class="container">
		<div class="row col-xs-12">
			<div class="jumbotron"
				style="background-repeat: no-repeat; background-image: url(resources/default/img/downloads-bg.jpg); background-position: center; background-size: cover; padding: 10%">
				<h1></h1>
			</div>
		</div>
		<div>
			<h3>
				<img class="img-circle" style="width: 20%; height: 30%" alt=""
					src="resources/default/img/3d_medical_symbol_new__clear_background__by_benbobby-d58szba.png">
				${msg}
			</h3>

		</div>
		<br />
		
		<form:form method="POST" modelAttribute="registros">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<form:select path="semana" items="${semanas}"
						itemValue="id" itemLabel="semana" />
			
		</form:form>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Registro de horarios</h3>
			</div>
			<div class="panel-body">
				<div>
					<table id="jqGrid"></table>
					<div id="jqGridPager"></div>
				</div>
				<input type="button" value="Guardar" onclick="getSelectedRows()">
			</div>
		</div>
		
	</div>

</body>
</html>