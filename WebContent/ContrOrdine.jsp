<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="modello.*,java.util.*,postgres.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
FacadePg fac = new FacadePg();
HttpSession sess=request.getSession();
String user=((Utente)sess.getAttribute("utente")).getNome();
List<Ordine> cat =fac.getStatoOrdini(user);
out.println("ordini del cliente "+user+"=" + cat.size());
%>
<table>
<tr><td>codice</td><td>stato</td><td>data</td></tr>

<%for(Ordine o:cat){%>
<tr>
<td><% out.println(o.getCodiceOrdine());%></td>
<td><% out.println(o.getStato());%></td>
<td><% out.println(o.getData());%></td>
</tr>
<%}  %>
</table>
</body>
</html>