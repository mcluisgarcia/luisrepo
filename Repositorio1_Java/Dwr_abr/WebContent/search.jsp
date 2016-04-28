<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Busqueda de personas</p>
	<script type='text/javascript' src='/Dwr_abr/dwr/engine.js'></script>
	<script type='text/javascript' src='/Dwr_abr/dwr/interface/People.js'></script>
	<script type='text/javascript' src='/Dwr_abr/dwr/util.js'></script>

	<script type="text/javascript">
		function init() {
			dwr.util.useLoadingMessage();
			Tabs.init("tabList", "tabContents");
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
				if (pattern.test(people[i].name)) {
					filtered.push(people[i]);
				}
			}
			dwr.util.removeAllRows("peoplebody");
			if (filtered.length == 0) {
				addSingleRow("peoplebody", "No matches");
			} else {
				dwr.util.addRows("peoplebody", filtered, [
						function(person) {
							return person.name.replace(pattern,
									"<span class='highlight'>$1</span>");
						}, function(person) {
							return "$" + person.age;
						}, function(person) {
							return person.address;
						} ], {
					escapeHtml : false
				});

			}
			peopleCache = people;
		}
		function filterChanged() {
			var filter = dwr.util.getValue("filter");
			if (filter.length == 0) {
				dwr.util.removeAllRows("peoplebody");
				addSingleRow("peoplebody", "Please enter a search filter");
			} else {
				if (filter.charAt(0) == lastFilter.charAt(0)) {
					fillTable(peopleCache);
				} else {
					People.getMatchingFromLargeCrowd(filter.charAt(0),
							fillTable);
					//People.getMatchingFromLargeCrowd(filter,
					//		fillTable);
					//console.log(filter);
				}
			}
			lastFilter = filter;
		}
		function addSingleRow(id, message) {
			dwr.util.addRows(id, [ i ], [ function(data) {
				return message;
			} ], {
				cellCreator : function() {
					var td = document.createElement("td");
					td.setAttribute("colspan", 3);
					return td;
				}
			})
		}
	</script>

	<p>
		Search: <input id="filter" onkeyup="filterChanged();" />
	</p>
	<table border="1" class="rowed grey">
		<thead>
			<tr>
				<th>Name</th>
				<th>Age</th>
				<th>Address</th>
			</tr>
		</thead>
		<tbody id="peoplebody">
		</tbody>
	</table>
</body>
</html>