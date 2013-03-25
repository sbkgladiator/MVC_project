package action;

import helper.FormProdottoHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import persistenza.PersistenceException;
import postgres.FacadePg;

import modello.Prodotto;

public class AzioneInserimentoProdotto extends Azione{
	
	public String esegui(HttpServletRequest request)
	  throws ServletException {
		HttpSession sess=request.getSession();
		FormProdottoHelper helper = new FormProdottoHelper(request);
		if (helper.convalida()) {
			FacadePg facade = new FacadePg();
				facade.addProdottoCatalogo(request.getParameter("nome"),request.getParameter("descrizione"),
						Integer.parseInt(request.getParameter("prezzo")),Integer.parseInt(request.getParameter("disponibilita")),
						request.getParameter("codice"));
			//
			return "inserito";
		}
		else{
	 		return "erroreConvalidaProdotto";}
		}
}
