package it.uniroma3.siw.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.repository.AllievoJpaRepository;

public class AllievoValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String cognome, String email, String telefono, String dataDiNascita, String luogoDiNascita) {
		
		boolean errori = false;
		
		try {
			Long.parseLong(telefono);
		}
		catch(NumberFormatException exception) {
			request.setAttribute("errTelefono", "Il telefono deve essere un numero");
		}
		
		if (!emailIisValid(email)) {
			request.setAttribute("errEmail", "Email non valida");
		}
		
		if(emailAlreadyExists(email, false)) {
			request.setAttribute("errEmail", "L'email fornita è già stata assegnata ad un allievo");
		}
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNome", "Il nome è obbligatorio.");
			errori = true;
		}
		else if (cognome == null || cognome.equals("")) {
			request.setAttribute("errCognome", "Il cognome è obbligattorio");
			errori = true;
		}
		else if (email == null || email.equals("")) {
			request.setAttribute("errEmail", "L'email è obbligatoria.");
			errori = true;
		}
		else if (telefono == null || telefono.equals("")) {
			request.setAttribute("errTelefono", "Il telefono è obbligatorio.");
			errori = true;
		}
		else if (dataDiNascita == null || dataDiNascita.equals("")) {
			request.setAttribute("errDataDiNascita", "La data di nascita è obbligatoria.");
			errori = true;
		}
		else if (luogoDiNascita == null || luogoDiNascita.equals("")) {
			request.setAttribute("errLuogoDiNascita", "Il luogo di Nascita è obbligatorio.");
			errori = true;
		}
		
		return errori;
		
	}
	
	public static boolean emailIisValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	public boolean emailAlreadyExists(String email, boolean test) {
		
		EntityManagerFactory emf;
		EntityManager em;
		
		if(test)
			emf = Persistence.createEntityManagerFactory("azienda-unit-test");
		else
			emf = Persistence.createEntityManagerFactory("azienda-unit");
		
		em = emf.createEntityManager();
		
		AllievoJpaRepository repository = new AllievoJpaRepository(em);
		
		Allievo allievo = repository.findByEmail(email);
		
		if(em!=null) em.close();
		if(emf!=null) emf.close();
		
		if (allievo == null)
			return false;
		else
			return true;
	}

}