package postgres;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import modello.*;
import persistenza.*;

public class OrdineDAOImpl implements OrdineDAO {
	private DataSource datasource;
	
	public OrdineDAOImpl() {
		datasource = new DataSource();
	}

	@Override
	public void evadiOrdine(Ordine ordine) throws PersistenceException {//ok
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;		
		try {
			String str = "update ordini set stato=? where codice=?";
			statement = connection.prepareStatement(str);
			statement.setString(1, ordine.getStato());
			statement.setString(2, ordine.getCodiceOrdine());			
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

	@Override
	public Ordine getOrdineById(int codice) throws PersistenceException {//ok
		Ordine ordine = null;
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "select ordini.id,data,stato,codice,cliente,nome,partitaiva,indirizzo from ordini LEFT outer JOIN clienti on ordini.cliente = clienti.id where ordini.id=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, codice);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				ordine=new Ordine();
				ordine.setId(codice);
				ordine.setCodiceOrdine(result.getString("codice"));
				ordine.setStato(result.getString("stato"));	
				ordine.setData(result.getDate("data"));
				Cliente cliente=new Cliente();
				cliente.setId(result.getInt("cliente"));
				cliente.setNome(result.getString("nome"));
				cliente.setIndirizzo(result.getString("indirizzo"));
				cliente.setPiva(result.getString("partitaiva"));
				ordine.setCliente(cliente);
				}			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());		
		}
		finally {
			try {if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return ordine;
	}

	@Override
	public List<Ordine> getOrdiniPerCliente(Cliente cliente)throws PersistenceException {//ok
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		Ordine o = null;
		List<Ordine> lo=new LinkedList<Ordine>();		
		try {
			String str = "select codice,data,stato,id from ordini where cliente=?";
			statement = connection.prepareStatement(str);
			statement.setInt(1, cliente.getId());
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				o=new Ordine();
				o.setCliente(cliente);
				o.setCodiceOrdine(result.getString("codice"));
				o.setData(result.getDate("data"));
				o.setStato(result.getString("stato"));
				o.setId(result.getInt("id"));
				lo.add(o);
				//da completare per restituire ordine completo di righe
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

	@Override
	public List<Ordine> listaOrdini() throws PersistenceException {//ok
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		List<Ordine> ordini = null;
		Ordine ordine = null;		
		try {
			String str = "select ordini.codice as codice,stato,data,cliente,id " +
					"from ordini left outer join clienti on ordini.cliente=clienti.codice";
			statement = connection.prepareStatement(str);
			ResultSet result = statement.executeQuery();
			ordini = new ArrayList<Ordine>();
			while(result.next()) {
			ordine =new Ordine();
			ClienteDAOImpl cliente=new ClienteDAOImpl();
			ordine.setCliente(cliente.getClienteById(result.getInt("cliente")));
			ordine.setCodiceOrdine(result.getString("codice"));
			ordine.setData(new java.util.Date(result.getDate("data").getDate()));
			ordine.setStato(result.getString("stato"));
			ordine.setId(result.getInt("id"));
			ordini.add(ordine);
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
		return ordini;
	}

	@Override
	public int registraOrdine(Ordine ordine) throws PersistenceException {//ok
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		int i;
		if(this.getOrdineByCodice(ordine.getCodiceOrdine())!=null)
			throw new PersistenceException("Ordine gia presente");
		try {
			String str = "insert into ordini (id,codice,data,stato,cliente) values (?,?,?,?,?)";
			statement = connection.prepareStatement(str);
			IdBroker id = new IdBrokerPostgres();
			i=id.getId();
			statement.setInt(1, i);
			statement.setString(2,new ClienteDAOImpl().getClienteById(ordine.getCliente().getId()).getNome()+i);
			statement.setDate(3, new java.sql.Date(ordine.getData().getTime()));
			statement.setString(4, ordine.getStato());
			statement.setInt(5,ordine.getCliente().getId());			
			statement.executeUpdate();
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
				}
			}
		return i;
	}
	
	public void addRigaOrdine(RigaOrdine riga)throws PersistenceException{
		Connection conn=this.datasource.getConnection();
		PreparedStatement statement =null;
		try{
		String query="insert into righeordini (ordine,quantita,prodotto) values (?,?,?)";
		statement=conn.prepareStatement(query);
		statement.setInt(1, riga.getOrdine());
		statement.setInt(2, riga.getQuantita());
		statement.setString(3, riga.getProdotto().getCodiceProdotto());
		statement.executeUpdate();	
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
	public Ordine getOrdineByCodice(String codice) throws PersistenceException {
		Ordine ordine = null;
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		try {
			String query = "select ordini.codice,ordini.id as id,data,stato,cliente,nome,partitaiva,indirizzo " +
					"from ordini LEFT OUTER JOIN clienti on cliente = clienti.id WHERE ordini.codice=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, codice);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				ordine=new Ordine();
				ordine.setCodiceOrdine(codice);
				ordine.setStato(result.getString("stato"));	
				ordine.setId(result.getInt("id"));
				ordine.setData(new java.util.Date(result.getDate("data").getTime()));
					Cliente cliente=new Cliente();
				cliente.setId(result.getInt("cliente"));
				cliente.setNome(result.getString("nome"));
				cliente.setIndirizzo(result.getString("indirizzo"));
				cliente.setPiva(result.getString("partitaiva"));
				ordine.setCliente(cliente);
				}			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());		
		}
		finally {
			try {if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return ordine;
	}

	public void deleteByCliente(int id) throws PersistenceException{
		Connection connection = this.datasource.getConnection();
		PreparedStatement statement = null;
		
		try {
			String str = "delete from ordini where cliente=?";
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
	
}
