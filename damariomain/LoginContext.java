package damariomain;

/**
 * Attraverso la classe LoginContext viene caricato un oggetto di strategia concreta
 * utilizzando un riferimento all'interfaccia LoginStrategy per eseguire tale algoritmo
 * concreto. Tale classe definisce l'interfaccia per accedere ai membri dell'algoritmo
 * caricato.
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class LoginContext {
    public LoginContext (LoginStrategy strategy) {
        this.strategy = strategy; 
    }
    
    /**
     * Il metodo si occupa di eseguire la strategia scelta dall'utente, ed infatti ha come 
     * parametri di input l'ID dell'utente, e la password inserita al momento dell'accesso. 
     * 
     * @param id Il parametro è un intero che rappresenta lo username dell'utente che intende eseguire l'accesso. 
     * @param pass La password è una stringa che data in input sarà confrontata con quella contenuta nel 
     * database per verificare le credenziali dell'utente. 
     * @return Il metodo ritorna un valore booleano che indica l'esito dell'accesso. 
     */
    
    public boolean executeStrategy (int id, String pass) {
        return strategy.doLogin(id, pass);
    }
    
    private final LoginStrategy strategy;
}
