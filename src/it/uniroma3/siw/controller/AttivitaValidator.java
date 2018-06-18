package it.uniroma3.siw.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class AttivitaValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String data, String orario) {
		
		boolean errori = false;
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNomeAttivita", "Il nome è obbligatorio.");
			errori = true;
		}
		if (data == null || data.equals("")) {
			request.setAttribute("errDataAttivita", "La data è obbligatoria.");
			errori = true;
		}
		if (orario == null || orario.equals("")) {
			request.setAttribute("errOrarioAttivita", "L'orario è obbligatorio.");
			errori = true;
		}
		
		if(!dateIsValid(data)) {
			request.setAttribute("errDataAttivita", "Data non valida.");
			errori = true;
		}
		if(!timeIsValid(orario)) {
			request.setAttribute("errOrarioAttivita", "Orario non valido.");
			errori = true;
		}	
		
		return errori;
		
	}
	
	public boolean dateIsValid(String data) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data2;
		try {
			data2 = formatter.parse(data);
			return data2.before(formatter.parse(formatter.format(new Date()))); // NON FUNZIONA, RITORNA SEMPRE FALSE, RISOLVERE
		} catch (ParseException e) {
			return false;
		}

	}
	
	public boolean timeIsValid(String orario) {

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date orario2;
		try {
			orario2 = formatter.parse(orario);
			return orario2.before(formatter.parse(formatter.format(new Date()))); // NON FUNZIONA, RITORNA SEMPRE FALSE, RISOLVERE
		} catch (ParseException e) {
			return false;
		}

	}

}