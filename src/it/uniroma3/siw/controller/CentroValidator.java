package it.uniroma3.siw.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class CentroValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String indirizzo, String email, String telefono, String capienza) {
		
		boolean errori = false;
		
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
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNomeCentro", "Campo obbligatorio.");
			errori = true;
		}
		else if (indirizzo == null || indirizzo.equals("")) {
			request.setAttribute("errIndirizzoCentro", "Campo obbligatorio.");
			errori = true;
		}
		else if (email == null || indirizzo.equals("")) {
			request.setAttribute("errEmailCentro", "Campo obbligatorio.");
			errori = true;
		}
		else if (telefono == null || telefono.equals("")) {
			request.setAttribute("errTelefonoCentro", "Campo obbligatorio.");
			errori = true;
		}
		else if (capienza == null || capienza.equals("")) {
			request.setAttribute("errCapienzaCentro", "Campo obbligatorio.");
			errori = true;
		}
		else if (!emailIisValid(email)) {
			request.setAttribute("errEmailCentro", "Email non valida.");
			errori = true;
		}
		
		// controlla il metodo per l'email già presente
		
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

}
