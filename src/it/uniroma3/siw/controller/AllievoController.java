package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.repository.AllievoJpaRepository;

@WebServlet("/richiestaAllievo")
public class AllievoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String nextPage = "";
		
		HttpSession session = request.getSession();
		
		AllievoValidator validator = new AllievoValidator();
		
		String nome = request.getParameter("nome").toUpperCase().trim();
		
		session.setAttribute("nome", nome);

		if(!validator.validate(request, nome)) {
			
			Allievo allievo = new Allievo();
			allievo.setNome(nome);
			
			session.setAttribute("allievo", allievo);
			request.setAttribute("successAttivita", "Attività inserita con successo!");
			
		}
		
		nextPage = "/newAllievo.jsp";
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
		
	}
}

