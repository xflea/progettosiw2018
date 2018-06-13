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
		<div>Nome: <input type="text" name="nome" value="${nome}"/></div>
		<div><font color="red">${ errNome }</font></div>
		<div>Cognome: <input type="text" name="cognome" value="${cognome}"/></div>
		<div><font color="red">${ errCognome }</font></div>
		<div>Email: <input type="text" name="email" value="${email}"/></div>
		<div><font color="red">${ errEmail }</font></div>
		<div>Telefono: <input type="text" name="telefono" value="${telefono}"/></div>
		<div><font color="red">${ errTelefono }</font></div>
		<div>Data di nascita: <input type="date" name="data di nascita" value="${dataDiNascita}"/></div>
		<div><font color="red">${ errDataDiNascita }</font></div>
		<div>Luogo di nascita: <input type="text" name="luogo di nascita" value="${luogoDiNascita}"/></div>
		<div><font color="red">${ errLuogoDiNascita }</font></div>
		<div><font color="green">${ successAllievo }</font></div>
		<div><input type="submit" name="sumbit" value="Invia" /></div>
	</form>

</body>
</html>