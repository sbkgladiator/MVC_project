package helper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.validator.*;
import java.lang.*;
import java.util.*;

public class FormProdottoHelper {
	private String nome; 
	private String descrizione; 
	private String codice; 
	private String disponibilita; 
	private String prezzo; 
	private HttpServletRequest request;

 
   public FormProdottoHelper(HttpServletRequest request) 
		 		throws ServletException {
	  this.request = request;
		this.nome = this.request.getParameter("nome"); 
		this.descrizione = this.request.getParameter("descrizione"); 
		this.codice = this.request.getParameter("codice"); 
		this.disponibilita = this.request.getParameter("disponibilita"); 
		this.prezzo = this.request.getParameter("prezzo"); 
	}
   
   public boolean convalida() {
		boolean tuttoOk = true;
		Map<String,String> errori = new HashMap<String, String>();

		if ((nome == null) || nome.equals("")) {
			tuttoOk = false;
			request.setAttribute("nome",nome);
			errori.put("nome","campo obbligatorio");
		}

		if ((descrizione == null) || descrizione.equals("")) {
			tuttoOk = false;
			request.setAttribute("descrizione",descrizione);
			errori.put("descrizione","campo obbligatorio");
		}

		if ((codice == null) || codice.equals("")) {
			tuttoOk = false;
			request.setAttribute("codice",codice);
			errori.put("codice","campo obbligatorio");
		}

		if (!isInteger(disponibilita)) {
			tuttoOk = false;
			request.setAttribute("disponibilita",disponibilita);
			errori.put("disponibilita","formato non valido");
		}

		if (!isInteger(prezzo)) {
			tuttoOk = false;
			request.setAttribute("prezzo",prezzo);
			errori.put("prezzo","formato non valido");
		}
		if (!tuttoOk)
			request.setAttribute("errori", errori);
		HttpSession sess=request.getSession();
		sess.setAttribute("errori", errori);
		return tuttoOk;
	}

	private boolean isInteger(String str) {
		 try{
			 Integer.parseInt(str);
			 return true;
		 }
		 catch(Exception e){
			 return false;
		 }
	}
	
	private boolean isBoolean(String a){		 
		 return a.equals("true")||a.equals("false");
	 }
}