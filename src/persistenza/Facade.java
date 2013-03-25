package persistenza;

import java.util.*;
import modello.*;
import postgres.*;

public interface Facade {	
public List<Ordine> getStatoOrdini(String cliente) ;
public String getDescrizione(String prodotto) ;
public List<Prodotto> getCatalogo() ;

public void addProdottoCatalogo(String nome, String descr,int prezzo, int disp,String cod) ;

public void registraOrdine(int ordine) ;
public int creaOrdine(String user) ;

public void addFornitura(Fornitore fornitore, String prodotto) ;
public List<Fornitore> getFornitoriProdotto(String codiceProdotto) ;
public void addRiga(int ord,int quant,String codice) ;
}
