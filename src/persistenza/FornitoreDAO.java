package persistenza;

import java.util.List;

import modello.*;

public interface FornitoreDAO {
	public List<Fornitore> doRetrieveAll() throws PersistenceException;

	public Fornitore doRetriveByNome(String nome) throws PersistenceException;
	
	public Fornitore doRetrivebyId(int id) throws PersistenceException;
	
	public List<Fornitore> doRetriveByProduct(int id) throws PersistenceException;
	
	public int aggiungiFornitore(Fornitore fornitore) throws PersistenceException;
}
