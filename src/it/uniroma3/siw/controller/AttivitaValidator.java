package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import it.uniroma3.siw.model.Centro;
import it.uniroma3.siw.service.CentroJpaRepository;

public class AttivitaValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String data, String orario, String centro) {
		
		boolean errori = false;
		
		try {
			new Long(centro);
		}
		catch(Exception e) {
			request.setAttribute("errCentroAttivita", "Devi indicare l'id numerico del centro.");
			errori = true;
		}
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNomeAttivita", "Il nome è obbligatorio.");
			errori = true;
		}
		if (data == null || data.equals("")) {
			request.setAttribute("errDataAttivita", "La data è obbligatoria.");
			errori = true;
		}else if(!dateIsValid(data)) {
			request.setAttribute("errDataAttivita", "Data non valida: le attività devono essere inserite con almeno un giorno di anticipo.");
			errori = true;
		}
		if (centro == null || centro.equals("")) {
			request.setAttribute("errCentroAttivita", "Il centro è obbligatorio.");
			errori = true;
		}else if(!centroIsValid(centro)) {
			request.setAttribute("errCentroAttivita", "Il centro indicato non esiste.");
			errori = true;
		}
		if (orario == null || orario.equals("")) {
			request.setAttribute("errOrarioAttivita", "L'orario è obbligatorio.");
			errori = true;
		}else if(!timeIsValid(orario)) {
			request.setAttribute("errOrarioAttivita", "Orario non valido: le attività devono essere inserite con almeno 24h di anticipo.");
			errori = true;
		}
		
		return errori;
		
	}
	
	public boolean dateIsValid(String data) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(data, dateFormatter);
		return date.isAfter(LocalDate.now());

	}
	
	public boolean timeIsValid(String orario) {

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime time = LocalTime.parse(orario, timeFormatter);
		return time.isAfter(LocalTime.now());
		
	}
	
	public boolean centroIsValid(String centroid) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
		EntityManager em = emf.createEntityManager();
		
		CentroJpaRepository repository = new CentroJpaRepository(em);
		
		Centro centro = repository.findByPrimaryKey(new Long(centroid));
		
		em.close();
		emf.close();
		
		return centro != null;
		
	}

}