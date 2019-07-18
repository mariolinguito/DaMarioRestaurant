package damariomain;

import java.sql.*; 
import javax.swing.*;

/**
 * La classe "ConnectionManager", come ci suggerisce il nome, permette la connessione 
 * diretta al database (creato precedentemente), e lo si fa con il metodo statico "getConnection()",
 * che restituisce come risultato una variabile "con" che stailisce la connessione stessa. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */


public class ConnectionManager {
    /**
     * Per permettere alle altre classi di stabilire la connessione al database che sta alla base
     * dell'appliazione, e quindi permettere l'esecuzione di query e la conseguente manipolazione 
     * di dati in esso contenuti, è necessario istanziare tale classe e quindi usufruire del metodo
     * quivi mostrato. 
     * 
     * @return Il metodo ritorna un oggetto di tipo Connection, necessario a permettere di stabilire la 
     * connessione per tutte quelle classi che ne fanno uso.  
     */
    
    public static Connection getConnection() {
        // Attraverso un blocco Try-Catch cerchiamo di stabilire la connessione al database 
        // attraverso le informazioni di accesso definire come variabili final. Nel caso in cui 
        // tale connessione non avvenisse, allora l'eccezione viene gestita dal blocco Catch, che 
        // si occupa di restituire un messaggio di errore. 
        
        try {
            Class.forName(JDBC_DRIVER);
            try {
                con = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Impossibile connettersi al database.\nInfo: " 
                        + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Il Driver non è stato trovato.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return con;
    }
    
    // Vengono definite le variabili statiche che contengono le informazioni per la 
    // connessione al database. 
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/daMario";    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
    private static final String USER = "root";   
    private static final String PASS = "root";
    private static Connection con;
}
