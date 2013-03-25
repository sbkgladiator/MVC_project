package postgres;

import java.sql.*;
import persistenza.*;

public class IdBrokerPostgres implements IdBroker{
  public int getId() throws PersistenceException {
		int newId = -1;
		DataSource ds =new DataSource();
		ResultSet result = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			String sqlQuery = "SELECT nextval(?) AS newId";  
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, "id_postgres");
			result = statement.executeQuery();
			if (result.next()) 
				newId = result.getInt("newId");
			else 	throw new PersistenceException("invalid id");
		} catch(SQLException e) {
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
		return newId;		
	}
}

