package persistenza;

import java.sql.*;

public class DataSource {
	private String dbURI = "jdbc:postgresql:siw";
	private String userName = "postgres";
	private String password = "admin";

	public Connection getConnection() throws PersistenceException {
		Connection connection;
		try {
		    Class.forName("org.postgresql.Driver");
		    connection = DriverManager.getConnection(dbURI,userName, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}
