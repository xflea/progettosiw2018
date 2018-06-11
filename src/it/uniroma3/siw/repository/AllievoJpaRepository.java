package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

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
		if (allievo.getNome() == null) {
			em.persist(allievo);
		}
		else {
			Allievo controllo = findByNome(allievo.getNome());
			if (controllo == null) {
				em.persist(allievo);
			}
			else {
				update(allievo);
			}
		}
		return allievo;
	}

	@Override
	public Allievo findByNome(String nome) {
		return em.find(Allievo.class, nome);
	}

	@Override
	public List<Allievo> findAll() {
		return em.createQuery("select * from allievi").getResultList();
	}

	@Override
	public void update(Allievo allievo) {
		em.merge(allievo);
	}

	@Override
	public void delete(Allievo allievo) {
		em.remove(allievo);		
	}

}
