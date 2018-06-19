<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "it.uniroma3.siw.model.Allievo" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Lista Allievi</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>
	
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="pannelloControllo.jsp">Pannello di controllo</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-list"></span> Attività<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="newAttivita.jsp">Inserisci attività</a></li>
						<li><a href="mostraListaAttivita.jsp">Lista attività</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-education"></span> Allievi<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="newAllievo.jsp">Inserisci allievo</a></li>
						<li><a href="mostraListaAllievi.jsp">Lista allievi</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-globe"></span> Centri<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="newCentro.jsp">Inserisci centro</a></li>
						<li><a href="mostraListaCentri.jsp">Lista attività</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						${username}</a></li>
			</ul>
		</div>
	</nav>

	<ul>
		<%
		
		ArrayList<Allievo> allievi = (ArrayList<Allievo>)request.getAttribute("allievi");
		
		if(allievi == null){
		%>
		<li><% out.print("Non è stato ancora registrato nessun allievo."); %></li>
		<%
		}else{
			for(int i = 0; i < allievi.size(); i++) {
				String nome = allievi.get(i).getNome();
				String cognome = allievi.get(i).getCognome();
				String email = allievi.get(i).getEmail();
				int telefono = allievi.get(i).getTelefono();
				Date dataDiNascita = allievi.get(i).getDataDiNascita();
				String luogoDiNascita = allievi.get(i).getLuogoDiNascita();
		%>
		<li>
			<div><% out.print(nome); %></div>
			<div><% out.print(cognome); %></div>
			<div><% out.print(email); %></div>
			<div><% out.print(telefono); %></div>
			<div><% out.print(dataDiNascita.toString()); %></div>
			<div><% out.print(luogoDiNascita); %></div>
		</li>	
		<% 
			}
		}
		%>
	</ul>

</body>
</html>