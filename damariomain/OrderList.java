package damariomain;

// Le librerie importate sono necessarie sia alla connessione al database (SQL), sia 
// alla dichiarazione di quegli elementi che compongono l'interfaccia grafica (SWING) 
// del programma in generale. 

import java.awt.*;
import java.sql.*;
import javax.swing.*; 
import javax.swing.table.*;

/**
 * La classe si occupa semplicemente di mostrare una lista degli ordini che 
 * sono stati effettuati dal cameriere che ha avuto accesso in quel preciso
 * momento. Questo per rendere più completo il programma e per avere maggior
 * controllo su di esso. 
 * La lista è composta da 45 elementi, ovvero dai 45 ultimi ordini effettuati
 * per cui la capacità della DefaultListModel è di quarantacinque elementi, 
 * così che tale lista venga popolata dagli elementi recuperati dalla tabella 
 * delle ordinazioni attraverso una query SQL, che si occupa anche di ordinarli 
 * in senso decrescente. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class OrderList extends JDialog {
    /**
     * Per poter eseguire la query SQL, ed in generale per poter recuperare e 
     * manipolare le informazioni relative agli ordini, bisogna innanzitutto 
     * stabilire una connessione al database che è stato predisposto all'im-
     * magazinamento di tutti i dati del programma. 
     * 
     * @param idW Il parametro di input riguarda il codice del cameriere che ha 
     * avuto accesso, in modo da recuperare tutti i sui ordini. 
     */
    
    public OrderList (int idW) {
        this.idWaiter = idW;
        
        try {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Il metodo si occupa di ritornare un oggetto di tipo JList, in modo che quest'ultimo 
     * possa essere opportunamente gestito ed insertio in una finestra, essendo un 
     * componente grafico che deve necessariamente essere mostrato. L'effetto che si ottiene 
     * è quello di una lista degli ultimi quarantacinque ordini effettuati dal cameriere, per 
     * rendere il programma più completo e per mostrare al cameriere gli ordini da effettuare. 
     * 
     * @return Il metodo ritorna un componente grafico di tipo JScrollPane che sarà mosrato 
     * in una finestra (o frame). 
     */
   
    public JScrollPane getOrder () {
        try {   
            // Si dichiara un oggetto di tipo DefaultTableModel che avrà il compito di 
            // gestire gli ordini recuperati dalla tabella delle ordinazioni, e mostrarli
            // sottoforma di tabella vera e propria che ha per colonne quelle definite dal 
            // vettore di String dichiarato tra le variabili.
            
            DefaultTableModel orderTable = new DefaultTableModel(); 
            orderTable.setColumnIdentifiers(columnNames); 
            
            // Dichiariamo quindi una nuova JTable dandogli come modello di base quello
            // appena sopra definito. Per motivi pratici, ancora, si allinea al centro 
            // (orizzontalmente) il contenuto di ognuna delle singole celle. 
            
            tblOrder = new JTable(); 
            tblOrder.setModel(orderTable);
            
            // Questo lo si fa dichiarando un nuovo oggetto di tipo DefaultTableCellRenderer, 
            // che si occuperà di gestire il contenuto delle celle e la parte relativa a 
            // come deve essere mostrato nella stessa. 
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER); 
            
            // Selezioniamo quindi le due colonne che ci interessa modificare, e settiamo il
            // loro allineamento al centro. Ripetiamo, questo non è altro che un accorgimento
            // grafico per poter pormettere al cameriere di poter gestire al meglio gli 
            // ordini.
            
            tblOrder.getColumn("ID Prodotto").setCellRenderer(centerRenderer);
            tblOrder.getColumn("ID Tavolo").setCellRenderer(centerRenderer); 
            
            // In questo modo adattiamo le dimensioni delle colonne alla finestra nella quale è
            // contenuta la tabella e settiamo la Viewport. 
            
            tblOrder.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tblOrder.setFillsViewportHeight(true);
            
            // Definiamo uno JScrollPane, ovvero un contenitore che gestisce un'area che può essere 
            // virtualmente estesa usando i meccanismi di scorrimento verticale e orizzontale. Questo
            // perchè si vuole gestire un qualcosa che magari può essere più grande di dimensione
            // rispetto alla finestra che lo contiene.
            // Lo JScrollPane riceve come input la componente di grandi dimensione che si vuole gestire 
            // con le barre di scorrimento, ed infatti definiamo anche la politica di tali barre 
            // permettendo a queste ultime di essere visibili solamente nel caso fossero davvero 
            // necessarie. Stiamo cioè definendo un nuovo comportamento per la visualizzazione o meno
            // delle barre di scorrimento. 
            
            scroll = new JScrollPane(tblOrder); 
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            
            // Per meglio adattare la tabella alla finestra impostiamo le dimensioni della stessa
            // manualmente, in modo che possa essere anche visualizzata la barre di scorrimento a
            // destra (quella verticale, che compare quando serve).
            
            scroll.setPreferredSize(new Dimension(355, 525));
            
            // La query si occupa di selezionare il codice identificativo del tavolo che 
            // riceve l'ordinazione e del prodotto che viene ordinato, ma solamente li 
            // dove vi è corrispondenza tra il codice identifiativo del cameriere e quello 
            // inserito (ovvero quello che ha avuto accesso), ordinando le informazioni in 
            // senso decrescente in base all'ID dell'ordine. 
            
            String sqlOrder = "SELECT id_t, id_p FROM ordinazioni WHERE id_c = " + idWaiter + " ORDER BY id_o DESC";
            rs = stmt.executeQuery(sqlOrder);
            int index = 0; 
            
            // Il ciclo while ha il compito di selezionare tutti gli ultimi trentatre 
            // ordini presenti nella tabella delle ordinazioni. Per farlo, si specifica
            // nell'intestazione del ciclo while che l'index (indice dichiarato come 
            // intero) deve sempre essere minore di trentatre.. Quindi se così non fosse
            // si esce dal ciclo while. 
            
            while (rs.next() && index < 45) {
                idTable = rs.getInt(1);
                idProduct = rs.getInt(2);
                                
                // In seguito non facciamo altro che inserire queste informazioni recuperate, in 
                // particolare il codice del tavolo e quello del prodotto, nella tabella che è stata 
                // precedentemente dichiarata.
                
                orderTable.addRow(new Object[]{idTable, idProduct});
                index++; 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore. Operazione non eseguita.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } 
        
        // Ciò che viene ritornato è ovviamente un elemento di tipo JScrollPane, che sarà 
        // opportunamente gestito dalla classe Waiter, mostrandone il risultato in 
        // un frame (in una finestra). 
        
        return scroll; 
    }
    
    private int idWaiter, idTable, idProduct;
    private JTable tblOrder; 
    private JScrollPane scroll; 
    private final static String [] columnNames = {"ID Tavolo", "ID Prodotto"}; 
    
    Connection con = null; 
    Statement stmt = null;
    ResultSet rs = null;
}
