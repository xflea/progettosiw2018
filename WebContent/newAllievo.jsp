<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuovo allievo</title>
</head>
<body>

	<form action="richiestaAllievo" method="post">
		<div>Nome: <input type="text" name="nomeAllievo" value="${nomeAllievo}"/></div>
		<div><font color="red">${ errNomeAllievo }</font></div>
		<div>Cognome: <input type="text" name="cognomeAllievo" value="${cognomeAllievo}"/></div>
		<div><font color="red">${ errCognomeAllievo }</font></div>
		<div>Email: <input type="text" name="emailAllievo" value="${emailAllievo}"/></div>
		<div><font color="red">${ errEmailAllievo }</font></div>
		<div>Telefono: <input type="text" name="telefonoAllievo" value="${telefonoAllievo}"/></div>
		<div><font color="red">${ errTelefonoAllievo }</font></div>
		<div>Data di nascita: <input type="date" name="dataAllievo" value="${dataDiNascitaAllievo}"/></div>
		<div><font color="red">${ errDataDiNascitaAllievo }</font></div>
		<div>Luogo di nascita: <input type="text" name="luogoAllievo" value="${luogoDiNascitaAllievo}"/></div>
		<div><font color="red">${ errLuogoDiNascitaAllievo }</font></div>
		<div><font color="green">${ successAllievo }</font></div>
		<div><input type="submit" name="sumbit" value="Invia" /></div>
	</form>

</body>
</html>