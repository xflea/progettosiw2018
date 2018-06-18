package it.uniroma3.siw.controller;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.service.AllievoJpaRepository;

public class AllievoValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String cognome, String email, String telefono, String dataDiNascita, String luogoDiNascita) {
		
		boolean errori = false;
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNomeAllievo", "Il nome è obbligatorio.");
			errori = true;
		}
		if (cognome == null || cognome.equals("")) {
			request.setAttribute("errCognomeAllievo", "Il cognome è obbligattorio");
			errori = true;
		}
		if (email == null || email.equals("")) {
			request.setAttribute("errEmailAllievo", "L'email è obbligatoria.");
			errori = true;
		}
		if (telefono == null || telefono.equals("")) {
			request.setAttribute("errTelefonoAllievo", "Il telefono è obbligatorio.");
			errori = true;
		}
		if (dataDiNascita == null || dataDiNascita.equals("")) {
			request.setAttribute("errDataDiNascitaAllievo", "La data di nascita è obbligatoria.");
			errori = true;
		}else if(!dateIsValid(dataDiNascita)) {
			request.setAttribute("errDataDiNascitaAllievo", "Data non valida");
			errori = true;
		}
		if (luogoDiNascita == null || luogoDiNascita.equals("")) {
			request.setAttribute("errLuogoDiNascitaAllievo", "Il luogo di Nascita è obbligatorio.");
			errori = true;
		} 
		
		try {
			new Integer(telefono);
		}
		catch(NumberFormatException exception) {
			request.setAttribute("errTelefonoAllievo", "Il telefono deve essere un numero");
			errori = true;
		}
		
		if (!emailIsValid(email)) {
			request.setAttribute("errEmailAllievo", "Email non valida");
			errori = true;
		}
		
		if(emailAlreadyExists(email)) {
			request.setAttribute("errEmailAllievo", "L'email fornita è già stata assegnata ad un allievo");
			errori = true;
		}
		
		return errori;
		
	}
	
	public boolean emailIsValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	public boolean emailAlreadyExists(String email) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
		EntityManager em = emf.createEntityManager();
		
		AllievoJpaRepository repository = new AllievoJpaRepository(em);
		
		Allievo allievo = repository.findByEmail(email);
		
		em.close();
		emf.close();
		
		return allievo != null;
	}
	
	public boolean dateIsValid(String dataDiNascita) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dataDiNascita, formatter);
		return date.isBefore(LocalDate.now());
		
	}

}