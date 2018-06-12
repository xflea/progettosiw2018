package it.uniroma3.siw.controller;

import java.io.IOException;

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

import it.uniroma3.siw.model.Centro;
import it.uniroma3.siw.repository.CentroJpaRepository;

@WebServlet("/richiestaCentro")
public class CentroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String nextPage = "";
		
		HttpSession session = request.getSession();
		
		CentroValidator validator = new CentroValidator();
		
		String nome = request.getParameter("nome").toUpperCase().trim();
		String indirizzo = request.getParameter("indirizzo").toUpperCase().trim();
		String email = request.getParameter("email").toUpperCase().trim();
		String telefono = request.getParameter(("telefono")).trim();
		String capienza = request.getParameter(("capienza")).trim();
		
		session.setAttribute("nome", nome);
		session.setAttribute("indirizzo", indirizzo);
		session.setAttribute("email", email);
		session.setAttribute("telefono", telefono);
		session.setAttribute("capienza", capienza);
		
		if(!validator.validate(request, nome, indirizzo, email, telefono, capienza)) {
			
			Centro centro = new Centro();
			centro.setNome(nome);
			centro.setIndirizzo(indirizzo);
			centro.setEmail(email);
			centro.setTelefono(telefono);
			centro.setCapienza(new Integer(capienza));
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
			EntityManager em = emf.createEntityManager();
			
			CentroJpaRepository repoCentro = new CentroJpaRepository(em);
			repoCentro.save(centro);
			
			session.setAttribute("centro", centro);
			request.setAttribute("successCentro", "Centro inserito con successo!");
			
		}
		
		nextPage = "/newCentro.jsp";
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return;
		
	}
}

