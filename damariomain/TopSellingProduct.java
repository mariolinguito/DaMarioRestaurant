package damariomain;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * La classe ha il semplice compito di mostrare, quando è istanziata, i prodotti 
 * più ventuti sfruttando una query che seleziona cinque prodotti ordinandoli in 
 * base al campo "quantita_v" in senso decrescente, in modo che il prodotto che 
 * ha in quel campo il numero più piccolo è quello più venduto. In poche parole, 
 * tanto più il numero di quello specifico campo è piccolo, tanto più il prodotto 
 * ha successo nelle vendite. Ovviamente la query SQL va eseguita, e vanno raccolte 
 * le informazioni contenute nella tabella dei prodotti a proposito di quel prodotto. 
 * Nel caso in cui la query non viene eseguita, allora l'eccezione viene catturata 
 * e gestita in modo corretto mostrando un messaggio con tutti i dettagli dell'errore.
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
 */

public class TopSellingProduct extends JDialog {
    public TopSellingProduct () {
        try {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Errore nella connessione al database.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Dal punto di vista tecnico il metodo si occupa di definire, costruire e gestire la tabella 
     * che sarà popolata dagli elementi raccolti attraverso una query scritta in SQL che seleziona 
     * le informazioni dei prodotti ordinati in senso crescente considerando la quantità disponibile
     * del prodotto stesso. I dati raccolti dalla query sono poi inseriti in una tabella costruita con 
     * la libreria Swing di Java, e poi mostrati in un JScrollPane, preso in prestito dalla medesima 
     * libreria, inserito in una finestra le cui dimensioni sono state definite a priori.    
     */
    
    public void getTopSellingProduct () {
        try {
            // In questo modo definiamo un modello di tabella con delle colonne che 
            // sono definite nell'array di String che settiamo come identificatore. 
            
            DefaultTableModel modelTable = new DefaultTableModel(); 
            modelTable.setColumnIdentifiers(columnNames);
            
            // L'elemento SWING che definisce la tabella è settato in modo che abbia come 
            // modello quello appena sopra definito, e che quindi abbia come colonne quelle
            // date al modello stesso. 
            
            tblMenu = new JTable(); 
            tblMenu.setModel(modelTable);
            
            // Per poter visualizzare graficamente e al meglio la tabella che verrà popolata 
            // con gli elementi e le informazioni recuperate dalla tabella dei prodotti. Si 
            // adattano le dimensioni al numero di elementi selezionati dalla query SQL, ovvero
            // i cinque prodotti più venduti. 
            
            tblMenu.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tblMenu.setFillsViewportHeight(true); 
            
            scroll = new JScrollPane(tblMenu);
            
            // Semmai gli elementi fossero più di cinque, allora si stabilisce la politica di 
            // adattamento della tabella con gli elementi in esso contenuti. Si inserisce una 
            // scroll bar sia orizzontale che verticale quindi. 
            
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            
            // La query in SQL non fa altro che selezionare le informazioni relative ai prodotti
            // più venduti. Infatti si selezionano i primi cinque prodotti che hanno a quantità
            // più bassa, e che quindi sono i prodotti che sono stati più venduti. 
            
            String sqlTopSeller = "SELECT id_p, nome_p, categoria_p FROM prodotti ORDER BY quantita_v ASC LIMIT 5"; 
            rs = stmt.executeQuery(sqlTopSeller); 
            
            // Per poter selezionare tutti i prodotti in modo continuo allora sfruttiamo un ciclo 
            // while dove al suo interno recuepriamo le informazioni che ci servono e le salviamo 
            // in delle variabili dichiarate appositamente. Si seleziona infatti l'ID, il nome e la 
            // categoria del prodotto. 
            
            while (rs.next()) {
                id_p = rs.getInt(1); 
                name_p = rs.getString(2); 
                category_p = rs.getString(3); 
                
                modelTable.addRow(new Object[]{id_p, name_p, category_p});
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        // Per motivi estetici includiamo un label che indica ciò che contiene la finestra, e
        // lo posizioniam al centro, settandogli il font in Arial, con uno stile BOLD ed una
        // grandezza di 16 px. Il colore poi viene cambiato in rosso. 
        
        topSellingLabel = new JLabel("Prodotti più venduti attualmente:", JLabel.CENTER); 
        topSellingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topSellingLabel.setForeground(Color.DARK_GRAY);        
        
        // Per poter visualizzare il tutto correttamente in una finestra, si crea un 
        // frame, il cui titolo è quello dato in input come stringa. Applichiamo a 
        // questo frame il colore bianco di sfondo (sempre per motivazioni esclusivamente 
        // di design) e settiamo il Layout Manager con un FlowLayout impostando poi 
        // le dimensioni della finestra stessa. 
        
        JFrame topSellingWindow = new JFrame("Top Selling Product");
        topSellingWindow.getContentPane().setBackground(Color.WHITE);
        
        // Facciamo in modo poi che quando la suddetta finestra venga chiusa, la 
        // finestra di Admin principale non viene in alcun modo modificata. Infatti 
        // se così non fosse, ogni volta che l'amministratore volesse consultare i 
        // prodotti più venduti dovrebbe accedere altrettante volte con le sue
        // credenziali. 
        
        topSellingWindow.setLayout(new FlowLayout());
        topSellingWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        // Infine dobbiamo aggiungere gli elementi che sono stati appena creati 
        // nella finestra principale che si occuperà d mostrarli seguendo le 
        // direttive del Layout Manager scelto: il FlowLayout. 
        
        topSellingWindow.getContentPane().add(topSellingLabel); 
        topSellingWindow.getContentPane().add(scroll); 
        
        // Per evitare che l'utente possa modificare le dimensioni della pagina 
        // settiamo il metodo "setResizable" a false, ed inoltre rendiamo il tutto 
        // visibile settando invece il metodo "setVisible" a true. 
        // Sempre per motivi di carattere estetico adattiamo le dimensioni della 
        // finestra al suo contenuto, per meglio poter vedere gli elementi.
        
        topSellingWindow.setSize(455, 170);
        topSellingWindow.setResizable(false); 
        topSellingWindow.setVisible(true);
    }
    
    private int id_p; 
    private String name_p, category_p;
    private JLabel topSellingLabel;
    
    // La tabella ha delle colonne che ovviamente devono avere un nome, ed è per 
    // questo che definiamo un array di String che li contiene e che sarà poi 
    // utilizzato per poter dare un identificativo alle colonne che conterranno 
    // gli elementi.
    
    private final static String [] columnNames = {"ID", "Nome Prodotto", "Categoria Prodotto"};
    private JTable tblMenu;
    private JScrollPane scroll; 
    
    private Connection con = null; 
    private Statement stmt = null;
    private ResultSet rs = null;

}
