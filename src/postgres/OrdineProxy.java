package postgres;

import java.sql.*;
import java.util.*;

import persistenza.DataSource;
import persistenza.PersistenceException;
import modello.Cliente;
import modello.Ordine;

public class OrdineProxy extends Ordine{
	private DataSource datasource;
	
	public OrdineProxy(){
		datasource=new DataSource();
	}
	
	public List<Ordine> getStatoOrdiniPerCliente(String cliente)throws PersistenceException {
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Ordine o = null;
		List<Ordine> lo=new LinkedList<Ordine>();		
		try {
			String str = "select codice,data,stato,id from ordini where cliente=?";
			statement = connection.prepareStatement(str);
			statement.setInt(1, new ClienteProxy().getClienteByNome(cliente).getId());
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				o=new Ordine();
				//o.setCliente(cliente);
				o.setCodiceOrdine(result.getString("codice"));
				o.setData(result.getDate("data"));
				o.setStato(result.getString("stato"));
				o.setId(result.getInt("id"));
				lo.add(o);
			}
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return lo;
	}

}
