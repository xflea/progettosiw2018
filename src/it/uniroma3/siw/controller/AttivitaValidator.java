package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

public class AttivitaValidator {
	
	public boolean validate(HttpServletRequest request, String nome, String data, String orario) {
		
		boolean errori = false;
		
		if (nome == null || nome.equals("")) {
			request.setAttribute("errNomeAttivita", "Il nome � obbligatorio.");
			errori = true;
		}
		if (data == null || data.equals("")) {
			request.setAttribute("errDataAttivita", "La data � obbligatoria.");
			errori = true;
		}else if(!dateIsValid(data)) {
			request.setAttribute("errDataAttivita", "Data non valida: le attivit� devono essere inserite con almeno un giorno di anticipo.");
			errori = true;
		}
		if (orario == null || orario.equals("")) {
			request.setAttribute("errOrarioAttivita", "L'orario � obbligatorio.");
			errori = true;
		}else if(!timeIsValid(orario)) {
			request.setAttribute("errOrarioAttivita", "Orario non valido: le attivit� devono essere inserite con almeno 24h di anticipo.");
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

}