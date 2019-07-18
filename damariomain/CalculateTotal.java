package damariomain;

import java.sql.*;
import javax.swing.*;

/**
 * La classe ha il chiaro compito di calcolare il totale di una serie di ordini 
 * fatti da uno specifico tavolo il cui numero identificativo unico è dato in
 * input al metodo che si occupa di restiuire il totale come double. Quest'ultimo 
 * viene calcolato con una query innestata scritta in SQL che si occupa prima di 
 * sommare tutti i valori della colonna costo_p dei prodotti li dove l'id dello 
 * stesso prodotto si trova in modo distinto nella tabella delle ordinazioni. 
 * Detto in parole povere, si sommano i prezzi di tutti quei prodotti presenti 
 * nella tabella delle ordinazioni che sono assegnati all'ID del tavolo che si sta 
 * gestendo. Si recupera poi il valore risultante e lo si salva in una variabile 
 * in modo che possa essere restituito successivamente.
 * 
 * @author Mario Linguito - 0124001063.
 * @version 1.0.0
 */

public class CalculateTotal extends JDialog {
    /**
     * Il metodo non fa altro che calcolare, attraverso una query scritta in SQL abbastanza complessa, il
     * totale dei costi di ogni prodotto che è stato acquistato dal tavolo il cui numero identificativo 
     * univoco è stato dato in input allo stesso metodo. Quest'ultimo restiuisce un valore Double che sarà 
     * il ristultato stesso della query. 
     * 
     * @param tNumberIns Come parametro di input vi è il numero identificativo del tavolo della quale 
     * si vuole conoscere il conto totale. 
     * @return Il metodo ritorna un Double che sarà il totale del costo di tutti i prodotti ordinati 
     * da quel tavolo specificato in input. 
     */
    
    public double getCalculatedTotal (int tNumberIns) {
        this.tableNum = tNumberIns; 
        
        try {
            // Cosi come in tutti gli altri metodi che si occupano di eseguire query e manipolare 
            // le informazioni contenute in delle tabelle, ci si deve innanzitutto connettere al 
            // database per poi definire la query SQL che si vuole eseguire. 
            
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            
            String sqlTotal = "SELECT SUM(costo_p) AS TOTAL FROM prodotti WHERE id_p IN (SELECT DISTINCT id_p FROM ordinazioni WHERE id_t = " + tableNum + ")"; 
            rs = stmt.executeQuery(sqlTotal); 
            rs.next(); 
            
            // Viene recuperato il risultato della query e viene salvato nella variabile, che 
            // verrà restituito come risultato del metodo stesso. 
            
            tTotal = rs.getDouble(1); 
        } catch (Exception e) {
            // Ovviamente quando si incorre in un problema l'eccezione che ne deriva viene 
            // gestita da questo blocco che mostra una finestra di dialogo in cui vi sono 
            // le informazioni dell'errore stesso che si è incontrato. 
            
            JOptionPane.showMessageDialog(null, "Errore, operazione non eseguita.\nInfo: " 
                    + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        return tTotal; 
    }
    
    private Connection con = null; 
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private int tableNum;
    private double tTotal; 
}
