<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Suma de numeros</title>
</head>
<body>
	<form method="post">
		Numero 1 <input type="text" name="n1" value="${n1}" />
		Numero 2 <input type="text" name="n2" value="${n2}" />
		<input type="submit" value="Query" />
	</form>

<h1>${suma}</h1>
	
</body>
</html>