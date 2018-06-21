<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Nuovo Centro</title>
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
						<li><a href="mostraListaCentri.jsp">Lista centri</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						${username}</a></li>
			</ul>
		</div>
	</nav>

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