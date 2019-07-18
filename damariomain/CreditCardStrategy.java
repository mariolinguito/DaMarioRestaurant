package damariomain;

// Dobbiamo necessariamente importare le librerie che sono necessarie per il corretto 
// funzionamento della classe. In pratica vengono importare le librerie per l'interfaccia
// grafica e per la gestione degli eventi determinati da quegli stessi elementi. 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * La classe implementa l'interfaccia PaymentStrategy dato che è volta a definire un 
 * terzo metodo di pagamento concesso all'acquirente, ovvero il pagameto tramite la 
 * carta di credito. Il metodo pay, che è l'Override di quello gia definito nell'inter- 
 * faccia per l'appunto, si occupa di mostrare una finestra di dialogo (infatti la 
 * classe estende anche la superclasse JDialog) nella quale il cliente deve inserire i 
 * dati relativi alla sua carta di credito. Fatto questo, viene mostrato un messaggio di 
 * avvenuto pagamento, altrimenti il messaggio mostrato è quello di errore. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class CreditCardStrategy extends JDialog implements PaymentStrategy {
    /**
     * Come già detto appena precedentemente, il metodo ha la chiara funzione di costruire e mostrare 
     * la finestra attraverso il quale si devono inserire le informazioni per il pagamento, come 
     * il numero della carta di credito, il codice PIN e la data di scadenza della stessa. Il metodo 
     * inoltre verifica innanzitutto che le spese da pagare siano superiori almeno allo 0.0 e che 
     * il numero di carta di credito sia esattamente di 16 cifre, così come il codice PIN sia di 5. 
     * Solo superati questi controlli, si può procedere con il pagamento, nel caso contrario, viene 
     * fatto presente all'utente che c'è stato un errore di inserimento. 
     * 
     * @param amount Il parametro di input è un double che rappresenta l'ammontare delle spese sostenute 
     * dal tavolo che si vuole servire. 
     */
    
    @Override
    public void pay (double amount) {
        // Così come per il bancomat, anche per il pagamento tramite carta di credito abbiamo 
        // bisogno di più informazioni, quali il numero di carta di credito, il CCV, e la data di
        // scadenza della stessa. E allo stesso modo, definiamo degli elementi che ci permettono di 
        // inserire le informazioni richieste. 
        
        JPanel panel = new JPanel (new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        lbCreditCardNum = new JLabel("Numero di Carta: "); 
        cs.gridx = 0; cs.gridy = 0; cs.gridwidth = 1; 
        panel.add(lbCreditCardNum, cs); 
        
        txCreditCardNum = new JTextField(16);
        cs.gridx = 1; cs.gridy = 0; cs.gridwidth = 2; 
        panel.add(txCreditCardNum, cs);
        
        lbCreditCardCvv = new JLabel("CVV di Sicurezza: "); 
        cs.gridx = 0; cs.gridy = 1; cs.gridwidth = 1; 
        panel.add(lbCreditCardCvv, cs); 
        
        txCreditCardCvv = new JPasswordField(16);
        cs.gridx = 1; cs.gridy = 1; cs.gridwidth = 2; 
        panel.add(txCreditCardCvv, cs);
        
        // A differenza del bancomat, per poter pagare con una carta di credito, c'è bisogno 
        // anche di specificare la data di scadenza della stessa, ed infatti è questo quello 
        // che facciamo facendolo inserire dall'utente.
                
        lbCreditCardDate = new JLabel("Data di Scadenza: "); 
        cs.gridx = 0; cs.gridy = 2; cs.gridwidth = 1; 
        panel.add(lbCreditCardDate, cs); 
        
        txCreditCardDate = new JTextField(5);
        cs.gridx = 1; cs.gridy = 2; cs.gridwidth = 2; 
        panel.add(txCreditCardDate, cs);
        
        // Per dare delle indicazioni all'utente che deve inserire l'informazione, settiamo il contenuto
        // della casella con un esempio come "placeholder".
        
        txCreditCardDate.setText("12/18");
        
        // Dichiariamo un bottone che avrà la funzione di permettere all'acquirente di eseguire il pagamento,
        // infatti non appena viene premuto genera un evento gestito in modo automatico da un ActionListener, 
        // che si occupa di mostrare un messaggio in una finestra che informa l'utente che l'operazione
        // è stata conclusa con successo. 
        
        JButton btPay = new JButton("Paga adesso");
                
        btPay.addActionListener((ActionEvent e) -> {
            if (txCreditCardNum.getText().length() == 16 && txCreditCardCvv.getText().length() == 3 && amount > 0.0) {
                JOptionPane.showMessageDialog(null, "Pagamento effettuato con Carta. \nSomma pagata: " 
                    + amount, "Complete", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Errore nell'inserimento / Il tavolo inserito è errato.", "Error", 
                        JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        });
        
        JPanel bp = new JPanel(); 
        bp.add(btPay); 
        
        getContentPane().add(panel, BorderLayout.CENTER); 
        getContentPane().add(bp, BorderLayout.PAGE_END);
        
        pack(); 
        setResizable(false); 
        setVisible(true);
    }
    
    private JLabel lbCreditCardNum, lbCreditCardCvv, lbCreditCardDate; 
    private JTextField txCreditCardNum, txCreditCardCvv, txCreditCardDate; 
}
