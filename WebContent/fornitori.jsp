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
Elenco fornitori:<br></br>
<table>
<tr><td>Nome</td><td>Indirizzo</td><td>Telefono</td></tr>
<br></br>
<%
	FacadePg facade = new FacadePg();
	List<Fornitore> forn=facade.getFornitoriProdotto(request.getParameter("prodotto")); 
	int i = 0;
	for(Fornitore f:forn) {
		out.println("<tr><td>"+f.getNome()+"</td><td>"+f.getIndirizzo()+"</td><td>"+f.getTelefono()+
				"</td></tr>");
		i++;
	}
%></table>
</body>
</html>