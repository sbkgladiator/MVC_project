<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="modello.*,java.util.*,postgres.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr><td>Nome</td><td>Descrizione</td><td>Disponibilità</td><td>Prezzo</td></tr>

<%
	FacadePg facade = new FacadePg();
	List<Prodotto> prodotti = facade.getCatalogo();
	int i = 0;
	for(Prodotto p : prodotti) {
		Prodotto pr=new Prodotto();
		out.println("<tr><td>"+p.getNome()+"</td><td>"+p.getDescrizione()+"</td><td>"+p.getDisponibilita()+
				"</td><td>"+p.getCosto()+"</td></tr>");
		i++;
	}
%></table>
</body>
</html>