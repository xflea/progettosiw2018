<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Nuovo allievo</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>

	<center>
	<div><h1>Inserisci nuovo allievo</h1></div>
	<form action="richiestaAllievo" method="post">
		<div>Nome: <br/><input type="text" name="nomeAllievo" value="${nomeAllievo}"/></div>
		<div><font color="red">${ errNomeAllievo }</font></div><br/><br/>
		<div>Cognome: <br/><input type="text" name="cognomeAllievo" value="${cognomeAllievo}"/></div>
		<div><font color="red">${ errCognomeAllievo }</font></div><br/><br/>
		<div>Email: <br/><input type="text" name="emailAllievo" value="${emailAllievo}"/></div>
		<div><font color="red">${ errEmailAllievo }</font></div><br/><br/>
		<div>Telefono: <br/><input type="text" name="telefonoAllievo" value="${telefonoAllievo}"/></div>
		<div><font color="red">${ errTelefonoAllievo }</font></div><br/><br/>
		<div>Data di nascita: <br/><input type="date" name="dataAllievo" value="${dataDiNascitaAllievo}"/></div>
		<div><font color="red">${ errDataDiNascitaAllievo }</font></div><br/><br/>
		<div>Luogo di nascita: <br/><input type="text" name="luogoAllievo" value="${luogoDiNascitaAllievo}"/></div>
		<div><font color="red">${ errLuogoDiNascitaAllievo }</font></div>
		<div><font color="green">${ successAllievo }</font></div><br/><br/>
		<div><input type="submit" class="bottone" name="submit" value="Invia" /></div>
	</form>
	</center>

</body>
</html>