package damariomain;

/**
 * I "reparti" che si occupano della gestione degli ordini sono due, tra cui la 
 * cucina, che ovviamente prepara tutti i prodotti che non sono bevande. La classe
 * che si occupa di definirla estende un'altra classe, che è quella astratta dell'
 * Handler, ed infatti per questo si fa l'override del metodo "handleOrder" per 
 * poter capire se il prodotto che si è scelto è una bevanda o un piatto. Se non è
 * un piatto, allora è una bevanda, e quindi si delega il compito di gestire 
 * quell'ordine al secondo "reparto", ovvero il bar. Si passa quindi al successore. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
*/

public class Kitchen extends Handler {
    /**
     * Il metodo si occupa di gestire l'ordine che è dato in input, solo nel caso però questo
     * si tratti di un piatto (ovvero di un prodotto che non appartiene alla categoria delle 
     * bevande), se così non fosse, allora dello stesso ordine se ne deve occupare il reparto
     * Bar, che è impostato appunto come successore e che presenta la stessa struttura logica 
     * della classe Kitchen. Il controllo della categoria dell'ordine viene fatto attraverso 
     * un semplice controllo IF-ELSE. 
     * 
     * @param order Ciò che è dato in input è proprio l'ordine, grazie al quale possiamo conoscerne 
     * la categoria per poterlo elaborare in uno dei due reparti. 
     */
    
    @Override
    public void handleOrder(Order order) {
        // Per capire se il prodotto trattato si tratta di una bevanda o di un piatto (qualsiasi 
        // esso sia), si sfrutta un semplice controllo IF-ELSE, che compara la categoria del prodotto
        // con la stringa "bibita", e se questo paragone risulta essere falso, allora si tratterà 
        // sicuramente di un piatto che dovrà essere preparato dalla cucina.
        
        if (!order.getCategoryOrder().equals("bibita")) {
            System.out.println("Si sta servendo un piatto al tavolo " + order.getIdCustomer());
            InsertIntoOrder newInserting = new InsertIntoOrder(); 
            newInserting.setInsert(order.getIdCustomer(), order.getIdOrder(), order.getIdWaiter()); 
        } else {
            // Nel caso contrario invece, si tratterà di una bevanda, che dovrà essere consegnata 
            // al tavolo dal reparto bar. Si tratta del successore. 
            
            mSuccessor.handleOrder(order);
        }
    }
}
