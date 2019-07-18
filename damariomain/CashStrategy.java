package damariomain;

import javax.swing.*;

/**
 * Cosi come le altre che si occupano della stessa cosa, anche in questa classe 
 * si intende sviluppare un altro metodo di pagamento, ovvero quello che prevede 
 * l'utilizzo di contanti. Appunto per questo, ovvero per il fatto che tale metodo di 
 * pagamento non è tracciato in alcun modo, risulta essere una classe strutturalmente 
 * e logicamente molto semplice, ed infatti ciò che viene mostrat è un semplice 
 * messaggio di dialogo che informa dell'avvenuto pagamento (o di un errore) mostrando
 * anche il totale pagato da quello specifico tavolo. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class CashStrategy extends JDialog implements PaymentStrategy {
    /**
     * La strategia per il pagamento in contanti non mostra alcuna particolare difficoltà logica, 
     * infatti non appena viene richiamato il metodo "pay" viene mostrato un semplice messaggio 
     * che informa l'utente che l'operazione si è conclusa con successo, mostrando anche la somma 
     * pagata per quell'ordine. Nonostante la semplicità strutturale della classe, si deve comunque 
     * controllare che l'ammontare delle spese per quel tavolo siano maggiori di 0.0, poichè non si 
     * può effettuare il pagamento per un tavolo che non ha effettuato alcun ordinazione. 
     * 
     * @param amount Il parametro dato in input al metodo è l'ammontare di tutte le spese accumulate da un tavolo 
     * (durate il quale ha effettuato gli ordini).
     */
    
    @Override
    public void pay(double amount) {
        if (amount > 0.0) {
            JOptionPane.showMessageDialog(null, "Pagamento effettuato con Contanti. \nSomma pagata: " 
                + amount, "Complete", JOptionPane.INFORMATION_MESSAGE);
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(null, "Errore nell'inserimento / Il tavolo inserito è errato.", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }
}
