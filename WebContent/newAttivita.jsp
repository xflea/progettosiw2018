<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuova attività</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>


	<form action="richiestaAttivita" method="post">
		<div>Nome: <input type="text" name="nome" value="${nome}"/></div>
		<div><font color="red">${ errNomeAttivita }</font></div>
		<div>Data <input type="date" name="data" value="${data}"/></div>
		<div><font color="red">${ errDataAttivita }</font></div>
		<div>Orario <input type="number" name="orario" min ="1" max="24" value="${orario}"/></div>
		<div><font color="red">${ errOrarioAttivita }</font></div>
		<div><font color="green">${ successAttivita}</font></div>
		<div><input type="submit" name="sumbit" value="Invia" /></div>
	</form>

</body>
</html>