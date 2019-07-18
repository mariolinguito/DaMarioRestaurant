package damariomain;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Per poter permettere all'amministratore del sistema di servire un tavolo, e 
 * quindi di eseguire il pagamento di un conto (con un totale definito di ordini 
 * a carico), viene definito un Prompt, ovvero sia una finestra nella quale vi è
 * una text field e tre bottoni, oguni dei quali corrisponde ad un tipo di pagamento 
 * scelto dall'utente di effettuare. Nella casella di testo viene inserito il numero 
 * del tavolo che si vuole servire, e premendo uno dei tre bottoni di cui poco prima 
 * si è trattato, viene generato un evento, quello cioè di procedere al pagamento 
 * seguendo quella strategia richiesta. Infatti per implementare il sistema dei 
 * pagamenti, si è utilizzato per l'appunto il Design Pattern Strategy, nella quale
 * sono le stesse modalità di pagamento ad essere le strategie. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class ExecutePaymentPrompt extends JDialog {
    /**
     * Il metodo costruisce e mostra la finestra di dialogo necessaria all'utente per poter 
     * inserire il numero identificativo del tavolo da processare e poter quindi scegliere il 
     * metodo di pagamento per poterlo fare. Per poter gestire il posizionamento degli elementi
     * che compongono la finestra si utilizza un Layout Manager, ovvero quello del GridBagLayout, 
     * cosi che, attraverso la manipolazion di alcuni indici, è possibile mostrare gli elementi 
     * nella finestra. 
     */
    
    public ExecutePaymentPrompt () {
        // Così come fatto in diversi altri casi durante lo sviluppo del codice, viene 
        // utilizzato uno specifico Layout Manager, quello del GridBagLayout, che 
        // divide la finestra in una griglia sulla quale posizionare poi gli elementi
        // manipolando i relativi indici.
        
        JPanel panel = new JPanel (new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        // Attraverso un label diamo indicazioni all'utente per rendere più funzionale 
        // e intuitivo il programma. Inoltre posizioniamo questo elemento in una 
        // specifica cella della finestra creata dal Layout Manager che è stato scelto 
        // di usare. Questo lo si fa manipolando gli indici x, y e width. Lo stesso 
        // viene fatto con gli altri elementi che abbiamo creato e che devono essere 
        // posizionati nelle celle della griglia.
        
        lbTable = new JLabel("Inserire il numero del tavolo da servire: "); 
        cs.gridx = 0; cs.gridy = 0; cs.gridwidth = 1; 
        panel.add(lbTable, cs); 
        
        txTable = new JTextField(2);
        cs.gridx = 1; cs.gridy = 0; cs.gridwidth = 2; 
        panel.add(txTable, cs);
                
        // Un errore comune che si commette quando si lavora con le Text Field sta nella 
        // conversione di ciò che viene inserito in questo campo di testo. Se infatti il 
        // campo è vuoto, allora non può essere convertito nulla, e il compilatore 
        // restituisce un errore. Si deve quindi poter catturare in "tempo reale" la 
        // pressione di un tasto in quella specifica Text Field. Si converte poi 
        // il contenuto di quella casella in un intero, per poter essere utilizzato come 
        // parametro di input e per poter svolgere le successive operazioni.
        
        txTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txTable = (JTextField) e.getSource();
                String numTableString = txTable.getText().trim();
                numTableInt = Integer.parseInt(numTableString);
            }
        });
        
        // Ogni bottone corrisponde ad un metodo di pagamento, ed ovviamente, ognuno di essi 
        // corrisponde ad un ascolatore che capta la pressione del tasto ed associa a questo 
        // evento un'azione, che in questo caso è il pagamento del conto di quel tavolo.
            
        JButton cashMachineS = new JButton("Bancomat"); 
        JButton cashS = new JButton("Contanti");
        JButton creditCardS = new JButton("Carta di credito");
                
        cashMachineS.addActionListener((ActionEvent e) -> {
            // Essendo che abbiamo bisogno di aggiungere nuove informazioni per poter eseguire il pagamento con
            // un bancomat, come il numero e il codice di sicurezza, bisogna necessariamente creare una nuova
            // finestra di dialogo, attraverso il quale inserire le informazioni persnali e procedere con il
            // pagamento del conto.
            
            new ExecutePayment(numTableInt).pay(new CashMachineStrategy());
            dispose();
        });
        
        cashS.addActionListener((ActionEvent e) -> {
            new ExecutePayment(numTableInt).pay(new CashStrategy());
            dispose();
        });
        
        creditCardS.addActionListener((ActionEvent e) -> {
            // Lo stesso ragionamento vale anche per il metodo di pagamento con una carta di credito. Questo
            // perchè bisogna aggiungere delle informazioni riguardanti la carta stessa, come il numero della
            // carta, il codice PIN, e la data di scadenza della stessa. Una volta inserite queste informazioni
            // si può procedere con il pagamento.
            
            new ExecutePayment(numTableInt).pay(new CreditCardStrategy());
            dispose();
        });
        
        // Dichiariamo a questo punto un nuovo pannello sulla quale mostrare tutti gli elementi che sono 
        // stati dichiarati e creati precedentemente. Ed in particolare, vengono aggiunti i bottoni 
        // che servono per la scelta del metodo di pagamento. 
        
        JPanel bpMethod = new JPanel(); 
        bpMethod.add(cashMachineS); 
        bpMethod.add(cashS); 
        bpMethod.add(creditCardS);
        
        // Per motivazioni grafiche inseriamo il pannello che gestisce la label e la text field nella
        // parte superiore della finestra, mentre invece i bottoni che riguardano le strategie di 
        // pagamento vengono posizionate nella parta inferiore, utilizzando il Layout Manager BorderLayout. 
        
        getContentPane().add(panel, BorderLayout.CENTER); 
        getContentPane().add(bpMethod, BorderLayout.PAGE_END);
        
        // Infine facciamo in modo che le dimensioni della finestra principale si adattino a quelle 
        // degli elementi in essa contenuti, evitando che possano variare (con il metodo setResizable 
        // settato a false) e mostrando il tutto (con il metodo setVisible settato a true).
        
        pack(); 
        setResizable(false); 
        setVisible(true);  
    }
    
    private final JLabel lbTable;
    private JTextField txTable;
    private int numTableInt;
}
