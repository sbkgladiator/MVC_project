package modello;
import java.util.*;

public class Utente {

	private int id;
	private String user,pwd,nome;
	private boolean isAdmin;
	
	public Utente()
	{
		
	}
		
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	
	
	public boolean getIsAdmin(){
		return this.isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin=isAdmin;
	}
	public void setPwd (String pwd){
		this.pwd=pwd;
	}
	public String getPwd(){
		return this.pwd;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
