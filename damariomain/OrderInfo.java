package damariomain;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * La classe si occupa di recuperare dal database alla base del programma le 
 * informazioni riguardanti un determinato prodotto. Questo permette ad un 
 * cameriere di conoscere i dettagli relativi al prodotto che è stato ordinato 
 * dato che ogni cameriere ha una lista di ordini da gestire. Inserendo l'ID del 
 * prodotto che è seplicitato nella lista, il cameriere può conoscere il nome, la 
 * categoria, il prezzo, e la quantità del prodotto presente in magazzino. 
 * Gli elementi grafici che vengono poi visualizzati attraverso un pannello sono 
 * semplicemente tre: 
 * 1. Un bottone che è legato ad un Action Listener, 
 * 2. Una Text Area nella quale verranno visualizzate le informazioni, 
 * 3. Un Text Field, attraverso il quale l'utente (cameriere) può inserire il 
 *    codice del prodotto che vuole conoscere. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class OrderInfo extends JPanel implements ActionListener {
    /**
     * Il costruttore della classe, oltre a stabilire la connessione con il database, si 
     * occupa anche di disegnare e costruire il pannello grazie al quale un utente, ed in 
     * particolare il cameriere, può recuperare le informazioni di un determinato ordine 
     * inserendo il codice ID del prodotto stesso. 
     */
    
    public OrderInfo () {
        try {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        // Gli elementi grafici mostrati sono semplicemente tre: 
        // 1. Un bottone alla quale è associato un ascoltatore, così che quando viene
        //    premuto, genera un evento (in questo caso l'esecuzione della query di 
        //    esecuzione),
        // 2. Un Text Field che permette all'utente di inseriere un ID (relativo al 
        //    prodotto) in modo che possa conoscerne i dettagli relativi a quest'ultimo, 
        // 3. Una Text Area nella quale mostrare i dati recuperati attraverso la query SQL, 
        //    che ovviamente disabilitiamo alle eventuali modifiche da parte dell'utente. 
        
        getInfo = new JButton(">>");
        textID = new JTextField("ID", 2);
        textInfo = new JTextArea(2, 24);
        textInfo.setEditable(false); 
        
        // Per motivi di natura estetica, si riempie la Text Area con una sorta di 
        // spiegazione della sua presenza in tal contesto. 
        
        textInfo.setText("Recupero delle informazioni \nrelative ad un prodotto..");
        
        // In seguito si aggancia l'ascoltatore al bottone getInfo, ovvero sia il 
        // bottone che una volta premuto genera un evento. Tale evento scatena 
        // l'esecuzione di una query SQL che seleziona dalla tabella dei prodotti 
        // tutte le informazioni relative al prodotto con quell'ID identificativo. 
        
        getInfo.addActionListener(this);
        
        // Ed infine si aggiungono gli elementi, che verranno poi mostrati nel pannello 
        // implementato nella classe Waiter, ovvero la classe del cameriere. 
        
        add(textID); 
        add(textInfo); 
        add(getInfo); 
    }
    
    /**
     * L'evento è gestito in modo corretto dal metodo di seguito implementato, che si 
     * occupa di eseguire la query volta a raccogliere le infomazioni del prodotto e 
     * visualizarle nella Text Area poco prima dichiarata. Ovviamente se per un qualsiasi 
     * motivo l'operazione non dovesse andare a buon fine, allora l'eccezione sarebbe gestita
     * mostrando un messaggio di errore. 
     * 
     * @param e 
     */
    
    @Override
    public void actionPerformed (ActionEvent e) {
        String sqlName = "SELECT nome_p, categoria_p, costo_p, quantita_v FROM prodotti WHERE id_p = " + textID.getText();
        try {
            rs = stmt.executeQuery(sqlName);
            rs.next(); 
            
            // Ovviamente mostriamo le informazioni risultanti dalla query nella Text Area settando il suo contenuto (come 
            // già fatto precedentemente) con le informazioni appena selezionate. Per mostrare al meglio il risultato, ne 
            // formattiamo il contenuto con le ecape sequence, in modo da mostrarle come se fosse una tabella.
            
            textInfo.setText("Nome: \t" + rs.getString(1) + "\nCategoria: \t" + rs.getString(2) + "\nCosto: \t" 
                    + rs.getDouble(3) + " €\nQuantità: \t" + rs.getInt(4)); 
        } catch (SQLException ex) {
            // Se l'operazione non dovesse andare a buon fine, allora l'eccezione che viene generata è 
            // opportunamente gestita attraverso una finestra di dialogo che indica che il fallimento 
            // dell'operazione e mostra alcuni dettagli tecnici sul perchè non è stata esguita l'operazione. 
            
            JOptionPane.showMessageDialog(null, "Errore. Operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private final JTextField textID; 
    private final JTextArea textInfo; 
    private final JButton getInfo;
    
    Connection con = null; 
    Statement stmt = null;
    ResultSet rs = null; 
}
