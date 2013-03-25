<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

      <%@ page import="modello.*,java.util.*,postgres.*" %>
    <% Utente utente = (Utente)session.getAttribute("utente");
   boolean autorizzato = true;
   if (utente!=null && !utente.getIsAdmin())
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=session.getAttribute("idOrdine")%>
<form method="post" action="<%=response.encodeUrl("aggiornaOrdine.do")%>" >
Seleziona prodotti da ordinanre:
<table>
<tr><td>Nome</td><td>Descrizione</td><td>Disponibilità</td><td>Prezzo</td><td>Aggiungi all'ordine</td><td>Quantità</td></tr>
<%
	FacadePg facade = new FacadePg();
	List<Prodotto> prodotti = facade.getCatalogo();
	int i = 0;
	List<Prodotto> add=new LinkedList<Prodotto>();
	for(Prodotto p : prodotti) {
		Prodotto pr=new Prodotto();
		out.println("<tr><td>"+p.getNome()+"</td><td>"+p.getDescrizione()+"</td><td>"+p.getDisponibilita()+
				"</td><td>"+p.getCosto()+"</td><td><input type='checkbox' name='prodotto"+i+"' value='"+p.getCodiceProdotto()+"' /></td><td><input type='text' name='need"+i+"' /></td></tr>");
		
		i++;
	}
%>
</table>
<input type="hidden" name="size" value="<%= prodotti.size() %>" />
<input type="hidden" name="idOrdine" value="<%= session.getAttribute("idOrdine") %>" />
<input type="submit" value="Aggiorna Ordine" />
</form>
</body>
</html>