package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.repository.AllievoJpaRepository;

import it.uniroma3.siw.controller.AllievoValidator;;

class AllievoTest {
	
	AllievoValidator validator = new AllievoValidator();

	@Test
	void emailIsValidTest() {
		
		assertTrue(validator.emailIsValid("prova@test.example"));
		assertFalse(validator.emailIsValid("prova@test"));
		assertFalse(validator.emailIsValid("prova.example"));
		assertFalse(validator.emailIsValid(""));
		
	}
	
	@Test
	void emailAlreadyExistsTest() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("azienda-unit-test");
		EntityManager em = emf.createEntityManager();
		
		AllievoJpaRepository repository = new AllievoJpaRepository(em);
		
		Allievo allievo = new Allievo();
		allievo.setNome("Kekkeroni");
		allievo.setCognome("Alformaggio");
		allievo.setEmail("kekdestroyer@kek.com");
		
		repository.save(allievo);
		
		assertNotNull(validator.emailAlreadyExists("kekdestroyer@kek.com", true));
		assertNull(validator.emailAlreadyExists("prova@test.example", true));
		
		if(em!=null) em.close();
		if(emf!=null) emf.close();
		
	}

}
