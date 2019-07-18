package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe si occupa semplicemente di recuperare informazioni relative al 
 * cameriere attraverso un codice identificativo unico, dalla tabella che ha 
 * il compito di immagazinare i dati relativi a tutti i camerieri che lavorano 
 * nel ristorante. Per fare questo si sfruttano delle semplici query scritte in SQL 
 * per la selezione dei dati dalla tabella. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class WaiterInformation {
    /**
     * Abbiamo bisogno di conoscere il codice identificativo del cameriere per poter
     * recuperare le informazioni relative a questo, ovvero il nome e il cognome. 
     * 
     * @param idW Il parametro di input è proprio il codice del cameriere necessario 
     * al recupero delle informazioni. 
     */
    
    public WaiterInformation (int idW) {
        // Abbiamo bisogno di conoscere il codice identificativo del cameriere per poter
        // recuperare le informazioni relative a questo, ovvero il nome e il cognome.
        
        this.idWaiter = idW; 
        
        try {
            // Come in ogni classe che utilizza il database per la manipoazione di dati in 
            // esso contenuti, ci si connette in un primo luogo al database, stabilendo con 
            // esso una connessione. Nel caso questo non avvenisse, allora viene generato un
            // errore che viene gestito correttamente dal blocco CATCH (come in tutti gli altri 
            // casi del genere). 
            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Il metodo si occupa di recuperare il nome come informazione dalla tabella
     * che tiene traccia, ovvero conserva, i dati riguardanti tutti i camerieri
     * che lavorano nel ristorante. Per fare questo si utilizza una semplice query 
     * scritta in SQL che seleziona appositamente il nome della tabella e lo
     * restituisce come parametro di output. 
     * 
     * @return Il metodo ritorna una stringa che contiene il nome del cameriere. 
     */

    public String getName () {        
        try {     
            // La query non fa altro che selezionare dalla tabella dei camerieri
            // il nome del cameriere stesso. 
            
            String sqlName = "SELECT nome FROM camerieri WHERE id_c = " + idWaiter;
            rs = stmt.executeQuery(sqlName);
            rs.next();
            
            name = rs.getString(1); 
            return name; 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return name; 
    }
    
    /**
     * La funzione del metodo è quella di selezionare il cognome del cameriere dalla
     * tabella che conserva tutti i dati di tutti i camerieri che lavorano al ristorante. 
     * E' una query SQL che si occupa di fare questo, ed eseguirla significa recuperare tale 
     * informazione e restituirla alla fine. 
     * 
     * @return Il metodo ritorna la stringa che contiene il cognome del cameriere. 
     */
    
    public String getSurname () {        
        try {  
            // Mentre questa seconda query svolge quasi lo stesso compito di 
            // quella precednte, solo che in questo caso viene selezionato il 
            // cognome del cameriere. 
            
            String sqlSurname = "SELECT cognome FROM camerieri WHERE id_c = " + idWaiter; 
            rs = stmt.executeQuery(sqlSurname);
            rs.next();
            
            surname = rs.getString(1); 
            return surname; 
        } catch (SQLException ex) {
            // Ovviamente, se l'esecuzione della query non dovesse andare a buon fine, allora 
            // si generà un'eccezione, che sarà gestita correttamente dal blocco CATCH. Mostrando
            // le informazioni riguardanti l'errore generato. 
            
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        // Se non viene generato alcun errore, allora si restituisce come ovvio che sia la stringa 
        // che contiene il cognome del cameriere alla quale si fa riferimento. 
        
        return surname; 
    }
    
    private int idWaiter;
    private String name, surname; 
    
    Connection con = null; 
    Statement stmt = null;
    ResultSet rs = null; 
}

