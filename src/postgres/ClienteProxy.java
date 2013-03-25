package postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modello.Cliente;
import persistenza.*;

public class ClienteProxy extends Cliente {
	private DataSource datasource ;
	
	public ClienteProxy(){
		datasource=new DataSource();
	}

	public Cliente getClienteByNome(String id) throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Cliente c = null;
		
		try {
			String str = "select * from clienti where nome=?";
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
}
