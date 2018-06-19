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

import it.uniroma3.siw.model.Centro;
import it.uniroma3.siw.service.CentroJpaRepository;

@WebServlet("/listaCentri")
public class ListaCentriController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nextPage = "/mostraListaCentri.jsp";
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
		EntityManager em = emf.createEntityManager();
		
		CentroJpaRepository repoCen = new CentroJpaRepository(em);
		ArrayList<Centro> centri = (ArrayList<Centro>)repoCen.findAll();
		request.setAttribute("centri", centri);
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
		
	}		
		
}