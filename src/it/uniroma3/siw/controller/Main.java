package it.uniroma3.siw.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	
	public static void main(String arg[]) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit");
		EntityManager em = emf.createEntityManager();	
		
		if(em!=null) em.close();
		if(emf!=null) emf.close();
		
	}

}
