package modello; 

import java.util.*;

public class Prodotto {
	private String codiceProdotto;
	private String nome;
	private String descrizione;
	private int costo,id,disponibilita;
	private List<Fornitore> fornitori;
	
	public Prodotto(){
		fornitori=new LinkedList<Fornitore>();
	}

	public String getCodiceProdotto() {
		return this.codiceProdotto;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public int getCosto(){
		return this.costo;
	}
	
	public int getId(){
		return this.id;
	}

	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescrizione(String descrizione) {
		 this.descrizione = descrizione;
	}

	public void setCosto(int costo){
		 this.costo = costo;
	}

	public void setId(int id){
		 this.id = id;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	public int getDisponibilita() {
		return disponibilita;
	}
	
	public void addFornitore(Fornitore fornitore){
		this.fornitori.add(fornitore);
	}
	
	public List<Fornitore> getFornitori(){
		return this.fornitori;
	}
	
}
