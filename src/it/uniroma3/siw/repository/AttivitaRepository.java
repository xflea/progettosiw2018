package it.uniroma3.siw.repository;

import java.util.List;
import it.uniroma3.siw.model.Attivit�;

public interface AttivitaRepository {
	
	public Attivit� save(Attivit� attivita);
	public Attivit� findByPrimaryKey(Long id);
	public List<Attivit�> findAll();
	public void update(Attivit� customer);
	public void delete(Attivit� customer);

}