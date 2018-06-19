package it.uniroma3.siw.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.uniroma3.siw.model.Centro;
import it.uniroma3.siw.repository.CentroRepository;

public class CentroJpaRepository implements CentroRepository{
	
	EntityManager em;
	
	public CentroJpaRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Centro save(Centro centro) {		
		if (centro.getId() == null) {
			em.persist(centro);
		}
		else {
			Centro controllo = findByPrimaryKey(centro.getId());
			if (controllo == null) {
				em.persist(centro);
			}
			else {
				update(centro);
			}
		}
		return centro;
	}

	@Override
	public Centro findByPrimaryKey(Long id) {
		return em.find(Centro.class, id);
	}
	
	public Centro findByEmail(String email) {
		EntityTransaction tx = em.getTransaction();
		Centro query = null;
		try {
			tx.begin();
			query = (Centro)em.createQuery("select Centro from centro where email=\'" + email + "\'").getSingleResult();
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return query;
		
	}

	@Override
	@SuppressWarnings ("unchecked")
	public List<Centro> findAll() {
		return em.createQuery("select * from centro").getResultList();
	}

	@Override
	public void update(Centro centro) {
		em.merge(centro);
	}

	@Override
	public void delete(Centro centro) {
		em.remove(centro);		
	}

}
