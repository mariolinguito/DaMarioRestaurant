package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe ha la funzione di aggiungere un nuovo prodotto alla tabella "prodotti". Per poter 
 * eseguire tali operazioni, però, c'è bisogno di eseguire prima la connessione al database, così
 * come avviene per ogni classe che esegue operazioni su qualche tabella. Tale connessione la 
 * si esegue nel costruttore della classe, mentre l'aggiunta del prodotto avviene tramite il metodo
 * "addProduct()", che prende in input le informazioni che devono ovviamente essere inserite nella 
 * tabella.
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class MenuOperationAdd extends JDialog {
    /**
     * In questo modo si esegue la connessione al database, ed in particolare la 
     * sfruttiamo tale connessione per poter gestire i dati all'interno delle 
     * tabelle che popolano il database. Questo lo si fa per ogni classe che sfrutta
     * la manipolazione di dati nelle tabelle del database. 
     */
    
    public MenuOperationAdd () {
        try {            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement(); 
        } catch (Exception e) {
            // Semmai la connessione al database non dovesse andare a buon fine, allora l'eccezione 
            // viene catturata e semplicemente gestita restituendo un messaggio di errore. Così 
            // come avviene anche per le altre classi "MenuOperationDelete" e "MenuOperationUpdate".
            
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * L'aggiunta di un prodotto viene gestita dal metodo che sfrutta una query scritta
     * in SQL per poter eseguire l'inserimento delle informazioni che sono date in input 
     * al metodo stesso. Se l'inserimento dovesse andare a buon fine, allora il metodo 
     * produce un messaggio informativo di avvenuto inserimento, altrimenti il messaggio 
     * che è mostrato è di errore. 
     * 
     * @param nProduct In input viene data la stringa che rappresenta il nome del prodotto da inserire.
     * @param cProduct Cosi come viene data la stringa che ne indica la categoria.  
     * @param pProduct Il terzo parametro di input indica invece il prezzo in double del prodotto che si sta inserendo. 
     * @param qProduct Mentre il quarto parametro rappresenta un intero che indica la quantità disponibile del prodotto. 
     */
    
    public void addProduct (String nProduct, String cProduct, double pProduct, int qProduct) {
        // La query SQL sviluppata si occupa di inserire un nuovo prodotto nel database dato il nome del prodotto, la categoria, il costo del prodotto 
        // e la quantità che si ha in magazzino (o in cucina). Si tenta di eseguire la query, e se questa va a buon fine allora viene mostrato un 
        // messaggio che indica l'avvenuto inseriemnto del prodotto. Nel caso contrario, invece, viene mostrato un messaggio di errore. 
        
        String sqlAdd = "INSERT INTO prodotti (nome_p, categoria_p, costo_p, quantita_v) VALUES ('" + nProduct + "', '" + cProduct + "', " + pProduct + ", " + qProduct + ")";
        
        try {
            stmt.executeUpdate(sqlAdd);
            JOptionPane.showMessageDialog(null, "Prodotto aggiunto.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Connection con = null; 
    private Statement stmt = null;
}
