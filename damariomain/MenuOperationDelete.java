package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe si occupa semplicemente di eliminare dei prodotti che sono contenuti 
 * nella tabella "prodotti" in base ad uno specifico ID dato in input. 
 * Ovviamente, prima di eseguire le operazioni di eliminazione del record, si deve 
 * eseguire la connessione al database appositamente creato per l'applicazione. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class MenuOperationDelete extends JDialog {
    public MenuOperationDelete () {
        try {            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement(); 
        } catch (Exception e) {
            // Semmai la connessione al database non dovesse andare a buon fine, allora l'eccezione 
            // viene catturata e semplicemente gestita restituendo un messaggio di errore. 
            
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Il metodo si occuperò di eseguire una semplice query che ha la funzione di eliminare 
     * quel singolo prodotto indicato dal codice univoco dato in input e legato al prodotto
     * stesso. Come al solito, se l'operazione è andata a buon fine, allora viene mostrato 
     * un messaggio che ne indica il successo, nel caso contrario, il messaggio indicherà 
     * il fallimento dell'operazione. 
     * 
     * @param codProduct Il parametro di input è un intero che rappresenta il codice univoco del prodotto da eliminare. 
     */
        
    public void deleteProduct (int codProduct) {
        String sqlDelete = "DELETE FROM prodotti WHERE id_p = " + codProduct;
        
        try {
            // Ovviamente si tenta di eseguire la query, e se questa va a buon fine 
            // allora viene mostrato un messaggio che lo indica..
            
            stmt.executeUpdate(sqlDelete);
            JOptionPane.showMessageDialog(null, "Prodotto rimosso con successo.");
        } catch (SQLException ex) {
            // .. altrimenti l'eccezione viene appositamente catturata e gestita. 
            
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Connection con = null; 
    private Statement stmt = null;
}
