<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="richiestaLogin" method="post">
		<div>Nome: <input type="text" name="username"/></div>
		<div>Password: <input type="password" name="password"/></div>
		<div><font color="red">${ errorLogin }</font></div>
		<div><input type="submit" name="submit" value="Invia"/></div>
	</form>

</body>
</html>