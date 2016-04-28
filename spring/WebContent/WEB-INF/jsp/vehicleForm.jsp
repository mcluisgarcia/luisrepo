<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form method="post" modelAttribute="vehicle">
	<form:errors path="*" cssClass="error"/>
	<table>
		<tr>
			<td>
				id
			</td>
			<td><form:input path="vehicleId"/></td>
		</tr>
		<tr>
			<td>
				color
			</td>
			<td><form:input path="color"/></td>
		</tr>
		<tr>
			<td>wheel</td>
			<td><form:input path="wheel"/></td>
		</tr>
		<tr>
			<td>seat</td>
			<td><form:input path="seat"/></td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>