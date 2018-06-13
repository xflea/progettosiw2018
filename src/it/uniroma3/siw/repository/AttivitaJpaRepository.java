package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

import it.uniroma3.siw.model.Attivit�;
import it.uniroma3.siw.repository.AttivitaRepository;

public class AttivitaJpaRepository implements AttivitaRepository{
	
	EntityManager em;
	
	public AttivitaJpaRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Attivit� save(Attivit� attivita) {		
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
		return attivita;
	}

	@Override
	public Attivit� findByPrimaryKey(Long id) {
		return em.find(Attivit�.class, id);
	}

	@Override
	public List<Attivit�> findAll() {
		return em.createQuery("select * from attivit�").getResultList();
	}

	@Override
	public void update(Attivit� attivita) {
		em.merge(attivita);
	}

	@Override
	public void delete(Attivit� attivita) {
		em.remove(attivita);		
	}

}
