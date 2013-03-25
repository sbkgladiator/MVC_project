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
<%HttpSession sess = request.getSession();
out.println(((Utente)sess.getAttribute("utente")).getNome());%>
<br></br>
Apri un nuovo ordine
<br></br>
<form action="<%=response.encodeUrl("apriOrdine.do")%>" method="post">
 <span><input type="submit" value="Conferma" name="conferma" /></span>
 </form>
 <br></br>
 <%if(session.getAttribute("idOrdine")!=null){ %>
 c'e' un ordine aperto!!
 <%} %>
</body>
</html>