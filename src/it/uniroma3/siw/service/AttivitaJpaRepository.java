package it.uniroma3.siw.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.uniroma3.siw.model.Attività;
import it.uniroma3.siw.repository.AttivitaRepository;

public class AttivitaJpaRepository implements AttivitaRepository{
	
	EntityManager em;
	
	public AttivitaJpaRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Attività save(Attività attivita) {	
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			if (attivita.getId() == null) {
				em.persist(attivita);
			}
			else {
				Attività controllo = findByPrimaryKey(attivita.getId());
				if (controllo == null) {
					em.persist(attivita);
				}
				else {
					update(attivita);
				}
			}
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return attivita;
	}

	@Override
	public Attività findByPrimaryKey(Long id) {
		
		EntityTransaction tx = em.getTransaction();
		Attività found = null;
		
		try {
			tx.begin();
			found = em.find(Attività.class, id);
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return found;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Attività> findAll() {
		
		EntityTransaction tx = em.getTransaction();
		List<Attività> attività = null;
		
		try {
			tx.begin();
			attività = (List<Attività>)em.createNativeQuery("select * from attività").getResultList();
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return attività;
	}

	@Override
	public void update(Attività attivita) {
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.merge(attivita);
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
	}

	@Override
	public void delete(Attività attivita) {
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.remove(attivita);
			tx.commit();
		}
		catch(Exception E) {
			tx.rollback();
		}
			
	}

}
