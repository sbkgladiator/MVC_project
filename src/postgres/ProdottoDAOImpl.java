package postgres;

import java.util.*;
import java.sql.*;

import modello.*;
import persistenza.*;

public class ProdottoDAOImpl implements ProdottoDAO {
	private DataSource datasource;
	
	public ProdottoDAOImpl() {
		datasource = new DataSource();
	}
	
	
	public void delete(Prodotto prodotto) throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		
		try {
			String str = "delete from prodotti where codice=?";
			statement = connection.prepareStatement(str);
			statement.setString(1, prodotto.getCodiceProdotto());
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

	public List<Prodotto> doRetrieveAll() throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		List<Prodotto> prodotti = null;
		Prodotto prodotto = null;
		
		try {
			String str = "select * from prodotti";
			statement = connection.prepareStatement(str);
			ResultSet result = statement.executeQuery();
			prodotti = new ArrayList<Prodotto>();
			while(result.next()) {
				prodotto = new Prodotto();
				prodotto.setCodiceProdotto(result.getString("codice"));
				prodotto.setNome(result.getString("nome"));
				prodotto.setDescrizione(result.getString("descrizione"));
				prodotto.setCosto(result.getInt("costo"));
				prodotto.setCosto(result.getInt("id"));
				prodotto.setDisponibilita(result.getInt("disponibilita"));
				prodotti.add(prodotto);
				//da completare per restituire completo di lista fornitori
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
		return prodotti;
	}

	public Prodotto doRetriveByKey(String codice) throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Prodotto prod = null;
		
		try {
			String str = "select * from prodotti where codice=?";
			statement = connection.prepareStatement(str);
			statement.setString(1, codice);
			
			ResultSet risultato=statement.executeQuery();
			while (risultato.next()){
				prod = new Prodotto();
				prod.setDescrizione(risultato.getString("descrizione"));
				prod.setCosto(risultato.getInt("costo"));
				prod.setCodiceProdotto(risultato.getString("codice"));
				prod.setNome(risultato.getString("nome"));
				prod.setId(risultato.getInt("id"));
				prod.setDisponibilita(risultato.getInt("disponibilita"));
				//da completare per restituire completo di lista fornitori
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
		return prod;
	}

	public int addProdotto(Prodotto prodotto) throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		String str;
		int codice=0;
		
		try {
				if(this.doRetriveByKey(prodotto.getCodiceProdotto())!=null){
					str = "update prodotti set codice=?,nome=?,descrizione=?,costo=?,disponibilita=? WHERE codice=?";
					statement = connection.prepareStatement(str);
					statement.setString(1, prodotto.getCodiceProdotto());
					statement.setString(2, prodotto.getNome());
					statement.setString(3, prodotto.getDescrizione());
					statement.setInt(4, prodotto.getCosto());
					statement.setInt(5, prodotto.getDisponibilita());
					statement.setString(6,prodotto.getCodiceProdotto());
					statement.executeUpdate();}
				else
					{str="insert into prodotti (codice,nome,descrizione,costo,id,disponibilita) values (?,?,?,?,?,?)";
				statement = connection.prepareStatement(str);
				statement.setString(1, prodotto.getCodiceProdotto());
				statement.setString(2, prodotto.getNome());
				statement.setString(3, prodotto.getDescrizione());
				statement.setInt(4, prodotto.getCosto());
				IdBroker id=new IdBrokerPostgres();
				codice=id.getId();
				statement.setInt(5, codice);
				statement.setInt(6,prodotto.getDisponibilita());
				statement.executeUpdate();}
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
	public void addFornitura(Fornitore fornitore, String prodotto)
			throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		String str;		
		try {
				str="insert into forniture (fornitore,prodotto) values (?,?)";
				statement = connection.prepareStatement(str);
				statement.setInt(2, this.doRetriveByKey(prodotto).getId());
				statement.setInt(1,fornitore.getId());
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
