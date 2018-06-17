<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "it.uniroma3.siw.model.Attivit�" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Attivit�</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>

	<ul>
		<%
		
		List<Attivit�> attivita = (List<Attivit�>)request.getAttribute("attivita");
		
		for(int i = 0; i < attivita.size(); i++) {
			String nome = attivita.get(i).getNome();
			Date data = attivita.get(i).getData();
			int orario = attivita.get(i).getOrario();
		%>
		<li>
			<div><% out.print(nome); %></div>
			<div><% out.print(data.toString()); %></div>
			<div><% out.print(orario); %></div>
		</li>	
		<% } %>
	</ul>

</body>
</html>