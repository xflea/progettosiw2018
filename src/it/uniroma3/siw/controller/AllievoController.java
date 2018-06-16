package it.uniroma3.siw.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
		session.setAttribute("dataAllievo", dataDiNascita);
		session.setAttribute("luogoAllievo", luogoDiNascita);

		if(!validator.validate(request, nome, cognome, email, telefono, dataDiNascita, luogoDiNascita)) {
			
			Allievo allievo = new Allievo();
			allievo.setNome(nome);
			allievo.setCognome(cognome);
			allievo.setEmail(email);
			allievo.setTelefono(new Integer(telefono));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			Date date2;
			try {
				date2 = formatter.parse(dataDiNascita);
				allievo.setDataDiNascita(date2);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
			allievo.setLuogoDiNascita(luogoDiNascita);
			
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();
			AllievoJpaRepository repoAll = new AllievoJpaRepository(em);
			repoAll.save(allievo);
			
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

