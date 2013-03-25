package postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import modello.*;
import persistenza.*;

public class FornitoreDAOImpl implements FornitoreDAO{
	private DataSource datasource;
	
	public FornitoreDAOImpl(){
		datasource = new DataSource();
	}

	@Override
	public int aggiungiFornitore(Fornitore fornitore)
			throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		int codice;
		if (this.doRetriveByNome(fornitore.getNome())!=null) 
			throw new PersistenceException("Fornitore esistente!");
		try {
			String str = "insert into fornitori (nome,telefono,indirizzo,id) values (?,?,?,?)";
			statement = connection.prepareStatement(str);
			statement.setString(1, fornitore.getNome());
			statement.setString(2, fornitore.getTelefono());
			statement.setString(3, fornitore.getIndirizzo());
			IdBroker id =new IdBrokerPostgres();
			codice=id.getId();
			statement.setInt(4, codice);
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
		return codice;
		
	}

	@Override
	public List<Fornitore> doRetrieveAll() throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Fornitore f = null;
		List<Fornitore> l=new LinkedList<Fornitore>();
		
		try {
			String str = "select * from fornitori";
			statement = connection.prepareStatement(str);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				f = new Fornitore();
				f.setId(result.getInt("id"));
				f.setNome(result.getString("nome"));
				f.setIndirizzo(result.getString("indirizzo"));
				f.setTelefono(result.getString("telefono"));
				l.add(f);
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
		return l;	
	}

	@Override
	public Fornitore doRetriveByNome(String nome) throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Fornitore f = null;
		
		try {
			String str = "select * from fornitori where nome=?";
			statement = connection.prepareStatement(str);
			statement.setString(1, nome);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				f = new Fornitore();
				f.setId(result.getInt("id"));
				f.setNome(result.getString("nome"));
				f.setIndirizzo(result.getString("indirizzo"));
				f.setTelefono(result.getString("telefono"));
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
		return f;
	}

	@Override
	public List<Fornitore> doRetriveByProduct(int id)
			throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Fornitore f = null;
		List<Fornitore> l=new LinkedList<Fornitore>();
		
		try {
			String str = "select fornitori.id as id,nome,telefono,indirizzo from forniture left outer join fornitori " +
					"on forniture.fornitore= fornitori.id where prodotto=?";
			statement = connection.prepareStatement(str);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				f = new Fornitore();
				f.setId(result.getInt("id"));
				f.setNome(result.getString("nome"));
				f.setIndirizzo(result.getString("indirizzo"));
				f.setTelefono(result.getString("telefono"));
				l.add(f);
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
		return l;
	}

	@Override
	public Fornitore doRetrivebyId(int id) throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Fornitore f = null;
		
		try {
			String str = "select * from fornitori where id=?";
			statement = connection.prepareStatement(str);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				f = new Fornitore();
				f.setId(result.getInt("id"));
				f.setNome(result.getString("nome"));
				f.setIndirizzo(result.getString("indirizzo"));
				f.setTelefono(result.getString("telefono"));
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
		return f;
	}

}
