package postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import postgres.IdBrokerPostgres;

import modello.*;
import persistenza.*;

public class RigaOrdineDAOImpl implements RigaOrdineDAO{
private DataSource datasource;

public RigaOrdineDAOImpl(){
	datasource = new DataSource();
}

	@Override
	public void addRiga(RigaOrdine riga) throws PersistenceException {
		Connection conn=this.datasource.getConnection();
		
		PreparedStatement statement =null;
		if (this.verificaDisponibilita(riga.getProdotto().getCodiceProdotto())<riga.getQuantita()) throw
		new PersistenceException("disponibilita'prodotto insufficente!!!!");
		
		try{
		String query="insert into righeordini (ordine,quantita,prodotto,id,numriga) values (?,?,?,?,?)";
		statement=conn.prepareStatement(query);
		statement.setInt(1, riga.getOrdine());
		statement.setInt(2, riga.getQuantita());
		statement.setInt(3, riga.getProdotto().getId());
		IdBroker id=new IdBrokerPostgres();
		statement.setInt(4,id.getId());		
		statement.setInt(5, this.nextRiga(riga.getOrdine()));
		statement.executeUpdate();	
		this.scalaDisponibilita(riga.getProdotto().getId(),riga.getProdotto().getDisponibilita()-riga.getQuantita());
	}catch (SQLException e) {
		throw new PersistenceException(e.getMessage());
	} finally {
		try {if(statement!=null) statement.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	}

	@Override
	public int nextRiga(int ordine) throws PersistenceException {
		Connection conn=this.datasource.getConnection();
		PreparedStatement statement =null;
		int riga=0;
		try{
		String query="select max(numriga) from righeordini where ordine=?";
		statement=conn.prepareStatement(query);
		statement.setInt(1, ordine);
		ResultSet risultato=statement.executeQuery();
		risultato.next(); 
		riga=risultato.getInt(1) +1;
	}catch (SQLException e) {
		throw new PersistenceException(e.getMessage());
	} finally {
		try {if(statement!=null) statement.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
		return riga;
	}

	public int verificaDisponibilita(String prodotto) throws PersistenceException{
		Connection conn=this.datasource.getConnection();
		PreparedStatement statement =null;
		int disp;
		try{
		String query="select disponibilita from prodotti where codice=?";
		statement=conn.prepareStatement(query);
		statement.setString(1, prodotto);
		ResultSet risultato=statement.executeQuery();
		risultato.next(); 
		disp=risultato.getInt("disponibilita");
	}catch (SQLException e) {
		throw new PersistenceException(e.getMessage());
	} finally {
		try {if(statement!=null) statement.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
		return disp;
	}
	public void deleteByOrdine(int id) throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		
		try {
			String str = "delete from righeordini where ordine=?";
			statement = connection.prepareStatement(str);
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	public void scalaDisponibilita(int prodotto,int dec) throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		
		try {
			String str = "update prodotti set disponibilita=? where id=?";
			statement = connection.prepareStatement(str);
			statement.setInt(1, dec);
			statement.setInt(2, prodotto);
			statement.executeUpdate();
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	
}
