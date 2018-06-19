package it.uniroma3.siw.repository;

import java.util.List;
import it.uniroma3.siw.model.Centro;

public interface CentroRepository {
	
	public Centro save(Centro centro);
	public Centro findByPrimaryKey(String email);
	public List<Centro> findAll();
	public void update(Centro centro);
	public void delete(Centro centro);

}