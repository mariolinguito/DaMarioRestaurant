package damariomain;

/**
 * La classe ha il semplice compito di prendere un nuovo ordine da uno dei tavoli 
 * che sono scelti a caso in un range di valori ben definito attraverso la classe 
 * Customer, che restituisce proprio un intero compreso tra questi valori (1 - 10). 
 * L'ordine è anch'esso generato in modo casuale in un range di valori che dipende 
 * dalla tabella che contiene i prodotti, così come anche il tempo di preparazione 
 * dello stesso. Il tutto confluisce poi nel suggerimento (da parte del cameriere) 
 * di un ordine alternativo a quello che è stato generato, permettendo all'utente di 
 * accettare o meno quella richiesta. 
 * Qualsiasi sia la scelta dell'utente, l'ordine verrà in un primo momento elaborato 
 * dal reparto della cucina, ma se questo dovesse essere una bevanda allora verrà 
 * automaticamente gestito dal repato bar, seguendo lo schema definito dal Design 
 * Pattern Chain Of Responsability, poichè (essendo generato a caso l'ordine), non si 
 * sa a priori quale dei due reparti si occuperà di esso. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
*/

public class TakeOrder {
    /**
     * @param codWaiter Il parametro di input corrisponde al codice del cameriere 
     * che sta prendendo l'ordine. 
     */
    
    public TakeOrder (int codWaiter) {
        // Vengono in questo caso dichiarate due variabili di tipo Handler, ovvero 
        // "kitchen" e "bar", che sono i reparti che si occuperanno della gestione 
        // degli ordini. Inoltre poi, viene impostato poi il successore. 
        
        Handler kitchen = new Kitchen(); 
        Handler bar = new Bar(); 
        kitchen.setSuccessor(bar);
        
        // Il cliente viene generato in modo random attraverso la classe "Customer", 
        // che ritorna un numero random compreso in un range di valori che va da 1 
        // fino a 10, ovvero fino al numero di tavoli massimi presenti nel ristorante. 
        // Il numero generato viene salvato in una variabile di tipo intero, e viene 
        // passato come parametro per poter essere appunto elaborato insieme all'ordine. 
        
        Customer newCustomer = new Customer(); 
        int CustomerID = newCustomer.getRandomTableNumber(); 
        
        // Allo stesso modo, anche un ordine viene generato in modo casuale, ed in
        // particolare viene generato in un primo luogo un numero intero che indica 
        // l'id del prodotto, ed in base a tale codice identificativo unico, viene 
        // recuperata anche la categoria dello stesso, che è necessaria al programma
        // per capire quale deve essere il reparto che deve gestire la richiesta. 
        
        CasualOrder newOrder = new CasualOrder(); 
        int OrderID = newOrder.getCasualOrderID(); 
        String OrderCategory = newOrder.getCasualOrderCategory(); 
        
        // Come richiesto dal programma, dobbiamo poter offrire al cliente un'alternativa al 
        // suo ordine, appartenente alla stessa categoria, e bisogna attendere che l'utente
        // scelga in modo da poter processare l'ordine correttamente.
        
        SuggestAlternative proAlternative = new SuggestAlternative(OrderCategory); 
        int rsQuestion = proAlternative.rtSuggestNow();
        
        // In seguito viene inviata una nuova richiesta alla catena che sarà quindi
        // in graado di gestire l'ordine corrente. Le informazioni che passiamo 
        // ovviamente sono: 
        // 1. Il codice del tavolo che fa l'ordine, 
        // 2. Il codice del prodotto ordinato, 
        // 3. La categoria del prodotto, 
        // 4. Il codice del cameriere che ha segnato l'ordine.
        // ..........................................................................
        // Si deve però processare l'ordine esatto del cliente, quindi si deve capire
        // quale sia stata la sua scelta: Accettare o meno la proposta del cameriere. 
        // Se la scelta è stata accettata, allora viene trasmesso un numero intero che 
        // è sicuramente maggiore di 0, in quanto corrisponderà all'ID del prodotto 
        // alternativo. Nel caso in cui, invece, ciò che viene trasmesso è uguale a 0, 
        // allora il cliente avrà scelto di continuare con il suo ordine originario. 
        
        if (rsQuestion > 0) {
            kitchen.handleOrder(new Order(CustomerID, rsQuestion, OrderCategory, codWaiter));
        } else if (rsQuestion == 0) {
            kitchen.handleOrder(new Order(CustomerID, OrderID, OrderCategory, codWaiter));
        } 
    }
}
