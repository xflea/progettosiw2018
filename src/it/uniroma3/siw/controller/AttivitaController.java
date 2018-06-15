package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma3.siw.model.Attivit�;
import it.uniroma3.siw.service.AttivitaJpaRepository;

@WebServlet("/richiestaAttivita")
public class AttivitaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String nextPage = "";
		
		HttpSession session = request.getSession();
		
		AttivitaValidator validator = new AttivitaValidator();
		
		String nome = request.getParameter("nome").toUpperCase().trim();
		String data = request.getParameter("data").toUpperCase().trim();
		String orario = request.getParameter("orario").toUpperCase().trim();
		
		session.setAttribute("nome", nome);
		session.setAttribute("data", data);
		session.setAttribute("orario", orario);
		
		if(!validator.validate(request, nome, data, orario)) {
			
			Attivit� attivita = new Attivit�();
			attivita.setNome(nome);
			attivita.setData(data);
			attivita.setOrario(orario);
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
			EntityManager em = emf.createEntityManager();
			
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			AttivitaJpaRepository repoAtt = new AttivitaJpaRepository(em);
			repoAtt.save(attivita);
			tx.commit();
			em.close();
			emf.close();
			
			session.setAttribute("attivita", attivita);
			request.setAttribute("successAttivita", "Attivit� inserita con successo!");
						
		}
		
		nextPage = "/newAttivita.jsp";
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
		
	}
}

