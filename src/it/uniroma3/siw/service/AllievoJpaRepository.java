package it.uniroma3.siw.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.repository.AllievoRepository;

public class AllievoJpaRepository implements AllievoRepository{
	
	EntityManager em;
	
	public AllievoJpaRepository(EntityManager em) {
		this.em = em;
	}

	// se esiste torna i dati, se non esiste faccio la persist
	@Override
	public Allievo save(Allievo allievo) {
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			
			tx.begin();
			if (allievo.getNome() == null) {
				em.persist(allievo);
			}
			else {
				Allievo controllo = findByPrimaryKey(allievo.getId());
				if (controllo == null) {
					em.persist(allievo);
				}
				else {
					update(allievo);
				}
			}
			tx.commit();
			
		}
		catch(Exception e){
			tx.rollback();
		}
		
		return allievo;
	}

	@Override
	public Allievo findByPrimaryKey(Long id) {
		
		EntityTransaction tx = em.getTransaction();
		Allievo found = null;
		
		try {
			tx.begin();
			found = em.find(Allievo.class, id);
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return found;
		
	}
	
	public Allievo findByEmail(String email) {
		EntityTransaction tx = em.getTransaction();
		Allievo allievo = null;

		try {
			tx.begin();
			allievo = (Allievo)em.createNativeQuery("select Allievo from allievo where email = '" + email + "'").getSingleResult();
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return allievo;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Allievo> findAll() {
		
		EntityTransaction tx = em.getTransaction();
		List<Allievo> allievi = null;
		
		try {
			tx.begin();
			allievi = em.createQuery("select * from allievo").getResultList();
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
		
		return allievi;
		
	}

	@Override
	@Transactional
	public void update(Allievo allievo) {
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(allievo);
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
	}

	@Override
	public void delete(Allievo allievo) {
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(allievo);
			tx.commit();
		}
		catch(Exception e) {
			tx.rollback();
		}
	}

}
