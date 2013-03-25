package action;

import helper.FormProdottoHelper;
import helper.FormRigaHelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modello.RigaOrdine;
import persistenza.PersistenceException;
import postgres.FacadePg;
import postgres.ProdottoDAOImpl;

/**
 * Servlet implementation class AzioneAggiungiRighe
 */
public class AzioneAggiungiRighe extends Azione {
	

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		String esito="aggiunte";
		HttpSession sess=request.getSession();
		int size=Integer.parseInt(request.getParameter("size"));
		FormRigaHelper helper = new FormRigaHelper(request);
		if (helper.convalida()) {
		for(int i=0;i<size;i++){
			String add=request.getParameter("prodotto"+i);
			if(add!=null){
				int ord=Integer.parseInt(request.getParameter("idOrdine"));
				FacadePg fac =new FacadePg();
		fac.addRiga(Integer.parseInt(request.getParameter("idOrdine")),Integer.parseInt(request.getParameter("need"+i)),add);
			
			}
		}
		}
		else esito="erroreConvalidaRiga";
		return esito;
	
	}

}
