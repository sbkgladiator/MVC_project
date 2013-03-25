package action;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modello.*;

import persistenza.PersistenceException;
import postgres.ClienteProxy;
import postgres.FacadePg;

/**
 * Servlet implementation class ApriOrdine
 */
public class AzioneApriOrdine extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		String esito="aperto";
		FacadePg fac=new FacadePg();
		HttpSession sess=request.getSession();		int idOrdine=0;
		idOrdine=fac.creaOrdine(((Utente)sess.getAttribute("utente")).getNome());
		sess.setAttribute("idOrdine", idOrdine);
		request.setAttribute("idOrdine", idOrdine);
		return esito;
	}

}
