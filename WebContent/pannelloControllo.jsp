<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pannello di controllo</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>

	<h1>Benvenuto!</h1>
	<div><a href="newAttivita.jsp">Inserisci nuova attività</a></div>
	<div><a href="listaAttivita">Lista delle attività</a></div>
	<div><a href="newAllievo.jsp">Aggiungi allievo</a></div>
	<div><a href="listaAllievi">Lista degli allievi</a></div>
	<div><a href="newCentro.jsp">Aggiungi centro</a></div>
	<div><a href="listaCentri">Lista dei centri</a></div>

</body>
</html>