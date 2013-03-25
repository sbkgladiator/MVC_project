package postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import modello.*;
import persistenza.*;


public class UtenteDAOImpl implements UtenteDAO {

	public void save(Utente utente) throws PersistenceException {
		if (this.doRetrieveById(utente.getUser()) == null) {
			this.doInsert(utente);
		}
		else
			System.out.println("Utente inserito");
	}
	
	
	private void doInsert(Utente utente) throws PersistenceException {
		DataSource ds = new DataSource();
		Connection connection = ds.getConnection();
		PreparedStatement statement = null;
		try {
			String update = "insert into utenze (isadmin,pwd,utente,id) values (?,?,?,?)";
			statement = connection.prepareStatement(update);
			statement.setBoolean(1, utente.getIsAdmin());
			statement.setString(2, utente.getPwd());
			statement.setString(3, utente.getUser());
			statement.setInt(4, utente.getId());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	
	public Utente doRetrieveById(String id) throws PersistenceException {
		Utente utente = null;
		DataSource ds =new DataSource();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = ds.getConnection();
			String retrieve = 	"SELECT user, pwd, isadmin " +
								"FROM utenze " +
								"WHERE user=?";
			statement = connection.prepareStatement(retrieve);
			statement.setString(1, id);
			result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setUser(id);
				utente.setPwd(result.getString("pwd"));
				utente.setIsAdmin(result.getBoolean("isadmin"));
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (result != null)
					result.close();
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return utente;
		
	}


	public Utente doRetrieveByIdPwd(String id, String pwd) throws PersistenceException {
		Utente utente = null;
		DataSource ds =new DataSource();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = ds.getConnection();
			String retrieve = 	"SELECT utente, pwd,  isadmin,id FROM utenze WHERE utente=? and pwd=?";
			statement = connection.prepareStatement(retrieve);
			statement.setString(1, id);
			statement.setString(2, pwd);
			result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setUser(id);
				utente.setPwd(result.getString("pwd"));
				utente.setIsAdmin(result.getBoolean("isadmin"));
				utente.setId(result.getInt("id"));
				utente.setNome(new ClienteDAOImpl().getClienteById(utente.getId()).getNome());
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (result != null)
					result.close();
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return utente;
		
	}
}
