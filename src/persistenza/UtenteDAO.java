package persistenza;

import modello.*;
import java.util.*;

public interface UtenteDAO {

	public void save(Utente utente) throws PersistenceException;
	
	
	public Utente doRetrieveById(String id) throws PersistenceException;

	public Utente doRetrieveByIdPwd(String id, String pwd) throws PersistenceException;
	
	
	
}
