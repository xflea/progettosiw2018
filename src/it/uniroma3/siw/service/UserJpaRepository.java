package it.uniroma3.siw.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.uniroma3.siw.model.Admin;
import it.uniroma3.siw.repository.UserRepository;

public class UserJpaRepository implements UserRepository{
	
	EntityManager em;
	
	public UserJpaRepository(EntityManager em) {
		this.em = em;
	}
	
	public boolean checkUser(String p_name, String p_password) {
		EntityTransaction tx = em.getTransaction();
		Admin obj_controllo = null;
		try {
			tx.begin();
			obj_controllo = (Admin)em.createQuery("select name, password from admin where name=\'" + p_name + "\' "
					+ "and password=\'" + p_password + "\'");
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return obj_controllo != null;
		
	}

}
