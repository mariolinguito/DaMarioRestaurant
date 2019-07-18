package damariomain;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * La classe no faaltro che visualizzare in una tabella apposita una lista di tutti i 
 * prodotti che vi sono nel menu, indicandone l'ID, il nome, la categoria del prodotto, 
 * il prezzo di quest'ultimo, il costo e la quantità restante. Sarà poi inserita nella 
 * finestra che gestisce l'Admin Panel, in modo da permettere all'amministratore di 
 * gestire tutto quanto.  
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class MenuList extends JDialog {
    /**
     * Cosi come anche diversi altri costruttori per le classi che si occupano quasi della
     * medesima cosa, anche in questo caso, per poter eseguire delle operazioni sui dati 
     * contenuti nel database, bisogna innanzitutto stabilire una connessione con esso. Questo 
     * viene fatto nel costruttore di questa classe. 
     */
    
    public MenuList () {
        try {
            // Come sempre si esegue prima di tutto la connessione al database, se questa non
            // dovesse andare a buon fine, allora si cattura l'eccezione e la si gestisce 
            // correttamente mostrando un messaggio di errore con tutte le informazioni del caso. 
            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Il metodo si occupa di selezionare tutti i prodotti dalla tabella con il medesimo nome
     * e di inserirli nella tabella il cui modello è stato definito preliminarmente. E' infatti 
     * attraverso il modello che definiamo le colonne della tabella attribuendogli un nome in 
     * modo da rendere il programma il più pulito possibile. 
     * 
     * @return Il ritorno del metodo è un modello che potrà essere usato per costruire e visualizzare 
     * la stessa tabella in una finestra. 
     */
    
    public DefaultTableModel getMenuModel () {
        try {
            // Si definisce un modello per la tabella che andrà ad essere implemetata nella classe 
            // Admin che costituisce il centro di controllo dell'amministratore. Il modello lo si 
            // definisce per dare un identificativo alle colonne che compongono la tabella, e per 
            // poter aggiungervi quegli elementi recuperati dalla tabella dei prodotti. 
            
            menuTable = new DefaultTableModel(); 
            menuTable.setColumnIdentifiers(columnNames);
            
            String sqlMenu = "SELECT * FROM prodotti";
            rs = stmt.executeQuery(sqlMenu); 
            
            while (rs.next()) {
                // Il ciclo while si occupa di selezionare ttti gli elementi contenuti nella tabella 
                // ed infatti termina nel momento in cui viene recuperato l'ultimo elemento che la 
                // popola. Queste informazioni vengono quindi salvate in delle variabili ed in seguito 
                // aggiunte alla tabella come righe. 
                
                idProduct = rs.getInt(1); 
                nameProduct = rs.getString(2); 
                categoryProduct = rs.getString(3); 
                priceProduct = rs.getDouble(4);
                quantityProduct = rs.getInt(5);
                
                menuTable.addRow(new Object[]{idProduct, nameProduct, categoryProduct, priceProduct, quantityProduct});
            }
        } catch (SQLException e) {
            // Se questo non dovesse andare a buon fine, allora viene generata una finestra di dialogo nella quale 
            // viene mostrato anche l'errore che si è verificato. 
            
            JOptionPane.showMessageDialog(null, "Errore. Operazione non eseguita.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } 
        
        return menuTable; 
    }
            
    private int idProduct, quantityProduct; 
    private String nameProduct, categoryProduct; 
    private double priceProduct; 
    private DefaultTableModel menuTable;
    
    // Per poter dare un nome alle colonne, si definisce un vettore di stringhe che contiene appunti 
    // i nomi delle colonne della tabella. 
    
    private final static String [] columnNames = {"ID Prodotto", "Nome Prodotto", "Categoria Prodotto", "Costo Prodotto", "Quantità Prodotto"};
    
    Connection con = null; 
    Statement stmt = null;
    ResultSet rs = null;
}
