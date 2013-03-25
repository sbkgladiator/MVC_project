package modello;

import java.util.*;

public class Cliente {
	private int id;
	private String nome;
	private String indirizzo;
	private String piva;
	private List<Ordine> ordini;
	
	public Cliente(){
		ordini=new LinkedList<Ordine>();
	}

	
	public String getNome() {
		return this.nome;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public  void setIndirizzo(String indirizzo) {
		 this. indirizzo = indirizzo;
	}

	public List<Ordine> getOrdini() {
		return this.ordini;
	}

	public void addOrdine(Ordine ordine) {
		this.ordini.add(ordine);
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return this.id;
	}
	
		
}