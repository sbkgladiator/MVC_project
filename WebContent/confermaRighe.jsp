<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Prodotti registrati all'ordine <%=session.getAttribute("idOrdine")%>!!!


<br></br>
<form action="<%=response.encodeUrl("chiudiOrdine.do")%>" method="post">
<input type="hidden" name="idOrdine" value="<%= session.getAttribute("idOrdine") %>" />
 <span><input type="submit" value="Chiudi e invia" name="conferma" /></span>
</body>
</html>