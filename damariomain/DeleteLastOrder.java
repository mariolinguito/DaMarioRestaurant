package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe si occuapa di gestire una delle operazioni fondamentali che il cameriere 
 * può svolgere, ovvero quella di eliminare l'ultimo ordine che è stato effettuato. Per 
 * fare questo si usa ancora una volta la connessione al database e la manipolazione dei 
 * dati in esso contenuti tramite una query SQL. In questo caso però ne sono due, evitando 
 * in tal modo di sfruttare una query complessa come sarebbe potuta essere invece una innestata.
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class DeleteLastOrder extends JDialog {
    /**
     * Uno dei compiti dati al cameriere è quello di poter avere la possibilità di cancellare
     * l'ultimo ordine che ha effettuato. Questo lo si fa con due semplici query SQL, infatti 
     * la prima query va semplicemente a selezionare dalla tabella delle ordinazioni quella 
     * ordinazione il cui ID è il più grande, ma solo tra le ordinazioni fatte da quel cameriere 
     * identificato dal suo codice univoco. Avendo recuperato questa informazione, la seconda 
     * query si occupa di eliminare quella ordinazioni che ha come codice identificativo il 
     * risultato stesso dell'esecuzione della prima query SQL. Una volta completato il tutto viene 
     * mostrato un messaggio che indica l'avvenuta cancellazione, nel caso contrario, il messaggio
     * mostrato è di errore. 
     * 
     * @param waiterID Il parametro di input è il codice del cameriere, essenziale per l'esecuzione 
     * delle query SQL contenute nel metodo. 
     */
    
    public void executeDeleteLastOrder (int waiterID) {
        try {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            
            // In particolare la prima query si occupa di selezionare l'elemento il cui id_o è massimo dalla 
            // tabella delle ordinazioni li dove l'id_c, ovvero il codice del cliente è quello che è 
            // stato inserito, ovvero del cameriere che sta eseguendo l'operazione. 
            
            String sqlSelection = "SELECT MAX(id_o) FROM ordinazioni WHERE id_c = " + waiterID;
            rs = stmt.executeQuery(sqlSelection);
            rs.next(); 
            
            // Si salva l'elemento selezionato in una variabile di tipo int, per poter essere utilizzata
            // nella seconda query. 
            
            int maxID_O = rs.getInt(1); 
            
            // La seconda query si occupa appunto di eliminare dalla tabella delle ordinazioni quell'elemento 
            // il cui codice id_o, ovvero il codice dell'ordinazione fatta, è uguale a quello contentuto nella 
            // variabile maxID_O, ovvero ancora uguale al risultato della prima query. 
            
            String sqlDeleting = "DELETE FROM ordinazioni WHERE id_o = " + maxID_O; 
            stmt.executeUpdate(sqlDeleting); 
            
            // Una volta eseguita la query, se questa va a buon fine, allora viene mostrato un messaggio in una 
            // finestra di dialogo che ne indica l'avvenuta esecuzione con successo.. 
            
            JOptionPane.showMessageDialog(null, "Ultimo ordine annullato con successo..", "Complete", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            // .. Mentre invece, se per un qualsiasi problema, non dovesse andar bene l'esecuzione della query SQL, allora l'eccezione 
            // viene catturata e gestita mediante un semplice messaggio mostrato in una finestra, che specifica anche 
            // l'entità dell'errore che si è verificato. 
            
            JOptionPane.showMessageDialog(null, "Errore nel sistema di cancellazione.\nInfo: " + 
                    e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Connection con = null; 
    private Statement stmt = null;
    private ResultSet rs = null; 
}
