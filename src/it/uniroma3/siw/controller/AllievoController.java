package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.service.AllievoJpaRepository;

@WebServlet("/richiestaAllievo")
public class AllievoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String nextPage = "";
		
		HttpSession session = request.getSession();
		
		AllievoValidator validator = new AllievoValidator();
		
		String nome = request.getParameter("nomeAllievo").toUpperCase().trim();
		String cognome = request.getParameter("cognomeAllievo").toUpperCase().trim();
		String email = request.getParameter("emailAllievo").trim();
		String telefono = request.getParameter("telefonoAllievo").trim();
		String dataDiNascita = request.getParameter("dataAllievo").trim();
		String luogoDiNascita = request.getParameter("luogoAllievo").toUpperCase().trim();
		
		session.setAttribute("nomeAllievo", nome);
		session.setAttribute("cognomeAllievo", cognome);
		session.setAttribute("emailAllievo", email);
		session.setAttribute("telefonoAllievo", telefono);
		session.setAttribute("dataDiNascitaAllievo", dataDiNascita);
		session.setAttribute("luogoDiNascitaAllievo", luogoDiNascita);

		if(!validator.validate(request, nome, cognome, email, telefono, dataDiNascita, luogoDiNascita)) {
			
			Allievo allievo = new Allievo();
			allievo.setNome(nome);
			allievo.setCognome(cognome);
			allievo.setEmail(email);
			allievo.setTelefono(new Long(telefono));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dataDiNascita, formatter);
			allievo.setDataDiNascita(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
			allievo.setLuogoDiNascita(luogoDiNascita);
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
			EntityManager em = emf.createEntityManager();
			AllievoJpaRepository repoAll = new AllievoJpaRepository(em);
			repoAll.save(allievo);
			
			em.close();
			emf.close();
			
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

