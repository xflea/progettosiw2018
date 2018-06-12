package it.uniroma3.siw.controller;

import javax.servlet.http.HttpServletRequest;

public class AttivitaValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String data, String orario) {
		
		boolean errori = false;
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNomeAttivita", "Il nome � obbligatorio.");
			errori = true;
		}
		else if (data == null || data.equals("")) {
			request.setAttribute("errDataAttivita", "La data � obbligatoria.");
			errori = true;
		}
		else if (orario == null || orario.equals("")) {
			request.setAttribute("errOrarioAttivita", "L'orario � obbligatorio.");
			errori = true;
		}
		
		return errori;
		
	}

}