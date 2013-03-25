<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
      <%@ page import="modello.*,java.util.*,postgres.*" %>
    <% Utente utente = (Utente)session.getAttribute("utente");
   boolean autorizzato = true;
   if (utente!=null && utente.getIsAdmin())
	   autorizzato=true;
   else 
   	   autorizzato = false;
   if (!autorizzato) {
   	   out.clear();
	   RequestDispatcher rd = application.getRequestDispatcher("/login.jsp");
   	   rd.forward(request, response);
	   return;
	}
%>
<% 
	String messaggio = "";
	Map<String,String> errori = (Map<String,String>)request.getAttribute("errori");
   	if (errori==null) 
		errori = new HashMap<String,String>();
   	else 
   		messaggio = "Riempire la form correttamente";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
  <body>
    <h1>Inserimento nuovo prodotto in catalogo</h1>
    <h2><%= messaggio %></h2>
	<form action="<%=response.encodeUrl("inserimentoProdotto.do")%>" method="post">
	  <div>
		<h2>Inserire dati prodotto:</h2>
		<span>Nome: <input type="text" 
			      name="nome" 
         		      value="<%if (request.getParameter("nome")!=null) 
				     out.print(request.getParameter("nome")); %>" />
		</span>
		
   		<% if (errori.get("nome")!=null)
		    out.print("<span class=\"errore\"> "+errori.get("nome")+ "</span>");%>
		
		
		<span>Descrizione: <input type="text" 
			      name="descrizione" 
         		      value="<%if (request.getParameter("descrizione")!=null) 
				     out.print(request.getParameter("descrizione")); %>" />
		</span>
		
   		<% if (errori.get("descrizione")!=null)
		    out.print("<span class=\"errore\"> "+errori.get("descrizione")+ "</span>");%>
		    
		<span>Codice: <input type="text" 
			      name="codice" 
         		      value="<%if (request.getParameter("codice")!=null) 
				     out.print(request.getParameter("codice")); %>" />
		</span>
		
   		<% if (errori.get("codice")!=null)
		    out.print("<span class=\"errore\"> "+errori.get("codice")+ "</span>");%>
		    
		<span>Prezzo: <input type="text" 
			      name="prezzo" 
         		      value="<%if (request.getParameter("prezzo")!=null) 
				     out.print(request.getParameter("prezzo")); %>" />
		</span>
		
   		<% if (errori.get("prezzo")!=null)
		    out.print("<span class=\"errore\"> "+errori.get("prezzo")+ "</span>");%>	
		    
		<span>Disponibilita: <input type="text" 
			      name="disponibilita" 
         		      value="<%if (request.getParameter("disponibilita")!=null) 
				     out.print(request.getParameter("disponibilita")); %>" />
		</span>
		
   		<% if (errori.get("disponibilita")!=null)
		    out.print("<span class=\"errore\"> "+errori.get("disponibilita")+ "</span>");%>		    	    		    
		  <span><input type="reset" value="Reimposta" /></span>
		  <span><input type="submit" value="Conferma" name="conferma" /></span>
		</div>

	</form>
	<a href="<%=response.encodeUrl("Amministrazione.jsp")%>">Torna indietro</a>
  </body>

</html>