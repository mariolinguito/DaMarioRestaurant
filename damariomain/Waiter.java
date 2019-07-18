package damariomain;

// Iportiamo quelle librerie che sono funzionali al corretto funzionamento della 
// classe, e in generale del programma.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * La classe costruisce quello che è il pannello di controllo di ogni cameriere che 
 * ha accesso all'applicazione. In particolare, viene mostrata una finestra nella 
 * quale vi sono gli elementi fondamentali predisposti alle mansioni del cameriere, 
 * ovvero il bottone per permettere a quest'ultimo di prendere delle ordinazioni, 
 * quello per cancellare l'ultimo ordine effettuato, una lista degli ordni effettuati
 * considerando il numero del tavolo che ha effettuato l'ordine e il prodotto ordinato, 
 * ed un particolare pannello grazie al quale si possono conoscere le informazini 
 * relative ad un prodotto, dato il suo ID.
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class Waiter {
    /**
     * Il costruttore della classe prende in input il codice del cameriere che sta 
     * eseguendo le operazione del momento in cui avviene il Login corretto. In 
     * seguito, attraverso l'utilizzo delle librerie Swing di Java, si disegna e 
     * costruisce l'interfaccia grafica del pannello di controllo, con i relativi 
     * label, bottoni, menu, etc..
     * 
     * @param codWaiterIns Il parametro di input del metodo è un intero che rappresenta 
     * il codice del cameriere che ha avuto accesso. 
     */

    public Waiter (int codWaiterIns) {
        this.codWaiter = codWaiterIns;
        
        // In questo modo vengono recuperate le informazioni relative al cameriere 
        // che sta eseguendo le operazioni, come il nome e il cognome in base al 
        // suo codice. 
        
        WaiterInformation getInformation = new WaiterInformation(codWaiter); 
        String wName = getInformation.getName(); 
        String wSurname = getInformation.getSurname();
        
        // Allo stesso modo vogliamo visualizzare l'elenco di tutti gli ordini che 
        // sono stati effettuati da quel cameriere (infatti si passa come parametro 
        // di input il codice identificativo del cameriere stesso). Lo si fa per 
        // tenere traccia di tutti gli ordini che sono stati fatti dal cameriere 
        // così come sarebbe nella vita reale. 
        
        OrderList getList = new OrderList(codWaiter); 
        
        // Vogliamo mostrare queste informazioni sulla finestra, ovvero sul centro 
        // di comando del cameriere, dalla quale può effettuare tutte le operazioni 
        // per il quale è stato predisposto. 
        
        JLabel wNameLabel = new JLabel("Benvenuto/a " + wName + " " + wSurname, JLabel.CENTER); 
        wNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        wNameLabel.setForeground(Color.RED);
        
        // Sull'interfaccia utente vengono aggiunti nuovi componenti, come label, 
        // e bottoni, che permettono al cameriere di poter eseguire le operazioni 
        // per il quale è stato predisposto, ovvero: 
        // 1. Prendere le ordinazioni dei clienti (in modo casuale), 
        // 2. Annullare l'ultima ordinazione effettuata, 
        // Un'altra operazione che può fare il cameriere, ma che rientra nella fase
        // di presa di un ordine, è quella di poter consigliare al cliente un 
        // prodotto alternativo, purchè faccia parte della stessa categoria. 
        
        JButton takeOrder = new JButton("Prendi un ordine"); 
        JButton cancelOrder = new JButton("Cancella ordine");
        
        JLabel wDescriptionLabel = new JLabel("Gli ultimi ordini effettuati: ", JLabel.CENTER);
        wDescriptionLabel.setForeground(Color.DARK_GRAY);
        
        // Alcuni degli elementi costruiti precedentemente sono anche interattivi, 
        // e quindi interagiscono con l'utente. Come ad esempio i bottoni, che una 
        // volta premuti, svolgono un qualche tipo di attività. 
        // ........................................................................
        // Quando il bottone di "Prendi un ordine" viene premuto, la pressione del 
        // tasto viene registrata dall'ascoltatore che ne associa un determinato 
        // evento, che in questo caso è proprio quello di prendere un ordine.
        
        takeOrder.addActionListener((ActionEvent e) -> {
            // L'evento associato al bottone è quello di creare un nuovo ordine 
            // dichiarando un oggetto di tipo TakeOrder, che si occuperà appunto 
            // di far avere un nuovo ordine al cameriere. Tale ordine è di tipo 
            // casuale, ed infatti sia l'ID del prodotto che la sua categoria 
            // vengono generati in modo random dalla classe "CasualOrder". 
            // Nella tabella che tiene traccia delle ordinazioni effettuate, però, 
            // è presente anche una colonna che indica quale cameriere ha preso tale 
            // ordinaione, e quindi, per poter popolare questo campo abbiamo bisogno 
            // del codice del cameriere che ha avuto accesso e che sta eseguendo 
            // le operazioni. Ovviamente, questo codice viene passato come parametro 
            // al costruttore della classe "TakeOrder", che, come ci suggerisce il 
            // nome, permette di generare un nuovo ordine sfruttado il Design Pattern 
            // Chain Of Responsability. 
            
            TakeOrder newOrder = new TakeOrder(codWaiter); 
        });
        
        cancelOrder.addActionListener((ActionEvent e) -> {
            // Quando invece viene premuto sul bottone per la cancellazione dell'ultimo 
            // ordine effettuato dal cameriere corrente, allora viene istanziata la 
            // classe che si occupa di eliminare l'ultimo ordine in base all'ID
            // dell'ordinazione presente nella tabella delle "ordinazioni" presente nel 
            // database. 
            
            new DeleteLastOrder().executeDeleteLastOrder(codWaiter);
        });
        
        // Viene in questo modo creato un frame, ovvero una finestra principale che può essere mdoficata a seconda
        // delle proprie esigenze. Il titolo di questa finestra è dato come input e ci fornisce anche l'ID del 
        // cameriere che ha avuto accesso e che sta eseguendo le operazioni.
        
        JFrame frame = new JFrame("Waiter Control Panel - Cod." + codWaiter);
        
        // La finestra può essere modificata seguendo le proprie esigenze, ed infatti 
        // facciamo in modo che quando viene chiusa la finestra viene terminato anche 
        // il programma; Oppure facciamo in modo che le dimensioni della finestra siano
        // quelle specificate in input. 
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(362, 730);
        frame.setUndecorated(false); 
        
        // Esistono numerosi tipologie di Layout Manager, ed in questo caso (come 
        // in quasi tutti i casi che si saono presentati durante lo sviluppo 
        // dell'applicazione) scegliamo come Layout il FlowLayout, alla quale 
        // settiamo il bianco come colore di sfondo.
        
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.WHITE);
        
        // Infine si aggiungono gli elementi precedentemente creati sulla finestra, in 
        // modo che l'utente possa interagire con essi.
        
        frame.getContentPane().add(wNameLabel);
        frame.getContentPane().add(takeOrder);
        frame.getContentPane().add(cancelOrder);
        frame.getContentPane().add(wDescriptionLabel);
        frame.getContentPane().add(getList.getOrder());
        
        // Il cameriere non conosce però quale sia il prodotto corrispondente a quell'ID, 
        // quindi vi è bisogno di un pannello grazie al quale, inserendo l'ID del 
        // prodotto ordinato (o di qualsiasi altro prodotto), mostra le informazioni 
        // relative a quel prodotto, dal nome fino alla quantità in deposito. 
        // Si crea un nuovo pannello per rendere indipendente il contenuto della finestra 
        // che riguarda i label, i bottoni per gli ordini, e la lista degli ordini dal 
        // form che permette di recuperare le informazioni di quel prodotto stesso. 
        
        JPanel infoPanel = new JPanel(new FlowLayout());
        
        // Viene dichiarato un oggetto di tipo OrderInfo che permette di recuperare le
        // informazioni riguardante il prodotto che si vuole conoscere. Successivamente
        // lo si aggiunge come elemento grafico alla finestra del centro di comando del 
        // cameriere. 
        
        OrderInfo getOrderInfo = new OrderInfo(); 
       
        infoPanel.add(getOrderInfo); 
        Container cnt = frame.getContentPane();
        cnt.add(getOrderInfo);
        
        // Ovviamente dobbiamo poi mostrare il tutto in una finestra e renderla visibile, e 
        // questo lo si fa facilmente settando la visibilità a TRUE con il metodo setVisible. 
        // Essendo poi che non vogliamo che l'utente che utilizza l'applicazione sia in grado 
        // di modificare le dimensioni della finestra, settiamo a FALSE la possibilità di 
        // manipolazione della finestra con il metodo setResizable. Ciò che ne risulta è la 
        // finestra con tutti i relativi elementi e pannelli. 
        
        frame.setResizable(false); 
        frame.setVisible(true);
    }
    
    private int codWaiter; 
}