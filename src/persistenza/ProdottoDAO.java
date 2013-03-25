package persistenza;

import modello.*;
import java.util.*;

public interface ProdottoDAO {
	public List<Prodotto> doRetrieveAll() throws PersistenceException;

	public Prodotto doRetriveByKey(String codice) throws PersistenceException;

	public void delete(Prodotto prodotto) throws PersistenceException;	
	
	public void addFornitura(Fornitore fornitore, String prodotto) throws PersistenceException;
	
	public int addProdotto(Prodotto prodotto) throws PersistenceException;
}
