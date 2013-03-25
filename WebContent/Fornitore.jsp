<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
	<table>
<tr><td>Codice</td><td>Descrizione</td><td>Disponibilità</td><td>Prezzo</td></tr>
<%
	FacadePg facade = new FacadePg();
	List<Prodotto> prodotti = facade.getCatalogo();
	int i = 0;
	List<Prodotto> add=new LinkedList<Prodotto>();
	for(Prodotto p : prodotti) {%>
	<tr><td><%= p.getCodiceProdotto()%></td><td><%= p.getDescrizione()%></td><td><%=p.getDisponibilita()%></td>
	<td><%=p.getCosto()%></td><td>
	<form action="<%=response.encodeUrl("fornitori.jsp")%>">
	<input type='hidden' name='prodotto' value="<%=p.getCodiceProdotto()%>">
	<input type='submit' value='Cerca'></td></tr></form>
	<%	i++;
	}
%></table>
</body>
</html>