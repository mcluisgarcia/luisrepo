<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hosiptal Doctors Zone</title>
</head>
<link href="WebContent/css/bootstrap.min.css" rel="stylesheet">
<link href="WebContent/css/grayscale.css" rel="stylesheet">
<link href="WebContent/css/jquery-ui.min.css" rel="stylesheet">
<link href="WebContent/css/jquery-ui.theme.css" rel="stylesheet">
<link href="WebContent/css/jquery-ui.structure.css" rel="stylesheet">

<body>
<script type="text/javascript" src="WebContent/js/jquery.easing.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="WebContent/js/bootstrap.min.js"></script>
<script type="text/javascript" src="WebContent/js/grayscale.js"></script>

<form>
  <div class="form-group">
  <div class="input-group">
  	<div class="input-group-addon">$</div>
    <label for="email">Email address</label>
    <input type="email" class="form-control" id="email" placeholder="Email">
  </div>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" id="password" placeholder="Password">
  </div>
  
  <div class="checkbox">
    <label>
      <input type="checkbox"> Check me out
    </label>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>
</body>
</html>
