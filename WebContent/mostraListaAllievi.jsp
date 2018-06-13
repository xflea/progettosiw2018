<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "it.uniroma3.siw.model.Allievo" %>
<%@ page import = "java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Allievi</title>
</head>
<body>

	<ul>
		<%
		
		List<Allievo> allievi = (List<Allievo>)request.getAttribute("allievi");
		
		for(int i = 0; i < allievi.size(); i++) {
			String nome = allievi.get(i).getNome();
			String cognome = allievi.get(i).getCognome();
			String email = allievi.get(i).getEmail();
			String telefono = allievi.get(i).getTelefono();
			String dataDiNascita = allievi.get(i).getDataDiNascita();
			String luogoDiNascita = allievi.get(i).getLuogoDiNascita();
		%>
		<li>
			<div><% out.print(nome); %></div>
			<div><% out.print(cognome); %></div>
			<div><% out.print(email); %></div>
			<div><% out.print(telefono); %></div>
			<div><% out.print(dataDiNascita); %></div>
			<div><% out.print(luogoDiNascita); %></div>
		</li>	
		<% } %>
	</ul>

</body>
</html>