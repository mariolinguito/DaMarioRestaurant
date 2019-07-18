package damariomain;

/**
 * Per il sistema del pagamento del conto di un tavolo si implementa un Design
 * Pattern già utilizzato nel corso dello sviluppo dell'applicazione, e cioè: 
 * Strategy. Ed è ovvia la decisione se si considera che ogni utente può 
 * scegliere con quale metodo poter saldare il propio conto. Allo stesso modo 
 * di quanto fatto per il login dell'utente, anche in questo caso si sviluppa 
 * un'interfaccia detta PaymentStrategy nella quale è definito un metodo pay che 
 * prende in input, ovviamente, l'ammontare delle spese da pagare. 
 * L'interfaccia è di riferimento per le classi che eseguono concretamente il 
 * pagamento, ovvero sia quelle classi che definiscono le diverse strategie, alla 
 * quale se ne potrebbe aggiungere un'altra facilmente, se solo si volesse. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public interface PaymentStrategy {
    public void pay (double amount); 
}
