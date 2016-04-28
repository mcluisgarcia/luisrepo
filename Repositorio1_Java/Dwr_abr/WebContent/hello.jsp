<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Uso de DWR</p>
	<script type='text/javascript' src='/Dwr_abr/dwr/engine.js'></script>
	<script type='text/javascript' src='/Dwr_abr/dwr/interface/Demo.js'></script>
	<script type='text/javascript' src='/Dwr_abr/dwr/util.js'></script>
	<script type="text/javascript">
		function update(){
			var name=dwr.util.getValue("demoName");
			Demo.sayHello(name,function(data){
				dwr.util.setValue("demoReply",data);
			});
		}
	</script>

	<p>
		Name: <input type="text" id="demoName"/> <input type="submit"
			value="Send" onclick="update()"/> <br> Reply: <span
			id="demoReply"></span>
	</p>
</body>
</html>