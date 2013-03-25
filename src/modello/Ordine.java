package modello;

import java.util.*;

public class Ordine {
	private int id;
	private Date data;
	private String stato,codice;
	private Cliente cliente;
	private List<RigaOrdine> righeOrdine;
	
	public Ordine(){
		this.righeOrdine=new LinkedList<RigaOrdine>();
	}

	public String getCodiceOrdine() {
		return this.codice;
	}
	public Date getData() {
		return this.data;
	}
	public String getStato() {
		return this.stato;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public List<RigaOrdine> getRigheOrdine(){
		return this.righeOrdine;
	}
	public void setCodiceOrdine(String codiceOrdine) {
		this.codice = codiceOrdine;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public  void setCliente(Cliente cliente) {
		 this.cliente = cliente;
	}
	public  void setStato(String stato) {
		 this.stato = stato;
	}
	public void addRigaOrdine(RigaOrdine rigaOrdine){
		 this.righeOrdine.add(rigaOrdine);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
		
}
