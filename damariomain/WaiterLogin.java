package damariomain;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Cosi come per la classe AdminLogin, anche in questo caso si implementa l'interfaccia, 
 * che possiede in se la firma del metodo doLogin, che prende in input l'ID dell'utente
 * che intende accedere, ovvero lo username, e la password scelta. Ci si connette al database
 * poi, per poter recuperare le informazioni dalla tabella dei camerieri, e si confrontano 
 * queste informazioni con i dati inseriti dall'utente stesso. Nel caso in cui l'operazione 
 * non dovesse andare a buon fine, allora viene generata un'eccezione che deve essere 
 * necessariamente gestita, e lo si fa questo mostrando una finestra di errore nella quale vi 
 * è anche riportato un messaggio relativo all'entità dell'errore stesso. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class WaiterLogin implements LoginStrategy {
    /**
     * Il metodo permette ad un utente di accedere all'applicazione come cameriere,
     * inserendo uno username (numerico) e una password, che saranno poi confrontati 
     * con le credenziali di accesso conservate nel database. 
     * 
     * @param id E' un intero che equivale allo username dell'utente che intende accedere. 
     * @param pass E' la password che tale utente inserisce per poter effettuare l'accesso.
     * @return Il metodo ritorna un valore booleano che indica il successo o meno del login. 
     */
    
    @Override
    public boolean doLogin(int id, String pass) {
         try {
            // In questo modo viene eseguito l'accesso al database, stabilendo una connessione
            // con lo stesso. 
            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            
            // Lo stesso discorso vale per quando l'utente intende eseguire l'accesso come cameriere. Allora
            // in questo caso si opera sulla tabella dei camerieri, ed essendo che è popolata da molti più utenti
            // rispetto alla tabella degli amministratori, cìè bisogno di affinare la ricerca andando a 
            // selezionare solo quel cameriere che ha il codice id uguale a quello inserito all'inizio dall'utente. 
                
            String sql = "SELECT pass FROM camerieri WHERE id_c = " + id;
            rs = stmt.executeQuery(sql);
            
            rs.next();
            String passWaiter = rs.getString(1); 
                
            // Si verificano le stesse condizioni in precedenza verificate anche per l'amministratore, 
            // con la differenza che in questo caso dobbiamo verificare l'uguaglianza della sola password
            // essendo che è la query stessa a verificare il parametro di ID. 
                
            if (pass.equals(passWaiter)) {
                Waiter waiter = new Waiter(id);
                LoginResult = true; 
            }
        } catch (Exception  e) {
            JOptionPane.showMessageDialog(null, "Errore nell'accesso come cameriere.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return LoginResult; 
    }
    
    private Connection con = null; 
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private boolean LoginResult;
}
