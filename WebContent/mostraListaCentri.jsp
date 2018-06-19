<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "it.uniroma3.siw.model.Centro" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Lista Centri</title>
</head>
<body>

	<%
		
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}		
	
	%>

	<ul>
		<%
		
		ArrayList<Centro> centri = (ArrayList<Centro>)request.getAttribute("centri");
		
		if(centri == null){
			%>
			<li><% out.print("Non � stato ancora registrato nessun centro."); %></li>
			<%
			}else{
				for(int i = 0; i < centri.size(); i++) {
					String nome = centri.get(i).getNome();
					String indirizzo = centri.get(i).getIndirizzo();
					String email = centri.get(i).getEmail();
					String telefono = centri.get(i).getTelefono();
					int capienza = centri.get(i).getCapienza();
				%>
				<li>
					<div><% out.print(nome); %></div>
					<div><% out.print(indirizzo); %></div>
					<div><% out.print(email); %></div>
					<div><% out.print(telefono); %></div>
					<div><% out.print(capienza); %></div>
				</li>	
				<%
				}
			}
		%>
	</ul>

</body>
</html>