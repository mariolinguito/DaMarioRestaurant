package damariomain;

/**
 * La classe definisce la struttura della richiesta, ovvero dell'ordine che è 
 * stato effettuato. Nel costruttore della classe infatti identifichiamo tutte 
 * le informazioni necessarie per inserire l'ordine nella tabella "ordinazioni", 
 * mentre con tutti gli altri metodi dichiarati nella classe li si recuperano. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */ 

public class Order {
    /**
     * Il costruttore della classe Order inizializza le variabili di istanza della stessa
     * classe con quelle date in input come parametri, ovvero l'ID del cliente, quello 
     * del cameriere, e quello dell'ordine compresa la categoria alla quale quest'ultimo
     * appartiene. 
     * 
     * @param idC Indica l'ID del cliente che sta eseguendo l'ordine. 
     * @param idO Indica l'ID del prodotto ordinato.  
     * @param cO Mentre invece questo parametro indica la categoria del prodotto che è stato ordinato. 
     * @param idW Indica l'ID del cameriere che ha preso l'ordinazione. 
     */
    
    public Order (int idC, int idO, String cO, int idW) {
        this.idWaiter = idW; 
        this.idCustomer = idC; 
        this.idOrder = idO; 
        this.cOrder = cO; 
    }
    
    // I metodi GET sono fondamentali al fine di recuperare le informazioni che sono 
    // state passate e processate al costruttore della classe, in modo che l'ordine 
    // possa essere inserito nel database senza intoppi. 
    
    /** 
     * @return Il metodo ritorna il codice identificativo del cameriere che sta
     * prendendo l'ordinazione. 
     */
    
    public int getIdWaiter () {
        return idWaiter; 
    }
    
    /**
     * @return Il meotodo ritorna l'ID del tavolo dalla quale sta partendo l'ordinazione, 
     * ovvero il codice del cliente. 
     */
    
    public int getIdCustomer () {
        return idCustomer; 
    }
    
    /**
     * @return Il metodo ritorna il codice univoco assegnato ad un prodotto 
     * (che è identificato con un ordine), ovvero il prodotto che si sta ordinando. 
     */
    
    public int getIdOrder () {
        return idOrder; 
    }
    
    /**
     * @return Il meotodo ritorna una stringa, ovvero la categoria del prodotto 
     * che è stato ordinato.  
     */
    
    public String getCategoryOrder () {
        return cOrder; 
    }
    
    private final int idCustomer, idOrder, idWaiter;  
    private final String cOrder; 
}
