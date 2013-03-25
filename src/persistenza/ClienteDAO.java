package persistenza;

import modello.*;

import java.util.List;

public interface ClienteDAO {
	public void creaCliente(Cliente c) throws PersistenceException;
	
	public Cliente getClienteById(int id) throws PersistenceException;
	
	public List<Cliente> getClienti() throws PersistenceException;
	
}
