package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe si occupa (come ci suggerisce anche il nome affibiatogli) di generare un ordine 
 * casuale. Questo lo si fa attraverso una semplice query che va a selezionare un unico elemento 
 * dalla tabella dei prodotti in modo casuale, e di tale prodotto, in particolare, si seleziona 
 * l'ID, ovvero il codice univoco che lo contraddistingue dagli altri. Ovviamente, per motivi 
 * pratici e legati alla logica dell'applicazione, abbiamo bisogno di conoscerne anche la categoria, 
 * e di questo infatti se ne occupa il secondo metodo della classe che recupera la categoria attraverso 
 * una query SQL che va a selezionarla proprio in corrispondenza dell'ID generato casualmente, e la 
 * restiuisce come stringa.
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class CasualOrder extends JDialog { 
    /**
     * Il metodo ha la funzione di eseguire la query in SQL attraverso il quale si seleziona l'ID di 
     * un prodotto in modo casuale. Tale codice poi, viene restituito come intero per poi essere 
     * adoperato in diverse altre operazioni che ne prevedono l'utilizzo. 
     * 
     * @return Il metodo ritorna un intero che è proprio il numero identificativo del prodotto selezionato 
     * casualmente dalla tabella che lo contiene. 
     */
    
    public int getCasualOrderID () {  
        try {
            stmt = con.createStatement();
            
            // La query SQL è molto semplice, infatti va semplicemente a selezionare l'ID di un prodotto 
            // che è preso in modo casuale dalla tabella che conserva le informazioni di tutti i prodotti,
            // limitando la selezione ad un prodotto soltanto, ed infatti uno ne vogliamo generare. 
            
            String sql = "SELECT id_p FROM prodotti ORDER BY RAND() LIMIT 1";
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            // Ciò che ne risulta è un intero, che viene salvato nella variabile pID, e che verrà 
            // poi restituita dal metodo. Nel caso in cui tutte queste operazioni poco prima 
            // descritte non dovessere andare a buon fine, allora viene generata un'eccezione che 
            // sarà appositamente gestita attraverso un messaggio mostrato in una finestra di dialogo. 
            
            pID = rs.getInt(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return pID; 
    }
    
    /**
     * Come lo si può capire dal nome affibiato al metodo, quest'ultimo svolge la funzione di recupero della 
     * categoria relativa al prodotto che è stato selezionato in modo casuale. Anche in questo caso si utilizza 
     * una query, che però non seleziona in modo casuale il prodotto, ma attraverso una clausola WHERE, va a 
     * selezionare la categoria del prodotto che ha come codice ID il codice che è stato restituito anche dal 
     * metodo precedente.
     * 
     * @return Il meotodo restituisce una stringa che corrisponde alla categoria del prodotto che è stato generato casualmente.  
     */
    
    public String getCasualOrderCategory () {
        try {
            stmt = con.createStatement();
            
            // La struttura della query è molto semplice a quella utilizzata nel metodo precedente, ed infatti 
            // attraverso tale query si va a selezionare la categoria del prodotto che ha come codice ID quello 
            // stesso codice che è il risultato della prima query. 
            
            String sql = "SELECT categoria_p FROM prodotti WHERE id_p = " + pID + " LIMIT 1";
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            // Viene in seguito restituito il risultato, che sarà utilizzato per capire in quale reparto l'ordine 
            // deve essere elaborato, dato che il programma ne prevede due: Cucina e Bar. 
            
            pCategory = rs.getString(1);
        } catch (Exception e) {
            
            // Anche in questo caso, se le operazioni non dovessero andare a buon fine, allora viene generata una 
            // finestra di errore, al cui interno sono mostrati anche dettagli precisi dell'entità dell'errore. 
            
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return pCategory; 
    }
    
    private final Connection con = ConnectionManager.getConnection(); 
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private int pID;
    private String pCategory; 
}
