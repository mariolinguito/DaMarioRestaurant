package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * Le operazioni di aggiornamento del menu, ovvero l'aggiornamento dei campi della 
 * tabella "prodotti" vengono eseguite attraverso i metodi di questa classe. Le modifiche 
 * avvengono seguendo la stessa procedura utilizzata nelle altre classi che si occupano
 * quasi delle medesime operazioni. Ci si connette in un primo luogo al database che 
 * immagazina i dati dell'applicazione, poi, in secondo luogo, si esegue la query scritta in
 * SQL, che si occupa di manipolare i dati contenuti nelle tabelle.
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class MenuOperationUpdate extends JDialog {
    public MenuOperationUpdate () {
        try {            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Come ci suggersce il nome, il metodo svolge la funzione di modificare il nome del prodotto
     * identificato dal suo codice ID univoco, con un nome inserito dall'utente e che è stato 
     * ovviamente dato in input. Questo lo si fa eseguendo una query che ha il compito di manipolare
     * le informazioni relative a quel prodotto specifico. 
     * 
     * @param codProduct Ciò che è dato in input è un parametro che indica il codice identificativo 
     * del prodotto, per poterlo selezionare dalla tabella. 
     * @param nProduct Inoltre è data come parametro di input anche una stringa che ne indica il 
     * nome che andrà a sostituire quello già presente
     * nella tabella. 
     */
    
    public void modProductName (int codProduct, String nProduct) {
        sqlUpdate = "UPDATE prodotti SET nome_p = '" + nProduct + "' WHERE id_p = " + codProduct;
        try {
            stmt.executeUpdate(sqlUpdate);
            JOptionPane.showMessageDialog(null, "Nome del prodotto modificato.");
        } catch (SQLException ex) {
            // Nel caso in cui la query non venga eseguita correttamente, viene mostrato un 
            // messaggio d'errore. 
            
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Il metodo ha la funzione di modificare la categoria del prodotto identificato da un codice
     * ID dato in input al metodo stesso. Per farlo sfrutta una query SQL che aggiorna la categoria
     * di quel prodotto identificato univocamente. 
     * 
     * @param codProduct Il primo parametro di input è un intero che rappresenta il codice ID del 
     * prodotto da modificare. 
     * @param cProduct Il secondo parametro di input rappresenta la stringa che indica la categoria 
     * con la quale si vuole modificare quella pre-esistente. 
     */
    
    public void modProductCategory (int codProduct, String cProduct) {
        sqlUpdate = "UPDATE prodotti SET categoria_p = '" + cProduct + "' WHERE id_p = " + codProduct;
        try {
            stmt.executeUpdate(sqlUpdate);
            JOptionPane.showMessageDialog(null, "Categoria del prodotto modificata.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Il metodo ha il compito di modificare il prezzo del prodotto il cui codice è dato in 
     * input al metodo stesso, così come in input è dato il prezzo che andrà a sostituire 
     * quello già contenuto nella tabella in corrispondenza di quella riga. 
     * 
     * @param codProduct Rappresenta il codice univoco assegnato al prodotto per poter eseguire la query in modo preciso.
     * @param pProduct Rappresenta il double che indica il prezzo del prodotto che andrà a sostiuire quello precedente. 
     */
    
    public void modProductPrice (int codProduct, double pProduct) {
        sqlUpdate = "UPDATE prodotti SET costo_p = '" + pProduct + "' WHERE id_p = " + codProduct;
        try {
            stmt.executeUpdate(sqlUpdate);
            JOptionPane.showMessageDialog(null, "Prezzo del prodotto modificato.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Il metodo va ad aggiornare il campo riguardante la quantità del prodotto che ha come 
     * codice identificativo ID quello specificato in input. Lo si fa questo attraverso una 
     * query SQL che va a sostituire la quantità attuale con quella specificata. 
     * 
     * @param codProduct Il primo parametro di input rappresenta il codie del prodotto che si vuole aggiornare. 
     * @param qProduct Il secondo parametro di input rappresenta la quantità (numero intero) che andrà ad aggiornare il campo. 
     */
    
    public void modProductQuantity (int codProduct, float qProduct) {
        sqlUpdate = "UPDATE prodotti SET quantita_v = '" + qProduct + "' WHERE id_p = " + codProduct;
        try {
            stmt.executeUpdate(sqlUpdate);
            JOptionPane.showMessageDialog(null, "Quantità del prodotto modificata.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private Connection con = null; 
    private Statement stmt = null;
    private String sqlUpdate; 
}
