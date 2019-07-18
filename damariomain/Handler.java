package damariomain;

/**
 * L'Handler, ovvero il responsabile principale delle richieste inviate dal client, è una parte 
 * fondamentale del Design Pattern utilizzato, ovvero il Chain Of Responsability. Che consente di 
 * separare il mittente di una richiesta dal destinatario, in modo che gli oggetti vengano inseriti
 * in una sorta di catena con una richiesta trasmessa continuamente fino a trovare un oggetto che 
 * la possa gestire. Questo Design Pattern viene utilizzato quando non conosciamo a priori quale
 * oggetto è in grado di gestire una determinata richiesta, ed infatti nel nostro caso non conosciamo
 * chi sarà il reparto che dovrà gestire la richiesta. Questo dipenderà dalla categoria del prodotto, 
 * che ci suggerisce quale sarà il reparto che gestirà l'ordine inviato dal cameriere.
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public abstract class Handler {    
    public void setSuccessor (Handler successor) {
        this.mSuccessor = successor; 
    }
    
    public abstract void handleOrder (Order order); 
    
    protected Handler mSuccessor; 
}
