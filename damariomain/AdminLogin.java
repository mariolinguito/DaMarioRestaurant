package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe che è funzionale a gestire l'accesso dell'amministratore di sistema 
 * implementa l'interfaccia LoginStrategy, che contiene la firma del metodo doLogin, 
 * ovvero sia quel metodo che praticamente permette il controllo dei dati e l'accesso 
 * all'area riservata all'amministratore. Ovviamente si vanno a selezionare con una 
 * semplice query in SQL quelle che sono le credenziali di accesso dell'amministratore, 
 * e le si salvano i due variabili, che saranno poi confrontate con le informazioni 
 * immesse dall'utente. Se queste sono esatte, allora si prosegue normalmente, 
 * altrimenti compare un messaggio di errore che indica che una delle due credenziali 
 * è errata. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class AdminLogin implements LoginStrategy {
    /**
     * Il metodo si occupa di verificare che i dati inseriti dall'utente (che sta effettuanto l'accesso)
     * e quelli conservati nel database siano coincidenti. Se questo si verificasse, allora tale metodo
     * restiuisce un valore booleano TRUE, e come è ovvio pensare, nel caso in cui ci fossero disuguaglianze 
     * tra i dati inseriti e quelli nella tabella, il metodo restituisce un valore booleano FALSE.
     * 
     * @param id Un intero che rappresenta lo username inserito dall'utente.
     * @param pass Una stringa che rappresenta invece la password inserita dall'utente per effettuare l'accesso.
     * @return Il metodo ritorna un valore booleano che indica il successo o meno dell'accesso. 
     */
    
    @Override
    public boolean doLogin(int id, String pass) {
        try {
            // In questo modo viene eseguito l'accesso al database, stabilendo una connessione
            // con lo stesso. 
            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            
            // Andiamo a selezionare tutto dalla tabella "amministratore", ed in particolare 
            // andiamo ad estrarre lo user ID e la password dalla stessa tabella, essendo campi 
            // che la compongono. Queste informazioni vengono salvate in due variabili, la variabile
            // "idAdmin", e quella "passAdmin". 
                
            String sql = "SELECT * FROM amministratore";
            rs = stmt.executeQuery(sql);
            
            rs.next();
            int idAdmin = rs.getInt(1);
            String passAdmin = rs.getString(2); 
                
            // Le due variabili vengono confrontate con gli elementi inseriti dagli utenti, 
            // e se sono verificate entrambe le condizioni (attraverso un blocco if-else), allora, 
            // si procede regolarmente, altrimenti si restituisce un messaggio di errore. 
                
            if (id == idAdmin && pass.equals(passAdmin)) {
                Admin admin = new Admin();
                LoginResult = true; 
            } 
        } catch (Exception  e) {
            // Se una qualche operazione contenuta nel blocco Try non dovesse andare a buon 
            // allora l'eccezione viene gestita da questo secondo blocco. Il parametro che 
            // utilizziamo in questo caso è polimorfo, nel senso che gestisce ogni tipo di 
            // eccezione senza distinzione alcuna. 
            
            JOptionPane.showMessageDialog(null, "Errore nell'accesso come amministratore.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return LoginResult; 
    }
    
    private Connection con = null; 
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private boolean LoginResult;
}
    
