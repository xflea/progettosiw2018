package it.uniroma3.siw.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
import javax.servlet.http.HttpSession;

import it.uniroma3.siw.model.Attivit�;
import it.uniroma3.siw.service.AttivitaJpaRepository;
import it.uniroma3.siw.service.CentroJpaRepository;

@WebServlet("/richiestaAttivita")
public class AttivitaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String nextPage = "";
		
		HttpSession session = request.getSession();
		
		AttivitaValidator validator = new AttivitaValidator();
		
		String nome = request.getParameter("nome").toUpperCase().trim();
		String data = request.getParameter("data").trim();
		String orario = request.getParameter("orario").toUpperCase().trim();
		String centro = request.getParameter("centro").trim();
		
		session.setAttribute("nome", nome);
		session.setAttribute("data", data);
		session.setAttribute("orario", orario);
		session.setAttribute("centro", centro);
		
		if(!validator.validate(request, nome, data, orario, centro)) {
			
			Attivit� attivita = new Attivit�();
			attivita.setNome(nome);
				
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(data, dateFormatter);
			attivita.setData(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime time = LocalTime.parse(orario, timeFormatter);
			attivita.setOrario(Date.from(time.atDate(date).atZone(ZoneId.systemDefault()).toInstant()));
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
			EntityManager em = emf.createEntityManager();
			
			CentroJpaRepository repoCentro = new CentroJpaRepository(em);
			attivita.setCentro(repoCentro.findByPrimaryKey(new Long(centro)));

			AttivitaJpaRepository repoAtt = new AttivitaJpaRepository(em);
			repoAtt.save(attivita);
			
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

