<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Nuovo Centro</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>

	<center>
	<div><h1>Inserisci nuovo centro</h1></div>
	<form action="richiestaCentro" method="post">
		<div>Nome: <br/><input type="text" name="nomeCentro" value="${nomeCentro}"/></div>
		<div><font color="red">${ errNomeCentro }</font></div><br/><br/>
		<div>Indirizzo: <br/><input type="text" name="indirizzoCentro" value="${indirizzoCentro}"/></div>
		<div><font color="red">${ errIndirizzoCentro }</font></div><br/><br/>
		<div>Email: <br/><input type="text" name="emailCentro" value="${emailCentro}"/></div>
		<div><font color="red">${ errEmailCentro }</font></div><br/><br/>
		<div>Telefono: <br/><input type="text" name="telefonoCentro" value="${telefonoCentro}"/></div>
		<div><font color="red">${ errTelefonoCentro }</font></div><br/><br/>
		<div>Capienza: <br/><input type="text" name="capienzaCentro" value="${capienzaCentro}"/></div>
		<div><font color="red">${ errCapienzaCentro }</font></div>
		<div><font color="green">${ successCentro}</font></div><br/><br/>
		<div><input type="submit" class="bottone" name="sumbit" value="Invia" /></div>
	</form>
	</center>

</body>
</html>