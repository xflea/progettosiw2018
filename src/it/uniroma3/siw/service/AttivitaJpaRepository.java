package it.uniroma3.siw.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.uniroma3.siw.model.Attivit�;
import it.uniroma3.siw.repository.AttivitaRepository;

public class AttivitaJpaRepository implements AttivitaRepository{
	
	EntityManager em;
	
	public AttivitaJpaRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Attivit� save(Attivit� attivita) {	
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			if (attivita.getId() == null) {
				em.persist(attivita);
			}
			else {
				Attivit� controllo = findByPrimaryKey(attivita.getId());
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
	public Attivit� findByPrimaryKey(Long id) {
		
		EntityTransaction tx = em.getTransaction();
		Attivit� found = null;
		
		try {
			tx.begin();
			found = em.find(Attivit�.class, id);
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return found;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Attivit�> findAll() {
		
		EntityTransaction tx = em.getTransaction();
		List<Attivit�> attivit� = null;
		
		try {
			tx.begin();
			attivit� = (List<Attivit�>)em.createNativeQuery("select * from attivit�").getResultList();
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return attivit�;
	}

	@Override
	public void update(Attivit� attivita) {
		
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
	public void delete(Attivit� attivita) {
		
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
