package damariomain;

import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 * La classe ha il compito di inserire l'ordine appena ricevuto nella tabella delle 
 * ordinazioni contenuta nel database che si sta utilizzando. In un primo luogo ci 
 * si connette al database stesso stabilendo una connessione con quest'ultimo, creando
 * poi uno statement funzionale all'esecuzione dell'apposita query SQL per l'inserimento. 
 * Sarà poi il metodo "setInsert" a permettere l'inserimento dei dati nel database, ed
 * infatti prende in input tutte le informazioni che dovranno poi essere caricate, come 
 * il codice del cliente (ovvero il tavolo), il codice dell'ordine, e quello del
 * cameriere. Ovviamente dobbiamo pur sempre considerare i tempi di preparazione di un 
 * ordine da parte sia del reparto della cucina che da quello del bar, ed infatti per 
 * simulare questo sfruttiamo un piccolo accorgiemento utilizzando la tecnologia 
 * dei thread. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class InsertIntoOrder extends JDialog {
    /**
     * Il costruttore della classe si occupa di stabilire la connessione con il database che è di base
     * all'applicazione, in modo da poter eseguire le query SQL sulle tabelle e manipolare i dati in 
     * esse contenuti.
     */
    
    public InsertIntoOrder () {
        try {            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Il metodo getRandomTimePreparation istanzia una classe Random atraverso l'oggetto
     * randomTime e si occupa di generare un numero casuale compreso in un range di valori
     * che va da 1000 a 5000, in modo da simulare il tempo di preparazione di un ordine,
     * rispettando uno dei requisiti di sviluppo dell'applicazione. 
     * 
     * @return Ritorna un intero generato casualmente tra 1000 e 5000. 
     */
    
    public int getRandomTimePreparation () {
        Random randomTime = new Random(); 
        return randomTime.nextInt(5000-1000) + 1000;
    }
    
    /**
     * Il metodo si occupa praticamente di eseguire la query che inserisce i dati relativi ad una
     * ordinazione nella tabella delle ordinazioni stessa. Prende in input infatti quelle che sono 
     * le informazioni necessarie a popolarla, ovvero sia l'ID del cliente (del tavolo), l'ID del 
     * prodotto che si sta ordinando, e l'ID del cameriere che sta prendendo l'ordinazione. Quest'ultima 
     * informazione è necessaria al fine di eseguire alcune operazioni per il quale il cameriere è predisposto. 
     * 
     * @param idCustomer L'ID del tavolo che sta effettuando l'ordinazione (un intero generato in modo casuale).
     * @param idOrder L'ID dell'ordine (ovvero del prodotto) anch'esso generato in modo casuale. 
     * @param idWaiter L'ID del cameriere che sta prendendo l'ordinazione, in modo da poterla eliminare qualora richiesto. 
     */
    
    public void setInsert (int idCustomer, int idOrder, int idWaiter) {
        // Attraverso una semplice query scritta in SQL si esegue l'inserimento delle informazioni nella 
        // tabella che riguarda le ordinazioni, per tenere traccia di queste ultime quando dovranno essere
        // manipolate dalle altre funzioni previste dal programma, come la cancellazione dell'ultima 
        // ordinazione fatta da quel cameriere (sempre identificato da un codice univoco), oppure l'eliminazione
        // di tutte le ordinazioni fatte da un determinato tavolo una volta che decide di saldare il conto. 
        
        String sqlAddOrder = "INSERT INTO ordinazioni (id_t, id_p, id_c) VALUES (" + idCustomer + ", " + idOrder + ", " + idWaiter + ")";
        
        // Essendo che è stata eseguita un'ordinazione, e che il piatto o la bevanda è stata servita al tavolo, 
        // bisogna a questo punto decrementare la quantità del prodotto presente in magazzino, in modo che poi
        // quando l'amministratore del sistema vorrà conoscere i prodotti più venduti (abbiamo previsto che ne 
        // siano cinque i prodotti mostrati) allorà lo si possa fare con una semplice query in SQL che ordina i
        // prodotti in senso decrescente limitando alla visualizzazione di soli cinque prodotti. 
        
        String sqlDecrementQuantity = "UPDATE prodotti SET quantita_v = quantita_v - 1 WHERE id_p = " + idOrder + " AND quantita_v > 0"; 
        try {
            // Attrverso il metodo "sleep" contenuto nella classe Thread siamo in grado di 
            // bloccare momentaneamente l'esecuzione del programma per un tempo che è quello
            // fornito come intero al metodo in input (millisecondi). A sua volta tale intero 
            // è il risultato ritornato dal metodo "getRandomTimePreparation" che si occupa 
            // appunto di generare un intero compreso in un certo range di valori. 
            
            Thread.sleep(this.getRandomTimePreparation());
            
            // A questo punto non resta che tentare di eseguire le query e di attendere che non ci siano errori nel 
            // corso delle operazioni. Semmai ci fossero, allora questi verranno catturati e gestiti come eccezioni
            // dal blocco catch che si occuperà di mostrare una finestra di dialogo con un messaggio che informerà 
            // l'utente che l'operazione non è andata a buon fine, ed inoltre fornirà alcuni dettagli tecnici del 
            // perchè l'operazione non è stata effettuata. 
            
            stmt.executeUpdate(sqlAddOrder);
            stmt.executeUpdate(sqlDecrementQuantity);
            JOptionPane.showMessageDialog(null, "Prodotto servito..", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Connection con = null; 
    private Statement stmt = null;
}
