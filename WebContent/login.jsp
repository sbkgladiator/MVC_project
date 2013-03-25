<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>login</title>
  <link rel="stylesheet" type="text/css" href="uniroma3.css" />
  </head>
  <body>
    <h1>Login</h1>
	 <form action="login.do" method="POST">
	   <div>
        <span>Username: <input type="text" name="username"/></span>
        <span>Password: <input type="password" name="password"/></span>
	   </div>
	   <div>
        <span><input type="reset" value="Reimposta"/></span>
        <span><input type="submit" value="Login" name="login"/></span>
      </div>
	</form>
			<br></br>
			<br></br>
			<a href="/progetto/catalogo.jsp"> -- Consulta catalogo prodotti -- </a>
  </body>
</html>