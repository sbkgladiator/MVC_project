package modello;

import java.util.*;

public class Fornitore {
	private int id;
	private String nome, indirizzo, telefono;
	private List<Prodotto> prodotti;
	
	public Fornitore(){
		prodotti=new LinkedList<Prodotto>();
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

	public void addProdotto(Prodotto prodotto){
		this.prodotti.add(prodotto);
	}
	
	public List<Prodotto> getProdotti(){
		return this.prodotti;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setTelefono(String telefono){
		this.telefono=telefono;
	}
	
	public String getTelefono(){
		return this.telefono;
	}
}
