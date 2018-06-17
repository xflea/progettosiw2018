package it.uniroma3.siw.controller;

import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import it.uniroma3.siw.model.Centro;
import it.uniroma3.siw.service.CentroJpaRepository;

public class CentroValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String indirizzo, String email, String telefono, String capienza) {
		
		boolean errori = false;
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNomeCentro", "Campo obbligatorio.");
			errori = true;
		}
		if (indirizzo == null || indirizzo.equals("")) {
			request.setAttribute("errIndirizzoCentro", "Campo obbligatorio.");
			errori = true;
		}
		if (email == null || indirizzo.equals("")) {
			request.setAttribute("errEmailCentro", "Campo obbligatorio.");
			errori = true;
		}
		if (telefono == null || telefono.equals("")) {
			request.setAttribute("errTelefonoCentro", "Campo obbligatorio.");
			errori = true;
		}
		if (capienza == null || capienza.equals("")) {
			request.setAttribute("errCapienzaCentro", "Campo obbligatorio.");
			errori = true;
		}
		
		
		
		if (!emailIsValid(email)) {
			request.setAttribute("errEmailCentro", "Email non valida.");
			errori = true;
		}
		
		if (emailAlreadyExists(email)) {
			request.setAttribute("errEmailCentro", "L'email fornita è già stata associata ad un centro.");
			errori = true;
		}
		
		try {
		    Integer.parseInt(telefono);
		}
		catch (NumberFormatException ex) {
			request.setAttribute("errTelefonoCentro", "Il telefono deve essere un numero.");
			errori = true;
		}
		
		try {
		    Integer.parseInt(capienza);
		}
		catch (NumberFormatException ex) {
			request.setAttribute("errCapienzaCentro", "La capienza deve essere un numero.");
			errori = true;
		}
		
		return errori;
		
	}
	
	public static boolean emailIsValid(String email) {
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
		
		CentroJpaRepository repository = new CentroJpaRepository(em);
		
		Centro centro = repository.findByEmail(email);
		
		em.close();
		emf.close();
		
		return centro != null;
	}

}
