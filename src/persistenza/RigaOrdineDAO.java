package persistenza;

import modello.*;

public interface RigaOrdineDAO {
public int nextRiga(int ordine) throws PersistenceException;
public void addRiga(RigaOrdine riga) throws PersistenceException;
}
