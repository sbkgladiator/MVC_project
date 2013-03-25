package modello;

public class RigaOrdine {
	private int codOrdine;
	private Prodotto Prodotto;
	private int quantita;
	private int numRiga,id;
	
	public RigaOrdine(){}

	public Prodotto getProdotto () {
		return this.Prodotto;
	}

	public int getQuantita() {
		return this.quantita;
	}
	
	public int getOrdine() {
		return this.codOrdine;
	}


	public void setProdotto(Prodotto prodotto) {
		this.Prodotto = prodotto;
	}
	
	public int getNumeroRiga() {
		return this.numRiga;
	}

	public void setNumeroRiga(int riga) {
		this.numRiga = riga;
	}
	
	public void setQuantita(int quantita) {
		 this.quantita = quantita;
	}
	
	public void setOrdine(int ordine) {
		this.codOrdine = ordine;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
