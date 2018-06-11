package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

import it.uniroma3.siw.model.Attività;
import it.uniroma3.siw.repository.AttivitaRepository;

public class AttivitaJpaRepository implements AttivitaRepository{
	
	EntityManager em;
	
	public AttivitaJpaRepository(EntityManager em) {
		this.em = em;
	}

	// se esiste torna i dati, se non esiste faccio la persist
	@Override
	public Attività save(Attività attivita) {		
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
		return attivita;
	}

	@Override
	public Attività findByPrimaryKey(Long id) {
		return em.find(Attività.class, id);
	}

	@Override
	public List<Attività> findAll() {
		return em.createQuery("select * from attività").getResultList();
	}

	@Override
	public void update(Attività attivita) {
		em.merge(attivita);
	}

	@Override
	public void delete(Attività attivita) {
		em.remove(attivita);		
	}

}
