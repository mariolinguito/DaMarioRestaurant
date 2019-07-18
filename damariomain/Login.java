package damariomain;

/**
 * La classe Login ha il semplice compito di capire quale utente sia stato scelto di 
 * far loggare, definire contesti in relazione a questa scelta, ed eseguire la 
 * strategia che si è scelto di adottare in relazione alla scelta dell'utente. Nel 
 * metodo startLogin, che avvia l'accesso, vie è un controllo IF-ELSE IF grazie al
 * quale il sistema capisce quale dei due tipi di utente sta per accedere, sviluppando 
 * una strategia apposita.
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class Login {
    /**
     * Il costruttore si occupa di inizializare le variabili di istanza con le informazioni 
     * che gli sono state date in input, ovvero l'ID e la Password dell'utente che vuole 
     * eseguire l'accesso, ed inoltre un parametro che indica quale utente deve eseguire 
     * l'accesso, ovvero se deve eseguirlo l'amministratore o il cameriere. 
     * 
     * @param id Questo indicato è un intero che indica lo username dell'utente 
     * che intende eseguire l'accesso. 
     * @param pass La stringa data in input rappresenta la parola chiave che l'utente 
     * utilizza come password per accedere all'applicazione. 
     * @param userIns Questo parametro indica al sistema quale dei due tipi di utente 
     * deve eseguire l'accesso, cosi da scegliere quale strategia utilizzare. 
     */
    
    public Login (int id, String pass, String userIns) {
        this.idIns = id; 
        this.passIns = pass; 
        this.userChose = userIns; 
    } 
    
    /**
     * Il metodo startLogin riesce a capire se l'utente che sta accedendo lo sta facendo come
     * ammnistratore o come cameriere, semplicemente attraverso un controllo preliminare. Se 
     * si sceglie di accedere come amministratore, allora si istanzia la classe LoginContext
     * passandogli come parametro di input l'istanza della classe che permette l'accesso come 
     * amministratore.. Lo stesso ragionamento lo si se si vuole eseguire l'accesso come 
     * cameriere. 
     * 
     * @return Il metodo ritorna un valore booleano, che sia falso se l'accesso è fallito, o vero nel caso contrario. 
     */
    
    public boolean startLogin () {
        // In un primo momento si intende capire, attraverso un blocco if-else if, 
        // quale tipo di utente far loggare nel sistema, se l'amministratore o il 
        // cameriere.
        
        if (userChose.equals("Admin")) {
            // Si crea un oggetto di tipo LoginContext scegliendo come strategia di 
            // accesso quella che riguarda l'amministratore. 
            
            context = new LoginContext(new AdminLogin());
            return context.executeStrategy(idIns, passIns); 
        } else if (userChose.equals("Waiter")) {
            // Altrimenti, se non è l'amministratore ad accedere, sarà sicuramente 
            // un cameriere, quindi si sceglie l'apposita strategia che permetterà 
            // all'utente di accedere nella sua sezione. 
            
            context = new LoginContext(new WaiterLogin()); 
            return context.executeStrategy(idIns, passIns); 
        }
        
        return false; 
    }
    
    private final int idIns;
    private final String passIns; 
    private final String userChose;
    private LoginContext context; 
}
