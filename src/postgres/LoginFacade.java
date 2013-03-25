package postgres;

import persistenza.ModelException;
import modello.Utente;
import persistenza.Facade;
import persistenza.PersistenceException;
import persistenza.UtenteDAO;
import postgres.UtenteDAOImpl;

public class LoginFacade{
	
	private UtenteDAO uDAO;
	private static LoginFacade instance=null;
	
	public LoginFacade(){
		this.uDAO=new UtenteDAOImpl();
	}

	public static LoginFacade getInstance(){
		if(instance==null)
			instance=new LoginFacade();
		return instance;
	}
	
	public Utente autenticaUtente(String id, String pwd){
		
		Utente u=null;
		try{
			u=new UtenteDAOImpl().doRetrieveByIdPwd(id, pwd);
		}catch(PersistenceException e){
			try {
				throw new ModelException(e.getMessage()+" : Errore nell'autenticazione");
			} catch (ModelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return u;
	}
}
