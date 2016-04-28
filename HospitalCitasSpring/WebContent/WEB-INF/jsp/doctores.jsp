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
	<spring:url value="/dwr/engine.js" var="engine" />
	<spring:url value="/dwr/interface/Fechas.js" var="fechas" />
	<spring:url value="/dwr/util.js" var="util" />

	<script src="${jqueryjs}"></script>
	<script src="${jqueryjstheme}"></script>
	<script src="${bootsjs}"></script>
	<script src="${gridlocalejs}"></script>
	<script src="${jqgridjs}"></script>
	<script src="${moment}"></script>
	<script src="${timepick}"></script>
	<script src="${doctores}"></script>
	<script src="${engine}"></script>
	<script src="${fechas}"></script>
	<script src="${util}"></script>

	<div class="container">
		<div class="row col-xs-12">
			<div class="jumbotron"
				style="background-repeat: no-repeat; background-image: url(resources/default/img/downloads-bg.jpg); background-position: center; background-size: cover; padding: 10%">
				<h1></h1>
			</div>
		</div>
		<div class="page-header">
			<h3>
				<img class="img-circle" style="width: 20%; height: 30%" alt=""
					src="resources/default/img/3d_medical_symbol_new__clear_background__by_benbobby-d58szba.png">
				${msg}
			</h3>

		</div>
		<div class="row">
			<div class="col-xs-12 col-md-2"><input type="button" value="Registrar Horarios" id="sHorarios"
				 /></div> 
			<div class="col-xs-12 col-md-1"><input type="button" value="Buscar" id="buscarCitas" /></div>
		    <div class="col-xs-12 col-md-9"><span class="pull-right"><a href="doctores/salir/" class="btn btn-primary">Cerrar Sesion</a></div>
		</div>
		<hr/>
		
		<div id="tablaDoctores" class="row col-xs-12">
			
			<form:form method="post" modelAttribute="combobox">
				<table>
					<tbody>
						<tr>
							<td>
								<ul>
									<form:label path="valor">Seleccione la Semana</form:label>
									<form:select path="valor" items="${semanas}" itemValue="clave"
										itemLabel="valor" onchange="setNumSem(this.value)" />
								</ul>
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
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


		<script type="text/javascript">
			function init() {
				dwr.util.useLoadingMessage();
				Tabs.init('tabList', 'tabContents');
				dwr.util.setValue("filter", "");
				addSingleRow("peoplebody", "Please enter a search filter");
			}

			var peopleCache = [];
			var lastFilter = "";

			function fillTable(people) {
				var filter = dwr.util.getValue("filter");
				var pattern = new RegExp("(" + filter + ")", "i");
				var filtered = [];
				for (i = 0; i < people.length; i++) {
					if (pattern.test(people[i].nombre)) {
						filtered.push(people[i]);
					}
				}
				dwr.util.removeAllRows("peoplebody");
				if (filtered.length == 0) {
					addSingleRow("peoplebody", "No matches");

				} else {
					dwr.util.addRows("peoplebody", filtered, [
							function(person) {
								return person.nombre.replace(pattern,
										"<a><span class='highlight'></span></a>");
							}, function(person) {
								return person.fechaCita;
							}, function(person) {
								return "<a href='#' onclick='showDetalles(this)' id='"+person.idcita+"'>"+person.idcita+"</a>";
							} ], {
						escapeHtml : false
					});
				}
				
				peopleCache = people;
			}
			function filterChanged(doctor) {
				$("#tablaDetalle").show();
				var filter = dwr.util.getValue("filter");
				if (filter.length == 0) {
					dwr.util.removeAllRows("peoplebody");
					addSingleRow("peoplebody",
							"Ingrese el nombre de su paciente");
				} else {
					Fechas.findCitas(doctor, filter, fillTable);
				}
				lastFilter = filter;
			}

			function addSingleRow(id, message) {
				dwr.util.addRows(id, [ 1 ], [ function(data) {
					console.log(id);
					return message;
				} ], {
					cellCreator : function() {
						var td = document.createElement("td");
						td.setAttribute("colspan", 3);

						return td;
					}
				});
			}
		</script>
		<p>
		<div id="busquedaP" class="row col-xs-12 col-md-5">
			<div class="input-group ">
				<span class="input-group-addon glyphicon glyphicon-search"
					id="basic-addon1"></span> <input type="text" class="form-control "
					placeholder="Buscar" aria-describedby="basic-addon1"
					onkeyup="filterChanged('${doctor.email}');" id="filter">
			</div>

			</p>
			<div class="panel panel-default" id="tablaDetalle">
				<div class="panel-heading">Citas de Pacientes</div>
				<div class="panel-body">

					<table border="1" class="table table-striped">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Detalles Cita</th>
								<th>ID</th>
							</tr>
						</thead>
						<tbody id="peoplebody">

						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<div id="res"></div>
	<div id="msgDlg" title="">

		<span class="ui-icon ui-icon-circle-check"
			style="float: left; margin: 0 7px 50px 0;">
			<div id="info"></div>
		</span>

	</div>
</body>
</html>