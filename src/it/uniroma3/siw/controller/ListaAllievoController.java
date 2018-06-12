package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.repository.AllievoJpaRepository;

@WebServlet("/listaAllievi")
public class ListaAllievoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nextPage = "/mostraListaAllievi.jsp";
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
		EntityManager em = emf.createEntityManager();
		
		AllievoJpaRepository repoAll = new AllievoJpaRepository(em);
		ArrayList<Allievo> allievi = (ArrayList<Allievo>)repoAll.findAll();
		request.setAttribute("allievi", allievi);
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
		
	}		
		
}