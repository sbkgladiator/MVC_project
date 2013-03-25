package helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modello.RigaOrdine;
import persistenza.PersistenceException;
import postgres.*;

public class FormRigaHelper {
	private HttpServletRequest request;
	
	 public FormRigaHelper(HttpServletRequest request) 
		throws ServletException {
this.request = request; 
}

public boolean convalida(){
	boolean esito=true;
	HttpSession sess=request.getSession();
	int size=Integer.parseInt(request.getParameter("size"));
	
	for(int i=0;i<size;i++){
		String add=request.getParameter("prodotto"+i);
		if(add!=null){
			if (request.getParameter("need"+i)==null) esito=false;
			if (request.getParameter("need"+i)=="") esito=false;
			if(!isInteger(request.getParameter("need"+i)))esito=false;
			try {
				if(Integer.parseInt(request.getParameter("need"+i))>new ProdottoProxy().getProdotto(add).getDisponibilita()) esito=false;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
		}
}
	return esito;
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
}
