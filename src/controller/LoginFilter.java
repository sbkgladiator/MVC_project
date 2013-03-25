package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
public class LoginFilter implements Filter {
	private static final long serialVersionUID = 1L;
	protected FilterConfig config;
	private ServletContext context;
	private String filterName;
	private HashMap<String, String> comando2azione;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException{

HttpServletRequest req = (HttpServletRequest)request;
HttpServletResponse res = (HttpServletResponse)response;
HttpSession sess =req.getSession();
String prossimaPagina = null;
String comando = this.leggiComando(((HttpServletRequest) request).getServletPath());
String nomeAzione = this.comando2azione.get(comando);
if (nomeAzione==null) {
	prossimaPagina = "/login.jsp";
}
else if(sess.getAttribute("utente")!=null ||( comando.equals("login") || comando.equals("logout")))
chain.doFilter(req, res);
}
	
	public void init(FilterConfig filterConfig)throws ServletException {
		this.comando2azione = new HashMap<String, String>();
		this.comando2azione.put("inserimentoProdotto","action.AzioneInserimentoProdotto");
		this.comando2azione.put("fornitore","action.AzioneCercaFornitore");
		this.comando2azione.put("convalidaProdotto","action.AzioneConvalidaProdotto");
		this.comando2azione.put("login","action.LoginAction");
		this.comando2azione.put("logout","action.Logout");
		this.comando2azione.put("catalogo","catalogo.jsp");
		this.comando2azione.put("apriOrdine","action.AzioneApriOrdine");
		this.comando2azione.put("chiudiOrdine","action.AzioneChiudiOrdine");
		this.comando2azione.put("aggiornaOrdine","action.AzioneAggiungiRighe");
		this.comando2azione.put("convalidaCliente","action.AzioneConvalidaCliente");

}

	@Override
	public void destroy() {
	}
	private String leggiComando(String s) {
		String comando=s.substring(1, (s.length()-3));
		return comando;
	}
}
