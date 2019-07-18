package damariomain;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * La classe ha il compito di definire in tutte le sue funzioni il centro di controllo
 * dell'amminstratore del sistema, coincidente con la figura del proprietario del 
 * ristorante che si vuole gestire, in pratica colui che sta alla cassa. 
 * Alcuni tra i compiti dell'amministratore sono quelli di inserire o rimuovere un
 * prodotto nel menu, oppure anche aggiornarne le informazioni. Una volta che il 
 * cliente ha terminato il tutto, è compito dell'amministratore quello di procedere al 
 * pagamento del conto totale lasciando all'acquirente la scelta del metodo di 
 * pagamento preferito (Contanti, Bancomat, o Carta di Credito). Inoltre può anche
 * controllare quali sono i prodotti più venduti in quel momento in modo da poter 
 * rifornire quei prodotti che più vengono richiesti. 
 * Alcune operazioni importati, quali l'eliminazione di un prodotto o l'aggiornamento 
 * delle sue informazioni vengono fatte attraverso una cooperazione perfetta tra 
 * il menu mostrato nella finestra attraverso una tabella (in uno JScrollPane), e i 
 * bottoni legati ad Action Listener che si occupano di gestire gli eventi generati dalla
 * loro pressione. Questo permette all'Admin di tenere tutto sotto controllo. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class Admin {
    /**
     * Per poter figurare il centro di controllo dell'amministratore si crea un frame, che
     * sarà la nostra finestra, sulla quale inseriamo e modifichiamo ogni elemento che
     * comporrà l'interfaccia grafica del programma. Il Layout Manager utilizzato in questo 
     * caso è il GrdiBagLayout grazie al quale manipolando gli indici e alcuni altri 
     * constraint si riescono a posizionare quegli elementi funzionali all'admin stesso. 
     */
    
    public Admin () {        
        JFrame frame = new JFrame("Admin Control Panel"); 
        
        frame.setExtendedState(JFrame.MAXIMIZED_VERT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(false); 
        
        // In questo modo scegliamo uno specifico Layout Manger che ci permette di gestire gli elementi 
        // che verranno mostrati nella finestra. In questo modo l'amministratore potrà svolgere tutte le 
        // funzioni per il quale è stato predisposto in modo efficiente ed intuitivo. 
        // Il tipo di Layout che si è scelto è quello che rende la manipolazione degli elementi mostrati 
        // nella finestra più efficiente ed efficace: Il GridBagLayout, che suddivide la finestra in sezioni 
        // come se fosse una matrice, e nella quale possiamo inserire gli elementi manipolando gli indici x ed 
        // y. Per fare questo si definisce un GridBagConstraints, in modo da vincolare gli elementi in una 
        // determinata posizione. 
        
        frame.setLayout(new GridBagLayout());        
        frame.getContentPane().setBackground(Color.white);         
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Si intende adesso aggiungere quei bottoni che equivagono ad altrettante funzioni che può 
        // svolgere l'aggiornamento dei dettagli di un prodotto, l'aggiunta di un nuovo prodotto al 
        // menu, o anche la sua eliminazione. Inoltre, uno dei compiti fondamentali dell'admin è quello 
        // di poter procedere al pagamento per un determinato cliente (tavolo) in tre modalità diverse,
        // oppure anche poter visualizzare quali sono i prodotti più venduti (periodicamente). 
        
        JButton infoUpdate = new JButton("Aggiorna Prodotto"); 
        
        // Il bottone viene visualizzato in quella posizione perchè è il programmatore a volerlo, 
        // definendo e manipolando gli indici delle ascisse e delle ordinate per modificare la 
        // posizione dello stesso. In seguito poi lo si aggiunge al frame, ovvero alla finestra che 
        // si occuperà di mostrarlo. Essendo che la finestra potrebbe avere degli spazi vuoti, si fa 
        // in modo che gli elementi vadano a riempirli disponendosi nel modo più efficiente possibile.
        
        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.weightx = 1; gbc.weighty = 1; 
        frame.getContentPane().add(infoUpdate, gbc);
        
        // Lo stesso discorso vale anche per gli altri bottoni, infatti si eseguono le stesse 
        // procedure per modificare la posizione del bottone e mostrarlo. 
        
        JButton addProduct = new JButton("Aggiungi Prodotto"); 
        gbc.gridx = 1; gbc.gridy = 0; 
        gbc.weightx = 1; gbc.weighty = 1; 
        frame.getContentPane().add(addProduct, gbc);
        
        JButton deleteProduct = new JButton("Elimina Prodotto"); 
        gbc.gridx = 2; gbc.gridy = 0; 
        gbc.weightx = 1; gbc.weighty = 1; 
        frame.getContentPane().add(deleteProduct, gbc);
    
        JButton executePayment = new JButton("Esegui il Pagamento");
        gbc.gridx = 3; gbc.gridy = 0; 
        gbc.weightx = 1; gbc.weighty = 1; 
        frame.getContentPane().add(executePayment, gbc);
        
        JButton getTopSeller = new JButton("Prodotti più Venduti");
        gbc.gridx = 4; gbc.gridy = 0; 
        gbc.weightx = 1; gbc.weighty = 1; 
        frame.getContentPane().add(getTopSeller, gbc); 
        
        // Alcune funzioni che può svolgere l'amministratore richiedono l'aggiunta di 
        // alcune informazioni quali il nome, la categoria, il prezzo e la quantità 
        // del prodotto che possiamo aggiungere al menu. Infatti le informazioni che 
        // vengono inserite in questi campi di testo vengono gestite dall'ascoltatore 
        // legato al bottone che ha la funzione di aggiungere un nuovo prodotto al 
        // menu.
        // Per motivi grafici dobbiamo poter posizionare tutti gli elementi di cui 
        // abbiamo bisogno nella finestra, e lo facciamo manipolando gli indici
        // x e y, poichè tutto lo spazio mostrato è diviso come se fosse una matrice, 
        // aggiungendo alcuni accorgimenti in modo da sfruttare al megli tutto lo
        // spazio in eccesso disponibile. 
        
        JLabel lbName = new JLabel("Nome Prodotto: "); 
        gbc.gridx = 0; gbc.gridy = 1; 
        gbc.weightx = 1.0; gbc.weighty = 1.0; 
        
        // Un ulteriore accorgimento grafico riguarda lo spazio che vi è tra gli elementi 
        // grafici (ovvero i label e i campi di testo), in particolare lo spazio superiore 
        // ad ogni elemento. 
        
        gbc.insets.top = 10;
        
        // Inoltre evitiamo che ogni elemento possa riempire orizzontamente tutto lo spazio 
        // mostrato, settando il riempimento a NONE. Lo stesso ragionamento lo si fa con 
        // tutti gli altri elementi dello stesso tipo. 
        
        gbc.fill = GridBagConstraints.NONE; 
        frame.getContentPane().add(lbName, gbc); 
        
        JTextField txName = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1; 
        gbc.weightx = 1.0; gbc.weighty = 1.0; 
        gbc.insets.top = 10;
        gbc.fill = GridBagConstraints.NONE; 
        frame.getContentPane().add(txName, gbc);
        
        JLabel lbCategory = new JLabel("Categoria Prodotto: "); 
        gbc.gridx = 0; gbc.gridy = 2; 
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.insets.top = 10; 
        gbc.fill = GridBagConstraints.NONE;  
        frame.getContentPane().add(lbCategory, gbc); 
        
        JTextField txCategory = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 2; 
        gbc.weightx = 1.0; gbc.weighty = 1.0; 
        gbc.insets.top = 10; 
        gbc.fill = GridBagConstraints.NONE; 
        frame.getContentPane().add(txCategory, gbc);
        
        // Gli elementi che seguono sono disposti tutti sul lato destro, così da 
        // rendere l'applicazione il più intuitiva possibile. Per fare questo si 
        // manipolano ancora una volta le ascisse e le ordinate per poterlo
        // posizionare nel punto desiderato, ed in particolare è l'ascissa che 
        // viene incrementata. 
        // Gli elementi che stiamo creando hanno la funzione di permettere 
        // all'Admin di poter inserire un nuovo prodotto, infatti quando le caselle 
        // di testo vengono popolate, una volta premuto sul tasto per l'aggiunta 
        // di un nuovo prodotto, viene generato un evento che è gestito richiamando 
        // il metodo che permette di inserire un nuovo elemento nella tabella dei 
        // prodotti salvati. 
        
        JLabel lbPrice = new JLabel("Prezzo Prodotto: "); 
        gbc.gridx = 2; gbc.gridy = 1; 
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.insets.top = 10;
        gbc.fill = GridBagConstraints.NONE; 
        frame.getContentPane().add(lbPrice, gbc); 
        
        JTextField txPrice = new JTextField(15);
        txPrice.setText("0.0"); 
        
        gbc.gridx = 3; gbc.gridy = 1; 
        gbc.weightx = 1.0; gbc.weighty = 1.0; 
        gbc.insets.top = 10;
        gbc.fill = GridBagConstraints.NONE; 
        frame.getContentPane().add(txPrice, gbc);
        
        JLabel lbQuantity = new JLabel("Quantità Prodotto: "); 
        gbc.gridx = 2; gbc.gridy = 2; 
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.insets.top = 10;
        gbc.fill = GridBagConstraints.NONE;  
        frame.getContentPane().add(lbQuantity, gbc); 
        
        JTextField txQuantity = new JTextField(15);
        txQuantity.setText("0");
        
        gbc.gridx = 3; gbc.gridy = 2; 
        gbc.weightx = 1.0; gbc.weighty = 1.0; 
        gbc.insets.top = 10; 
        gbc.fill = GridBagConstraints.NONE;  
        frame.getContentPane().add(txQuantity, gbc);
        
        // Ogni bottone produce un evento diverso, ed infatti ad ognuno di questi è 
        // legato un ActionListener, un ascoltatore, che si occupa di gestire l'evento 
        // richiamando magare delle funzionalità specifiche per quel bottone. Quando per 
        // esempio si clicca sul bottone per eseguire il pagamento, viene avviata la 
        // procedura di pagamento (in tre modalità diverse); Oppure, quando si clicca sul 
        // bottone per conoscere i prodotti più venduti si viene reindirizzati verso la 
        // finestra che contiene i prodotti più venduti; E così continuando per ogni 
        // bottone..
        
        // Il bottone che si occupa di aggiornare le informazioni relative ad il prodotto che 
        // è stato selezionato con il puntatore del mouse è legato ad un Action Listener che si 
        // occupa di eseguire l'operazione. Il ragionamento che sta alla base dell'operazione è 
        // semplice ed immediato: Un utente può decidere se aggiornare alcune o tutte le 
        // informazioni relative a quel prodotto, e per fare questo si crea una "lista" di 
        // controlli IF, grazie ai quali si capisce se le caselle di testo sono state riempite con
        // qualcosa, che sarà ovviamente ciò che andrà a sostituire l'informazione già contenuta
        // nel database. In seguito poi si richiama il metodo relativo a ciò che si vuole modificare 
        // contenuto nella classe MenuOperationUpdate. 
        
        infoUpdate.addActionListener((ActionEvent e) -> {
            try {
                int selectedRow = tblMenu.getSelectedRow(); 
                
                String pName = txName.getText();
                String pCategory = txCategory.getText(); 
                
                int toUpdateID = (int)tblMenu.getModel().getValueAt(selectedRow, 0);
                MenuOperationUpdate newUpdateOperation = new MenuOperationUpdate(); 
                
                // Se il campo che andiamo a controllare non è vuoto, allora possiamo procedere 
                // con l'aggiornamento di quella relativa informazione. Altrimenti si passa al 
                // secondo controllo IF che riguarda la successiva casella di testo. 
                
                if(!txName.getText().isEmpty()) {
                    newUpdateOperation.modProductName(toUpdateID, pName); 
                    
                    // Attraverso questa istruzione settiamo il valore contenuto nella cella che abbiamo selezionato 
                    // con il nuovo valore che inseriamo nella corrispettiva casella per l'aggiornamento delle 
                    // informazioni relative al prodotto. La colonna da modificare viene aggiunta manualmente 
                    // per ovviare a problemi che possono presentarsi nel momento in cui si vogliono aggiornare più
                    // informazioni dello stesso prodotto. 
                    
                    tblMenu.getModel().setValueAt(pName, selectedRow, 1);
                } if (!txCategory.getText().isEmpty()) {
                    newUpdateOperation.modProductCategory(toUpdateID, pCategory); 
                    tblMenu.getModel().setValueAt(pCategory, selectedRow, 2);
                }
                
                // Essendo che non tutte le colonne della tabella dei prodotti sono di tipo stringa, 
                // prima di poter controllare il contenuto della casella di testo ed eventualmente 
                // aggiornare con tale contenuto le informaioni del prodotto, abbiamo bisogno di 
                // convertire le stringhe in tipi compatibili con quelli definiti dalla tabella dei 
                // prodotti. Così il prezzo diventa un double e la quantità ovviamente è un intero. 
                
                Double pPrice = Double.parseDouble(txPrice.getText());
                int pQuantity = Integer.parseInt(txQuantity.getText());
                
                // Si controlla che il double contenuto nella casella di testo del prodotto sia
                // diverso da 0.0, e se questo è verificato allora si può procedere all'aggiornamento 
                // del prezzo di quel prodotto. Lo stesso vale per la quantità, considerando come 
                // tipo però l'intero in questo caso. 
                
                if (pPrice != 0.0) {
                    newUpdateOperation.modProductPrice(toUpdateID, pPrice);
                    tblMenu.getModel().setValueAt(pPrice, selectedRow, 3);
                } if (pQuantity != 0) {
                    newUpdateOperation.modProductQuantity(toUpdateID, pQuantity); 
                    tblMenu.getModel().setValueAt(pQuantity, selectedRow, 4);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Non è stato selezionato alcun prodotto.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // Quando il bottone per inserire un nuovo prodotto nel menu viene premuto,
        // allora l'Action Listener legato a questo elemento fa si che ciò accada. 
        // Si recuperano in un primo momento i contenuti di ciascuna casella di testo, 
        // convertendo li dove è necessario per permettere la completa compatibilità 
        // dei tipi. In seguito si istanzia la classe MenuOperationAdd il cui interno 
        // è stato definito il metodo che si occupa proprio di inserire un nuovo 
        // prodotto nel menu, e che prende in input le informazioni necessarie per farlo.
        
        addProduct.addActionListener((ActionEvent e) -> {
            String pName = txName.getText(); 
            String pCategory = txCategory.getText(); 
            double pPrice = Double.parseDouble(txPrice.getText());
            int pQuantity = Integer.parseInt(txQuantity.getText());
            
            MenuOperationAdd newAddOperation = new MenuOperationAdd(); 
            newAddOperation.addProduct(pName, pCategory, pPrice, pQuantity);
        });
        
        // Così come il bottone per l'aggiunta o l'aggiornamento del prodotto, anche quello
        // per l'eliminazione si occupa di manipolare i dati contenuti nella tabella che 
        // li contiene. In particolare quando con il cursore si va a selezionare un prodotto
        // e si va succesisvamente a cliccare sul bottone per l'eliminazione di quello stesso,
        // l'Action Listener legato a quell'azione svolge diverse operazioni: 
        // 1. Converte in un intero l'oggetto risultante dalla selezione, in particolare andiamo
        //    a catturare l'ID del prodotto stesso che si trova alla colonna 0, e alla riga 
        //    dipendente dalla selezione dell'utente. 
        // 2. Si dichiara un oggetto di tipo MenuOperationDelete, e lo si usa per richiamare il 
        //    metodo deleteProduct, che prende in input proprio l'ID dello stesso prodotto. 
        
        deleteProduct.addActionListener((ActionEvent e) -> {
            try {
                int toDeleteID = (int)tblMenu.getModel().getValueAt(tblMenu.getSelectedRow(), 0); 
            
                MenuOperationDelete newDeleteOperation = new MenuOperationDelete(); 
                newDeleteOperation.deleteProduct(toDeleteID); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Non è stato selezionato alcun prodotto.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // Quando il bottone creato appositamente per le tre diverse procedure di 
        // pagamento viene premuto, allora viene richiamato un evento attraverso il 
        // quale viene mostrato un PROMPT grazie al quale si può scegliere il numero 
        // del tavolo da servire e il metodo di pagamento da eseguire. 
        // Tutto il sistema di gestione del pagamento viene sviluppato con il Design 
        // Pattern Strategy, grazie al quale vengono definite tre strategie che l'utente, 
        // ovvero l'admin, dovrà scegliere di percorrere. 
        
        executePayment.addActionListener((ActionEvent e) -> {
            ExecutePaymentPrompt payNow = new ExecutePaymentPrompt();
        });
        
        // Compito dell'admin è anche quello di controllare periodicamente i prodotti 
        // più venduti che sono contenuti nel database, ed in particolare in una tabella
        // chiamata "prodotti" in cui vi è una colonna denominata "quantita_v" che 
        // definisce la quantità di prodotto in magazzino, il che vuol dire che più basso 
        // è quel numero, più alto è il tasso di vendita di quel prodotto. 
        // Essendo che non possiamo visualizzare tutti i prodotti, ne visualizziamo soltanto 
        // cinque, ovvero i cinque best seller di tutto il menu. 
        // Quando si clicca sul bottone, quello che viene mostrato è una finestra POP-UP, 
        // contenente la lista dei cinque prodotti più venduti. 
        
        getTopSeller.addActionListener((ActionEvent e) -> {
            TopSellingProduct getProduct = new TopSellingProduct();
            getProduct.getTopSellingProduct();
        });
        
        // Per rendere più completo il centro di controllo dell'amministratore inseriamo anche 
        // il menu impaginato attravero una tabella grazie al quale poi eseguire le operazioni 
        // che l'Admin è predisposto a fare: l'aggiunta o l'eliminazione di un prodotto, o 
        // l'aggiornamento delle informazioni dello stesso. 
        
        getMenuModel = new MenuList(); 
        
        // A differenza di quanto fatto con la lista degli ordini, ad esempio, in questo caso 
        // dobbiamo specificare esplicitamente la tabella che si vuole creare nella classe in 
        // cui vi sono specificati anche gli altri elementi, quali bottoni, che interagiscono 
        // con la stessa. Quindi quando si istanzia la classe attraverso l'oggetto getMenuList, 
        // il metodo getMenuModel() restituisce solamente il Modello della tabella, e non il 
        // risultato completo (modello, tabella e scroll pane).
        
        tblMenu = new JTable(); 
        tblMenu.setModel(getMenuModel.getMenuModel());
        
        // Cosi come si definisce esplicitamente lo stessto Scroll Pane preso in prestito dalle 
        // librerie SWING integrate in Java. Quindi vi si inserisce all'interno la tabella appena
        // creata. 
        
        scroll = new JScrollPane(); 
        scroll = new JScrollPane(tblMenu);
        
        // Vengono definire allora le politiche di visualizzazione e selezione delle barre di 
        // scorrimento orizzontali e verticali, che vengono mostrate solamente quando è 
        // necessario, ovvero quando il numero di elementi supera le dimensioni della stessa 
        // tabella. In seguito quest'ultima la si posiziona manipolando gli indici del Layout 
        // Manager che abbiamo scelto, ovvero il GridBagLayout.  
        
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.getContentPane().add(scroll, gbc);
        
        // La finestra, ed in particolare le dimensioni della finestra, in questo modo 
        // vengono adattate al contenuto della stessa. Più elementi aggiungiamo alla 
        // finestra, più grandi saranno le dimensioni di quest'ultima. Infine la
        // rendiamo visibile con il metodo "setVisible" che prende in input un valore 
        // booleano. 
                
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    private final MenuList getMenuModel; 
    private JTable tblMenu; 
    private JScrollPane scroll; 
}
