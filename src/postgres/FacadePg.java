package postgres;

import java.util.*;

import persistenza.*;
import modello.*;

public class FacadePg implements Facade{
	private ProdottoDAO prodotti;
	private FornitoreDAO fornitori;
	private OrdineDAO ordini;
	
	public FacadePg(){
		fornitori = new FornitoreDAOImpl();
		ordini = new OrdineDAOImpl();
		prodotti = new ProdottoDAOImpl();
	}

	@Override
	public void addFornitura(Fornitore fornitore,String prodotto) {
		try {
			if(fornitori.doRetriveByNome(fornitore.getNome())==null) 
				fornitori.aggiungiFornitore(fornitore);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		try {
			prodotti.addFornitura(fornitori.doRetriveByNome(fornitore.getNome()), prodotto);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}	

	@Override
	public List<Prodotto> getCatalogo() {
		List<Prodotto> l=null;
		try {
			l= new ProdottoProxy().getCatalogo();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public String getDescrizione(String prodotto)  {
		String prod=null;
		try {
			prod=new ProdottoProxy().getProdotto(prodotto).getDescrizione();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return prod;
	}

	@Override
	public List<Fornitore> getFornitoriProdotto(String codiceProdotto) {
		List<Fornitore> fornitor=null;
		try {
			fornitor= fornitori.doRetriveByProduct(prodotti.doRetriveByKey(codiceProdotto).getId());
		} catch (PersistenceException e) {
			e.printStackTrace();
		}		
		return fornitor;
	}
	
	public void addRiga(int ord,int quant,String codice){
		RigaOrdine riga=new RigaOrdine();
		try {
			riga.setProdotto(new ProdottoDAOImpl().doRetriveByKey(codice));
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		riga.setQuantita(quant);
		riga.setOrdine(ord);
		try {
			new RigaOrdineDAOImpl().addRiga(riga);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registraOrdine(int ordine) {
		Ordine ord=new Ordine();	
		ord.setStato("chiuso");
		try {
			ord.setCodiceOrdine(ordini.getOrdineById(ordine).getCodiceOrdine());
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}
		try {
					ordini.evadiOrdine(ord);
				} catch (PersistenceException e) {
					e.printStackTrace();
				}					
	}	
	
	@Override
	public List<Ordine> getStatoOrdini(String attore) {
		List<Ordine> l=null;
		try {
			l= new OrdineProxy().getStatoOrdiniPerCliente(attore);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}		
		return l;
	}


	@Override
	public void addProdottoCatalogo(String nome, String descr,int prezzo, int disp,String cod) {
		Prodotto prodotto = new Prodotto();
		prodotto.setNome(nome);
		prodotto.setDescrizione(descr);
		prodotto.setCosto(prezzo);
		prodotto.setDisponibilita(disp);
		prodotto.setCodiceProdotto(cod);
		try {
			this.prodotti.addProdotto(prodotto);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int creaOrdine(String user) {
		
		Ordine ordine=new Ordine();				
				try {
					ordine.setCliente(new ClienteProxy().getClienteByNome((user)));
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
				ordine.setData(new GregorianCalendar().getTime());
				ordine.setStato("aperto"); int o=0;
		try {
			o= ordini.registraOrdine(ordine);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return o;
	}

}
