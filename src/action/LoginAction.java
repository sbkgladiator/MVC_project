package action;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.codec.digest.DigestUtils;

import persistenza.ModelException;
import postgres.LoginFacade;

import modello.Utente;

public class LoginAction extends Azione{

	public LoginAction(){}

	public String esegui(HttpServletRequest request) throws ServletException{
		LoginFacade facade =new LoginFacade();
		String id = request.getParameter("username");
		String pwd = request.getParameter("password");
			Utente utente=null;
				utente = facade.autenticaUtente(id, DigestUtils.md5Hex(pwd));
				if (utente!=null) {
				HttpSession session = request.getSession(false);
				if (session != null)
					session.invalidate();
				session = request.getSession();
				session.setAttribute("utente",utente);
				if(utente.getIsAdmin())
					return "admin";
				else
					return "cliente";
			}
			else
				return "errore";
		
	}
}