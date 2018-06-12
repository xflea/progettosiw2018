package it.uniroma3.siw.repository;

import java.util.List;
import it.uniroma3.siw.model.Allievo;

public interface AllievoRepository {
	
	public Allievo save(Allievo allievo);
	public Allievo findByEmail(String email);
	public List<Allievo> findAll();
	public void update(Allievo allievo);
	public void delete(Allievo allievo);

}