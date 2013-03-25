package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modello.Utente;

import action.Azione;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> comando2azione; 
	private Map<String, String> esito2pagina; 
	private List<String> admin;
	private List<String> user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prossimaPagina = null;
		  String comando = this.leggiComando(request.getServletPath());
		  String nomeAzione = this.comando2azione.get(comando);
		  if (nomeAzione==null) {
			prossimaPagina = "/error.jsp";
		  }
		  else {
			Azione azione = null;
			try {
				azione = (Azione)Class.forName(nomeAzione).newInstance();
				String esitoAzione = azione.esegui(request);
				prossimaPagina = this.esito2pagina.get(esitoAzione);
			} catch (InstantiationException e) {
					prossimaPagina = "/error.jsp";
			} catch (IllegalAccessException e) {
					prossimaPagina = "/error.jsp";
			} catch (ClassNotFoundException e) {
					prossimaPagina = "/error.jsp";
			}
		}
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(response.encodeUrl(prossimaPagina));
		rd.forward(request, response);		
	}
	private String leggiComando(String s) {
		String comando=s.substring(1, (s.length()-3));
		return comando;
	}
	public void init() {
			this.comando2azione = new HashMap<String, String>();
		this.comando2azione.put("inserimentoProdotto","action.AzioneInserimentoProdotto");
		this.comando2azione.put("fornitore","action.AzioneCercaFornitore");
		this.comando2azione.put("catalogo","catalogo.jsp");
		this.comando2azione.put("convalidaProdotto","action.AzioneConvalidaProdotto");
		this.comando2azione.put("login","action.LoginAction");
		this.comando2azione.put("logout","action.Logout");
		this.comando2azione.put("apriOrdine","action.AzioneApriOrdine");
		this.comando2azione.put("chiudiOrdine","action.AzioneChiudiOrdine");
		this.comando2azione.put("aggiornaOrdine","action.AzioneAggiungiRighe");
		this.comando2azione.put("convalidaCliente","action.AzioneConvalidaCliente");
			this.esito2pagina= new HashMap<String, String>();
		this.esito2pagina.put("prodottoValido","/confermaProdotto.jsp");
		this.esito2pagina.put("erroreConvalidaProdotto","/Inserimento.jsp");
		this.esito2pagina.put("erroreConvalidaRiga","/erroreRiga.jsp");
		this.esito2pagina.put("errore","/error.jsp");
		this.esito2pagina.put("logout","/login.jsp");
		this.esito2pagina.put("trovati","/fornitori.jsp");
		this.esito2pagina.put("cliente","/Benvenuto.jsp");
		this.esito2pagina.put("chiuso","/chiuso.jsp");
		this.esito2pagina.put("admin","/Amministrazione.jsp");
		this.esito2pagina.put("aperto","/selezionaProdotti.jsp");
		this.esito2pagina.put("aggiunte","/confermaRighe.jsp");
		this.esito2pagina.put("inserito","/ConfermaInserimento.jsp");
		this.esito2pagina.put("prodottoInserito","/inseritoProdotto.jsp");		
		this.esito2pagina.put("clienteValido","/confermaCliente.jsp");
		this.esito2pagina.put("erroreUtente","/login.jsp");
	}
}
