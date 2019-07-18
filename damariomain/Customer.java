package damariomain;

import java.util.Random;

/**
 * La classe si occupa semplicemente di simulare un cliente, generando un numero 
 *  casuale che va da uno fino a dieci (numero fisico di tavoli disponibili nel 
 *  ristorante). Restituisce infine questo numero intero, che da ora in poi sarà 
 *  il cliente da servire. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class Customer { 
    /**
     * Il metodo si occupa di generare e restituire un intero compreso tra 1 e 10, 
     * in modo da simulare l'ordinazione (anch'essa casuale) da parte di un cliente. 
     * 
     * @return Ciò che è ritornato dal metodo è un intero casuale compreso in un range di 1 a 
     * 10, che equivale al numero di tavoli disponibili. 
     */
    
    public int getRandomTableNumber () {
        Random randomTableNumber = new Random(); 
        return randomTableNumber.nextInt(10-1) + 1; 
    }
}
