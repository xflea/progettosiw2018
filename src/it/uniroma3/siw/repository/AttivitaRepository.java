package it.uniroma3.siw.repository;

import java.util.List;
import it.uniroma3.siw.model.Attività;

public interface AttivitaRepository {
	
	public Attività save(Attività attivita);
	public Attività findByNome(String nome);
	public List<Attività> findAll();
	public void update(Attività customer);
	public void delete(Attività customer);

}