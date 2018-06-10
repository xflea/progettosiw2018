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

	// se esiste torna i dati, se non esiste faccio la persist
	@Override
	public Attivit� save(Attivit� attivita) {		
		if (attivita.getNome() == null) {
			em.persist(attivita);
		}
		else {
			Attivit� controllo = findByNome(attivita.getNome());
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
	public Attivit� findByNome(String nome) {
		return em.find(Attivit�.class, nome);
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
