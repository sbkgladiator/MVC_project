package postgres;

import java.util.*;
import java.sql.*;

import persistenza.*;
import modello.*;

public class ClienteDAOImpl implements ClienteDAO {
	private DataSource datasource;
	
	public ClienteDAOImpl() {
		datasource = new DataSource();		
	}

	public Cliente getClienteById(int id) throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Cliente c = null;
		
		try {
			String str = "select * from clienti where id=?";
			statement = connection.prepareStatement(str);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				c = new Cliente();
				c.setId(result.getInt("id"));
				c.setNome(result.getString("nome"));
				c.setIndirizzo(result.getString("indirizzo"));
				c.setPiva(result.getString("partitaiva"));
			}
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return c;
	}
	
	
	public List<Cliente> getClienti() throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		List<Cliente> clienti = null;
		Cliente cliente = null;
		
		try {
			String str = "select * from clienti";
			statement = connection.prepareStatement(str);
			ResultSet result = statement.executeQuery();
			clienti = new ArrayList<Cliente>();
			while(result.next()) {
				cliente = new Cliente();
				cliente.setNome(result.getString("nome"));
				cliente.setIndirizzo(result.getString("indirizzo"));
				cliente.setPiva(result.getString("partitaiva"));
				cliente.setId(result.getInt("id"));
				clienti.add(cliente);
			}
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return clienti;		
	}
	
	public Cliente getClienteByPIva(String id) throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Cliente c = null;
		
		try {
			String str = "select * from clienti where partitaiva=?";
			statement = connection.prepareStatement(str);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				c = new Cliente();
				c.setId(result.getInt("id"));
				c.setNome(result.getString("nome"));
				c.setIndirizzo(result.getString("indirizzo"));
				c.setPiva(result.getString("partitaiva"));
			}
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return c;
	}
	
	public void creaCliente(Cliente c) throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		if (this.getClienteByPIva(c.getPiva())!=null) 
			throw new PersistenceException("Client exists");
		try {
			String str = "insert into clienti (id,nome,partitaiva,indirizzo) values (?,?,?,?)";
			statement = connection.prepareStatement(str);
			IdBroker id = new IdBrokerPostgres();
			statement.setInt(1, id.getId());
			statement.setString(2, c.getNome());
			statement.setString(3, c.getPiva());
			statement.setString(4, c.getIndirizzo());
			statement.executeUpdate();
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
