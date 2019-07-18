package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe ha il compito di eliminare tutte le ordinazioni dall'omonima tabella. Questo 
 * lo si fa attraverso l'esecuzione di una query scritta in SQL che prima seleziona tutti 
 * i record della tabella che hanno l'id_t uguale a quello inserito, e poi li elimina. 
 * Il suo compito è molto semplice quindi, ed infatti il codice che ne deriva non è complesso, 
 * ma l'importanza che ha per il corretto funzionamento del programma è evidente, in quanto impedisce 
 * che ci siano sovrapposizioni di ordinazioni, "ripulendo" il tavolo. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class ClearOrder extends JDialog {
    /**
     * Funzione del metodo executeClearOrder è quella di appunto eliminare dalla tabella delle
     * ordinazioni tutti gli ordini che sono stati effettuati dal tavolo che ha come codice 
     * identificativo quell indicato in input. E' la query che si occupa di fare questo, quindi 
     * compito del metodo è solo quello di eseguirla correttamente. 
     * 
     * @param numTable Il parametro di input è l'intero che rappresenta il numero del tavolo della quale cancellare gli ordini già pagati. 
     */
    
    public void executeClearOrder (int numTable) {
        try {
            // Si tenta, in questo modo, di connettersi al database per permettere ovviamente 
            // l'esecuzione della query SQL. La connessione e tutte le eccezioni del caso 
            // vengono gestite dalla classe "ConnectionManager". 
            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            
            String sql = "DELETE FROM ordinazioni WHERE id_t = " + numTable;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // Semmai ci dovesse essere un problema con la cancellazione degli ordini relativi a quel tavolo, l'eccezione 
            // viene catturata e gestita, dato che viene mostrato un semplice messaggio in una finestra di dialogo che 
            // informa l'operatore che non vi è stata alcuna cancellazione, e indica anche l'entità del problema stesso. 
           
            JOptionPane.showMessageDialog(null, "Errore nel sistema di cancellazione.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Connection con = null; 
    private Statement stmt = null;
}
