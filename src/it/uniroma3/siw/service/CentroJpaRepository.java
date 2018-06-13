package it.uniroma3.siw.service;

import java.util.List;

import javax.persistence.EntityManager;

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

	@Override
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
