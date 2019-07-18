package damariomain;

/**
 * Viene in questo modo dichiarata l'interfaccia di riferimento per tutti gli 
 * algoritmi concreti, ovvero sia per tutte quelle classi che la implementano. 
 * Nel caso quivi mostrato le classi che implementano tale interfaccia sono 
 * WaiterLogin e AdminLogin, che vengono definite strategie concrete. Se infatti
 * volessimo prevedere un altro tipo di utente, come il cliente, allora ci sarebbe 
 * bisogno soltanto di creare una nuova classe che implementa l'interfaccia appena 
 * sotto definita. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public interface LoginStrategy {
    /**
     * Il metodo doLogin è un metodo che restituisce un valore booleano 
     * che permette di capire se il login è andato a buon fine o meno. 
     * tale metodo prende in input un intero che equivale allo user e una 
     * stringa che equivale alla password corrispondente a quello stesso user. 
     * 
     * @param id Lo username è indicato come un intero. 
     * @param pass La password invece è una stringa, ed anch'essa viene fornita in input. 
     * @return Il metodo ritorna un valore booleano che indica il successo o meo del login. 
     */
    
    public boolean doLogin (int id, String pass); 
}
