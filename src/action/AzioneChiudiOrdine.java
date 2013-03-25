package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modello.Ordine;

import persistenza.PersistenceException;
import postgres.FacadePg;
import postgres.OrdineDAOImpl;

/**
 * Servlet implementation class ChiudiOrdine
 */
public class AzioneChiudiOrdine extends Azione {


	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		String esito="chiuso";
		HttpSession sess=request.getSession();
		int ord=((Integer)sess.getAttribute("idOrdine")).intValue();
		FacadePg fac=new FacadePg();
		Ordine o=null;
		try {
			o = new OrdineDAOImpl().getOrdineById(ord);
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}
		o.setStato("chiuso");
			fac.registraOrdine(ord);
			sess.removeAttribute("idOrdine");
		return esito;
	}

}
