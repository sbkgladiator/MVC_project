package persistenza;

import modello.*;
import java.util.*;

public interface OrdineDAO {
	public int registraOrdine(Ordine ordine) throws PersistenceException;
	
	public List<Ordine> listaOrdini() throws PersistenceException;
	
	public void evadiOrdine(Ordine ordine) throws PersistenceException;
	
	public Ordine getOrdineById(int codice) throws PersistenceException;
	
	public Ordine getOrdineByCodice(String codice) throws PersistenceException;
	
	public List<Ordine> getOrdiniPerCliente(Cliente cliente) throws PersistenceException;
	
	public void addRigaOrdine(RigaOrdine riga) throws PersistenceException;
}
