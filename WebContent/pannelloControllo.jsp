<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Pannello di controllo</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>

	<center>
		<div><h1>Pannello di controllo</h1></div>
		<div><a href="newAttivita.jsp">Inserisci nuova attività</a></div>
		<div><a href="listaAttivita">Lista delle attività</a></div>
		<hr/>
		<div><a href="newAllievo.jsp">Aggiungi allievo</a></div>
		<div><a href="listaAllievi">Lista degli allievi</a></div>
		<hr/>
		<div><a href="newCentro.jsp">Aggiungi centro</a></div>
		<div><a href="listaCentri">Lista dei centri</a></div>
	</center>

</body>
</html>