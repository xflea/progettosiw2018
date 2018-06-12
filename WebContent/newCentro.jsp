<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuovo Centro</title>
</head>
<body>

	<form action="richiestaCentro" method="post">
		<div>Nome: <input type="text" name="nome" value="${nome}"/></div>
		<div><font color="red">${ errNomeCentro }</font></div>
		<div>Indirizzo: <input type="text" name="indirizzo" value="${indirizzo}"/></div>
		<div><font color="red">${ errIndirizzoCentro }</font></div>
		<div>Email: <input type="text" name="email" value="${email}"/></div>
		<div><font color="red">${ errEmailCentro }</font></div>
		<div>Telefono: <input type="text" name="telefono" value="${telefono}"/></div>
		<div><font color="red">${ errTelefonoCentro }</font></div>
		<div>Capienza: <input type="text" name="capienza" value="${capienza}"/></div>
		<div><font color="red">${ errCapienzaCentro }</font></div>
		<div><font color="green">${ successCentro}</font></div>
		<div><input type="submit" name="sumbit" value="Invia" /></div>
	</form>

</body>
</html>