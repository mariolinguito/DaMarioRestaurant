package damariomain;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * La classe implementa l'interfaccia PaymentStartegy in modo da poter essere una strategia 
 * di pagamento che può essere scelta dall'utente al momento del saldo del proprio conto.
 * Ciò che la classe si adopera a sviluppare è l'interfaccia grafica che permette l'inserimento 
 * di quei dati che sono necessari al pagamento stesso, ovvero il numero della carta ed il
 * codice PIN della stessa. Infine viene mostrato un messaggio che comunica all'utente, in questo
 * caso l'amministratore, che il pagamento è andato a buon fine.. Nel caso contrario, allora si 
 * deve gestire l'eccezione che mostra un messaggio di errore, comunicando anche dettagli tecnici
 * riguardanti quest'ultimo. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class CashMachineStrategy extends JDialog implements PaymentStrategy { 
    /**
     * Il metodo, che è l'Override dell'omonimo metodo dichiarato nell'interfaccia PaymentStartegy, ha il compito
     * di permettere all'acquirente il pagamento attraverso il Bancomat, ed in particolare si occupa di costruire 
     * l'interfaccia grafica attraverso il quale l'utente può inserire le informazioni necessarie al pagamento del 
     * conto, e di mostrare un messaggio di avvenuto pagamento, oppure uno che indica l'errore che si è incontrato 
     * durante l'elaborazione del pagamento. Ovviamente, nel corso del pagamento vi sono una serie di verifiche 
     * attraverso il quale si capisce se il numero di carta inserito sia esattamente di 16 cifre, ed il PIN sia di 5. 
     * Oltre ciò, non si può effettuare il pagamento per un tavolo che non ha fatto alcuna ordinazione, quindi si 
     * deve anche controllare che l'ammontare delle spese per un tavolo sia superiore almeno allo 0.0. Se così non 
     * fosse allora verrebbe generato un errore che indica all'amministratore che qualcosa è andato storto. 
     * 
     * @param amount In input viene dato il totale che bisogna pagare attraverso il metodo di pagamento implementato 
     * in questo metodo. 
     */
    
    @Override
    public void pay(double amount) {
        // Rispetto al pagamento in contanti, per il pagamento tramite Bancomat abbiamo 
        // bisogno di altre informazioni aggiuntive, quali per esempio il numero di carta 
        // di credito, e il PIN della stessa. Queste informazioni vengono inserite tramite 
        // degli appositi campi di testo creati in SWING. 
        
        // Si comincia col creare un nuovo pannello gestito attraverso un Layout Manager che 
        // è quello del GridBagLayout, che (come già è stato fatto in precedenza per il login)
        // permette di gestire la finestra come se fosse composta da celle di uguale dimensione. 
        // In questo modo si vanno ad inserire gli elementi nelle apposite celle manipolando 
        // gli indici di x, y, e gridwidth. 
        
        JPanel panel = new JPanel (new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        // Gli elementi principali che andiamo ad inserire sono: 
        // 1. Il label che indica la funzione del campo di testo relativo all'inserimento
        //    del numero di carta, 
        // 2. Il campo di testo per l'inserimento del numero di carta relativo al conto 
        //    che si vuole utilizzare per il pagamento.
        
        lbCreditCardNum = new JLabel("Numero di Carta: "); 
        cs.gridx = 0; cs.gridy = 0; cs.gridwidth = 1; 
        panel.add(lbCreditCardNum, cs); 
        
        txCreditCardNum = new JTextField(16);
        cs.gridx = 1; cs.gridy = 0; cs.gridwidth = 2; 
        panel.add(txCreditCardNum, cs);
        
        // Ovviamente, come già fatto per quanto riguarda il numero della carta da utilizzare, 
        // a questa carta è associato un codice univoco, che è inserito tramite gli elementi: 
        // 1. Il label indica il fatto che quel campo di testo è stato creato per 
        //    l'inserimento del PIN della carta, 
        // 2. Il campo di testo per il PIN è particolare in quanto, essendo l'informazione 
        //    molto sensibile, è coperta dai ben noti pallini neri usati per le password. 
        
        lbCreditCardPin = new JLabel("Pin di Sicurezza: "); 
        cs.gridx = 0; cs.gridy = 1; cs.gridwidth = 1; 
        panel.add(lbCreditCardPin, cs); 
        
        txCreditCardPin = new JPasswordField(16);
        cs.gridx = 1; cs.gridy = 1; cs.gridwidth = 2; 
        panel.add(txCreditCardPin, cs);
        
        // Quando l'utente clicca sul bottone per procedere al pagamento, allora si genera 
        // un evento che viene catturato e gestito da un ascoltatore. 
        
        JButton btPay = new JButton("Paga adesso");
        
        // E' infatti l'ActionListener che gestisce l'evento ed associa a questo un'azione da
        // compiere. Ovviamente, essendo l'intento dell'utente quello di pagare il conto del 
        // suo tavolo, una volta cliccato sul bottone, viene mostrata una finestra di dialogo che 
        // indica l'avvenuto pagamento, indcando la somma pagata, e il codice della carta. Infine
        // la finestra viene chiusa con il comando "dispose()". 
        
        btPay.addActionListener((ActionEvent e) -> {
            if (txCreditCardNum.getText().length() == 16 && txCreditCardPin.getText().length() == 5 && amount > 0.0) {
                JOptionPane.showMessageDialog(null, "Pagamento effettuato con Bancomat. \nSomma pagata: " 
                    + amount, "Complete", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Errore nell'inserimento / Il tavolo inserito è errato.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        });
        
        // La finestra viene sempre gestita in due sezioni, ed infatti la prima sezione riguarda
        // i label e i campi di testo, mentre invece la seconda sezione (gestita da un altro pannello) 
        // si occupa di mostrare i relativi bottoni, ovvero i bottone per il pagamento in questo caso. 
        
        JPanel bp = new JPanel(); 
        bp.add(btPay); 
        
        getContentPane().add(panel, BorderLayout.CENTER); 
        getContentPane().add(bp, BorderLayout.PAGE_END);
        
        // La finestra la si può gestire in base alle proprie esigenze. In questo caso la finestra viene 
        // mostrata al centro, e viene eliminata la possibilità di modificare la sua dimensione (questo 
        // è molto importante dato che le modifiche alle dimensioni della finestra provocherebbero delle 
        // malformazioni agli elementi che la compongono). 
        
        pack(); 
        setResizable(false); 
        setVisible(true); 
    } 
    
    private JLabel lbCreditCardNum, lbCreditCardPin; 
    private JTextField txCreditCardNum, txCreditCardPin; 
}
