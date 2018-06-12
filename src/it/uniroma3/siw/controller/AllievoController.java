package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma3.siw.model.Allievo;

@WebServlet("/richiestaAllievo")
public class AllievoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String nextPage = "";
		
		HttpSession session = request.getSession();
		
		AllievoValidator validator = new AllievoValidator();
		
		String nome = request.getParameter("nome").toUpperCase().trim();
		String cognome = request.getParameter("cognome").toUpperCase().trim();
		String email = request.getParameter("email").trim();
		String telefono = request.getParameter("telefono").trim();
		String dataDiNascita = request.getParameter("dataDiNascita").trim();
		String luogoDiNascita = request.getParameter("luogoDiNascita").toUpperCase().trim();
		
		session.setAttribute("nome", nome);

		if(!validator.validate(request, nome, cognome, email, telefono, dataDiNascita, luogoDiNascita)) {
			
			Allievo allievo = new Allievo();
			allievo.setNome(nome);
			allievo.setCognome(cognome);
			allievo.setEmail(email);
			allievo.setTelefono(new Long(telefono));
			allievo.setDataDiNascita(dataDiNascita);
			allievo.setLuogoDiNascita(luogoDiNascita);
			
			session.setAttribute("allievo", allievo);
			request.setAttribute("successAllievo", "Allievo inserito con successo!");
			
		}
		
		nextPage = "/newAllievo.jsp";
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
		
	}
}

