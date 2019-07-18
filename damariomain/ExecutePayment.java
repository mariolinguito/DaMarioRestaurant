package damariomain;

/**
 * La classe ha il compito di eseguire il pagamento per tutti i prodotti acquistati da un tavolo. Ovviamente 
 * avendo utilizzato un Design Pattern come Strategy, dobbiamo far scegliere all'utente quale sia 
 * il metodo di pagamento scelto, calcolare il totale del suo ordine (CalculateTotal) e poi 
 * procedere con il pagamento. Restituire un messaggio di avvenuto pagamento, e poi tramite una 
 * QUERY cancellare tutte le ordinazioni di quel tavolo. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class ExecutePayment {
    /**
     * Il costruttore della classe si occupa di inizializzare la variabile di istanza della
     * classe con ciò che è dato in input allo stesso costruttore. 
     * 
     * @param numTable Il parametro di input è il codice univoco assegnato al tavolo che si vuole servire. 
     */
    
    public ExecutePayment (int numTable) {
        this.nTable = numTable; 
    }
    
    public void pay (PaymentStrategy paymentMethod) {
        // Si calcola il totale della spesa fatta da un tavolo in relazione alle ordinazioni 
        // che quello stesso tavolo ha effettuato. Il risultato lo si passa come parametr di 
        // input per poter eseguire il pagamento. 
        
        double totalAmount = new CalculateTotal().getCalculatedTotal(nTable); 
        paymentMethod.pay(totalAmount);
        
        // Una volta che avviene il pagamento, come è ovvio che sia, tutti i records
        // contenuti nella tabella "ordinazioni" corrispondenti al cliente (tavolo)
        // che sta effettuando il pagamento, vengono eliminati, per poter far spazio 
        // a nuove ordinazioni senza avere interferenze da quelle precedenti.
        
        new ClearOrder().executeClearOrder(nTable);
    }
    
    private final int nTable; 
}
