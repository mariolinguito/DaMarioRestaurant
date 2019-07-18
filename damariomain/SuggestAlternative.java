package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe si occupa di proporre piatti alterativi data una scelta 
 * iniziale del cliente. Ovviamente il cliente può scegliere di
 * accettare l'alternativa oppure continuare con la sua scelta. Questo 
 * viene fatto mostrando una finestra di dialogo che ha il compito di 
 * chiedere al cliente se vuole accettare la proposta del cameriere, oppure 
 * declinarla, oppure ancora di chiuedere la finestra. Viene in seguito 
 * generato un numero, un intero, che è gestito tramite un blocco IF-ELSE.
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class SuggestAlternative extends JDialog {
    /**
     * Il costruttore si occupa di inizializzare la variabile di istanza con la categoria
     * del prodotto inserita, in modo da consentire al cameriere di proporre pietanze o 
     * bevande alternative appartenenti alla stessa categoria. 
     * 
     * @param ctProduct Il parametro di input è una stringa che corrisponde alla 
     * categoria del prodotto inizialmente scelto. 
     */
    
    public SuggestAlternative (String ctProduct) {
        this.ctP = ctProduct; 
    }
    
    /**
     * Il metodo si occupa di ritornare l'id di un prodotto alternativo 
     * facente parte della stessa categoria del prodotto originariamente 
     * scelto dal cliente. Per far si che ciò accasa si utilizza una query 
     * scritta in SQL che seleziona un prododotto a caso (si utilizza la 
     * funzione RAND() già compresa in SQL), limitando alla scelta di un solo
     * prodotto dalla tabella che contiene questi ultimi. 
     * 
     * @return Il metodo ritorna un intero che corrisponde all'ID del prodotto 
     * alternativamente suggerito. 
     */
    
    public int rtSuggestNow () {
        try {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            
            String sqlSuggesting = "SELECT id_p, nome_p FROM prodotti WHERE categoria_p = '" + ctP + "' ORDER BY RAND() LIMIT 1";             
            rs = stmt.executeQuery(sqlSuggesting);
            rs.next();
            
            // Si recupera l'ID del prodotto, ed il nome del prodotto stesso che è stato scelto
            // in modo casuale attraverso al query. E' necessario questo per poter capire quale 
            // prodotto è stato scelto in alternativa e per mostrare al cliente l'alternativa. 
            
            idP = rs.getInt(1);
            nmP = rs.getString(2);
            
            Object[] options = {"Si, lo gradirei.", "No, la ringrazio."};
            int n = JOptionPane.showOptionDialog(null, "Gentile cliente, \nPotrebbe desiderare in alternativa " 
                    + nmP, "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            // Quando al cliente viene posta la domanda, egli può scegliere se accettare o meno la proposta del cameriere,
            // e addirittura può direttamente chiudere la finestra. In ognuno di questi casi, viene generato un numero 
            // intero che equivale alla scelta fatta.
            // ...........................................................................................................
            // Se infatti la scelta fatta è quella di accettare la proposta del cameriere, allora viene generato un intero
            // pari a 0, ed il cameriere si occupa di cambiare l'ID del prodotto precedente con questo nuovo che è stato
            // scelto. Mentre invece se la scelta del cliente è quella di declinare la proposta del cameriere allora
            // l'intero generato è pari a 1, e quindi si procede normalmente, come se la domanda non fosse stata fatta. 
            // Se il cliente chiude la finestra, evadendo direttamente la domanda, allora anche in questo caso l'evento
            // genera un intero che è diverso sia da 0 che da 1, e produce un messaggio che indica che non si è capito la
            // scelta e che quindi si procederà normalmente.
            
            switch (n) {
                case 0:
                    // Quando l'utente sceglie di accettare la proposta del cameriere, allora viene generata una finestra di dialogo
                    // in cui viene mostrato un messaggio che informa dall'avvenuto cambiamento di ordine. Inoltre poi viene
                    // ritornato l'ID del nuovo prodotto scelto, in modo che possa essere correttamente elaborato.
                    
                    JOptionPane.showMessageDialog(null, "Ottima scelta, le porteremo la nostra alternativa..", null, 
                            JOptionPane.INFORMATION_MESSAGE);
                    return idP;
                case 1:
                    // Nel caso in cui l'utente non voglia cambiare il suo ordine, allora, allo stesso modo di prima, viene mostrata
                    // una finestra di dialogo grazie al quale l'utente viene a sapere che il suo ordine non varierà e sarà eseguito
                    // come previsto. Per fare questo, viene ritornato un valore pari a 0, che indicherà che non è stato scelto di
                    // cambare l'ordine.
                    
                    JOptionPane.showMessageDialog(null, "Le porteremo la sua scelta allora..", null, JOptionPane.INFORMATION_MESSAGE);
                    return 0;
                default:
                    // Nel caso in cui l'utente-cliente chiude la finestra di dialogo senza aver fatto una scelta
                    // l'ordine viene processato come se l'utente avesse scelto di declinare la proposta del
                    // cameriere. Allo stesso modo di prima, quindi, viene ritornato un valore pari a 0.
                    
                    JOptionPane.showMessageDialog(null, "Non riesco a capire la scelta\nle porteremo il suo ordine..", null, 
                            JOptionPane.INFORMATION_MESSAGE);
                    return 0; 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return 0; 
    }
    
    private Connection con = null; 
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private int idP;
    private final String ctP;
    private String nmP;
}
