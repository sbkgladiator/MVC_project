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
Benvenuto amministratore
<br></br>
			<br><a href="<%=response.encodeUrl("Inserimento.jsp")%>">Inserire nuovo prodotto al catalogo</a>
<br></br>
<br><a href="<%=response.encodeUrl("Fornitore.jsp")%>">Ricerca fornitori prodotto</a>
<br></br>
			<form action="logout.do" method="post">
		  <span><input type="submit" value="Logout" name="conferma" /></span>
	  
</body>
</html>