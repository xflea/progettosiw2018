package it.uniroma3.siw.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServlet;

public class SessionController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void contextInitialized(ServletContextEvent e) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
		e.getServletContext().setAttribute("emf", emf);
	}

	public void contextDestroyed(ServletContextEvent e) {
		EntityManagerFactory emf = (EntityManagerFactory)e.getServletContext().getAttribute("emf");
		emf.close();
	}

}