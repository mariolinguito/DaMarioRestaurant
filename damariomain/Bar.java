package damariomain;

/**
 * Come richiesto nei requisiti del progetto, se non è un piatto l'ordine da gestire,
 * allora è una bevanda e dovrà essere gestita dal bar, che si occuperà di consegnare 
 * il prodotto al tavolo. La struttura della classe è molto simile a quella che riguarda
 * la cucina, ed infatti cambia solamente la condizione contenuta nell'intestazione del 
 * blocco IF-ELSE. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class Bar extends Handler {
    /**
     * Il metodo si occupa di capire se l'ordine che è stato generato, e che prende in input, debba essere 
     * elaborato dal reparto delle bevande o dal reparto della cucina. Infatti è attarverso un semplice controllo
     * IF-ELSE IF che si capisce se la categoria alla quale appartiene l'ordine sia compresa o meno tra quelle che 
     * vengono gestite dal bar. Se cosi non fosse, allora si trasmette questo stesso ordine al successore, seguendo 
     * la logica del Design Pattern scelto Chain of Responsability, ovvero alla cucina. 
     * 
     * @param order Il parametro di input è di tipo Order, infatti abbiamo bisogno di conoscere le informazioni riguardanti lo stesso ordine. 
     */
    
    @Override
    public void handleOrder(Order order) {
        // Se comparando la categoria del prodotto alla stringa "bibita", questo 
        // ci fornisce un riscontro positivo, allora si tratta di una bevanda, che 
        // sarà correttamente gestita. Altrimenti si passerà al successore, ovvero 
        // alla cucina dato che si sta trattando di un piatto. 
        
        if(order.getCategoryOrder().equals("bibita")) {
            System.out.println("Si sta servendo una bevanda al tavolo " + order.getIdCustomer());
            
            // La corretta gestione dell'ordine riguarda anche e soprattutto l'inserimento di 
            // tutti i dati relativo a questo nell'apposita tabella che tiene traccia di questi. 
            // La tabella di cui parliamo è quella delle "ordinazioni", che ha bisogno di 
            // immagazinare una serie di informazioni, quali l'ID del tavolo che sta eseguendo
            // l'ordine, l'ID del prodotto che è stato ordinato, e l'ID del cameriere che ha 
            // preso nota dell'ordine, in modo che possa cancellare il suo ultimo ordine se 
            // richiesto. 
            
            InsertIntoOrder newInserting = new InsertIntoOrder(); 
            newInserting.setInsert(order.getIdCustomer(), order.getIdOrder(), order.getIdWaiter()); 
        } else {
            mSuccessor.handleOrder(order); 
        }
    }
}
