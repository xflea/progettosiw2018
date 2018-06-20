package it.uniroma3.siw.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.uniroma3.siw.repository.UserRepository;

public class UserJpaRepository implements UserRepository{
	
	EntityManager em;
	
	public UserJpaRepository(EntityManager em) {
		this.em = em;
	}
	
	public boolean checkUser(String p_name, String p_password) {
		EntityTransaction tx = em.getTransaction();
		Object obj_controllo = null;

		try {
			tx.begin();
			obj_controllo = em.createNativeQuery("select name, password from admin where name = '" + p_name + "' and password = '" + p_password + "'").getSingleResult();
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return obj_controllo != null;
		
	}

}
