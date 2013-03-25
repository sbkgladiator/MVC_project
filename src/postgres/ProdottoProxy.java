package postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modello.Prodotto;
import persistenza.DataSource;
import persistenza.PersistenceException;

public class ProdottoProxy {
	private DataSource datasource=new DataSource();
public ProdottoProxy(){
	
}
public List<Prodotto> getCatalogo() throws PersistenceException {
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
			prodotto.setId(result.getInt("id"));
			prodotto.setDisponibilita(result.getInt("disponibilita"));
			prodotti.add(prodotto);
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

public Prodotto getProdotto(String codice) throws PersistenceException {
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

}
