<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Login</title>
</head>
<body>

	<center>
		<form action="richiestaLogin" method="post">
			<div>Nome: <br/><input type="text" name="username"/></div><br/>
			<div>Password: <br/><input type="password" name="password"/></div><br/>
			<div><font color="red">${ errorLogin }</font></div><br/>
			<div><input type="submit" name="submit" value="Invia"/></div>
		</form>
	</center>

</body>
</html>