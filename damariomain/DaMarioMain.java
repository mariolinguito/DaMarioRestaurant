package damariomain;

// Vengono importate le librerie Java che servono al corretto 
// funzionamento del programma. Tra quelle che aggiungiamo troviamo
// la libreria SWING per l'aspetto grafico, quella EVENT per agganciare 
// un evento ad un azione dell'utente, etc..

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Si intende simulare un sistema automatizzato per la gestione di ordini ad un 
 * ristorante. Il ristorante dispone di un numero preciso di camerieri distinti
 * tra loro da un codice identificativo, e di un numero di tavoli preciso, anch'essi
 * distinti tra loro da dei codici univoci.
 * I camerieri hanno il compito di prendere le ordinazioni che una volta inviati 
 * sono gestiti o dal reparto cucina, o dal reparto bar, essendo che i prodotti sono 
 * divisi per categoria. Inoltre il cameriere può annullare la sua ultima ordinazione
 * oppure anche proporre alternative alle scelte dei clienti (a patto che siano della 
 * stessa categoria del prodotto scelto). 
 * L'applicazione prevede anche l'accesso come amministratore, utente cioè che ha
 * mansioni diverse rispetto al cameriere, in quanto ha la possibilità di aggiungere, 
 * eliminare o aggiornare un prodotto nel menu, oppure anche quello di procedere al 
 * pagamento del conto di un tavolo in tre modalità diverse, o anche quello di 
 * visualizzare i prodotti più venduti in quel momento. 
 * Lo sviluppo di un'interfaccia grafica intuitiva, l'utilizzo di Design Pattern volti 
 * alla risoluzione di deteerminati problemi, l'uso efficiente delle eccezioni, e un 
 * database robusto di base faranno il resto. 
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class DaMarioMain {
    /**
     * Il MAIN del programma si occupa, come ovvio che sia, di permettere la prima
     * interazione tra l'applicazione e l'utente, permettendo a quest'ultimo di 
     * scegliere come accedere (se con amministratore o come cameriere) attraverso 
     * una finestra di dialogo in cui vi sono due bottoni, un label ed un immagine. 
     * 
     * @param args
     * @throws IOException 
     */
    
    public static void main(String[] args) throws IOException {
        // Viene creato un Frame, ovvero una finestra principale, che 
        // ha un aspetto grafico, una cornice ridimensionabile e un 
        // titolo, che viene dato in input come Stringa. 
        
        JFrame frame = new JFrame("Login");
        
        // Possiamo aggiungere nuovi componenti, quali dei label, 
        // dei bottoni, delle immagini, dei menu, etc.. 
        // In questo caso aggiungiamo un label, due bottoni e un'immagine.
        // ...............................................................
        // I due bottoni sono legati a un Action Listener, che esegue delle 
        // determinate operazioni quando quello stesso bottone viene premuto.
        
        JLabel ifLabel = new JLabel("Accedi come..");
        JButton btnLoginAdmin = new JButton("Amministratore");
        JButton btnLoginWaiter = new JButton("Cameriere");
        
        
        // In questo modo carichiamo nel programma un'immagine che si 
        // trova nel PATH selezionato. L'immagine la si inserisce in 
        // un label, che la mostra. 
        
        BufferedImage imgLoaded = ImageIO.read(new File("src/img/account-dialog.png"));
        ImageIcon imgLogin = new ImageIcon(imgLoaded);
        JLabel imgLabel = new JLabel(imgLogin); 
        
        // Ad ognuno dei bottoni presenti viene associato un qualche 
        // tipo di azione. Quando si agisce sul componente, ovvero si 
        // preme sul bottone, si ha un evento che è inviato all'ascoltatore. 
        // E' infatti l'ascoltatore che gestisce l'evento ed esegue delle 
        // determinate azioni in base alle informazioni contenute nell'oggetto
        // Event (chi ha creato l'evento, come lo ha creato, etc..). 
        // ....................................................................
        // Quando il bottone di "Amministratore" viene premuto, viene visualizzata
        // un'apposita finestra di Login nella quale bisogna inserire l'ID utente e
        // la PASSWORD associata ad un account che è stato preliminarmente creato in
        // un database relazione gestito dal DBMS MySQL. 
        // Nel caso in cui il bottone "Amministratore" viene premuto, si passa come 
        // parametro una stringa che descrive il tipo di accesso. Lo stesso discorso
        // vale per il bottone "Cameriere".
        
        
        btnLoginAdmin.addActionListener((ActionEvent e) -> {
            LoginWindow newLogin = new LoginWindow("Admin");
            newLogin.setVisible(true);
        });
        
        btnLoginWaiter.addActionListener((ActionEvent e) -> {
            LoginWindow newLogin = new LoginWindow("Waiter");
            newLogin.setVisible(true);
        });
        
        // Possiamo modellare e personalizzare la finestra in qualsiasi modo
        // noi vogliamo. Ed infatti si comunica che quando la finestra viene 
        // chiusa, allora anche tutto il programma deve essere chiuso. 
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Si sceglie un tipo di Layout, in questo caso un FlowLayout, e lo si 
        // pone al centro dello schermo (qualsiasi sia la risoluzione del display). 
        
        frame.setLayout(new FlowLayout());
        frame.setSize(310, 200);
        frame.setLocationRelativeTo(null);
        
        // Il colore dello sfondo lo si setta con il bianco, e si aggiungono gli 
        // elementi che precedentemente sono stati creati sulla finestra. Ed infine
        // si rende il tutto visibile. 
        
        frame.getContentPane().setBackground(Color.white);
        frame.getContentPane().add(imgLabel); 
        frame.getContentPane().add(ifLabel); 
        frame.getContentPane().add(btnLoginAdmin);
        frame.getContentPane().add(btnLoginWaiter);
        frame.setVisible(true);
    } 
}
