package it.uniroma3.siw.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.DatatypeConverter;

import it.uniroma3.siw.repository.UserRepository;

public class UserJpaRepository implements UserRepository{
	
	EntityManager em;
	
	public UserJpaRepository(EntityManager em) {
		this.em = em;
	}
	
	public boolean checkUser(String p_name, String p_password) {
		EntityTransaction tx = em.getTransaction();
		Object obj_controllo = null;
		String hashedpassword = null;
		
		try {
			hashedpassword = hashPassword(p_password);
		}
		catch(Exception e) {
			
		}

		try {
			tx.begin();
			obj_controllo = em.createNativeQuery("select name, password from admin where name = '" + p_name + "' and password = '" + hashedpassword + "'").getSingleResult();
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return obj_controllo != null;
		
	}
	
	public String hashPassword(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		
		return myHash;

	}

}
