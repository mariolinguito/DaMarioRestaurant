package damariomain;

// Si importano le librerie necessarie per la costruzione della finestra di Login, 
// e per eseguire le altre operazioni necessarie al funzionamento del programma. 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * La classe ha il compito di mostrare una finestra di Login, nel quale 
 * sono definiti elementi quali dei campi di inserimento per il testo, e dei 
 * bottoni per permettere l'accesso o la chiusura della stessa pagina aperta. 
 * La finestra si divide in due parti: 
 * La prima parte (quella superiore) infatti riguarda i campi di inserimento del 
 * testo, quali quelli per inserire il codice e la password per l'dentificazione. 
 * La seconda parte (quella inferiore) invece contiene i bottoni per il Login e 
 * la concellazione dell'operazione. 
 * Sono quindi due pannelli indipendenti. 
 * 
 * @author Mario Linguito - 0124001063. 
 * @version 1.0.0
*/

public class LoginWindow extends JDialog {
    /**
     * Il metodo (come anche si evice dal nome) svolge la funzione di creazione e visualizzazone
     * della finestra di dialogo attraverso il quale l'utente può inserire le informazioni, ovvero
     * le credenziali, per poter eseguire l'accesso. Attraverso un'accurata manipolazione dei 
     * constraints per il posizionamento degli elementi, vengono mostrate componenti quali label e
     * Text Field, tra cui una particolare per l'inserimeto della password (coperta dai pallini neri). 
     * Infine vi sono i bottoni, quello per l'accesso legato ad un Action Listener che permette 
     * appunto l'accesso trasmettendo i dati alle classi che si occupano di questo, e quello per la 
     * cancellazione, ovvero l'annullamento dell'operazione. 
     * 
     * @param userIns Il paramentro di input è una stringa che indica quale tipo di utente si è scelto di far accedere. 
     */
    
    public LoginWindow (String userIns) {
        // In base alla scelta di accesso, siamo in grado di definire il contesto 
        // per poter poi eseguire l'accesso con il tipo di utente corretto. 
        
        this.userChose = userIns; 
        
        // Da qui in poi viene costruita la finestra di Login, creando un nuovo 
        // pannello scegliendo come Layout Manager il GridBagLayout. 
        
        JPanel panel = new JPanel (new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        // Per poter visualizzare gli elementi necessari all'accesso dell'utente che 
        // utilizza il programma, si crea un label e un text field per l'inserimento 
        // dell'ID numerico. Il posizionamento di entrambi gli elementi lo si stabilisce
        // grazie al GridBagConstraints, che posiziona gli elementi sulla griglia. 
        
        lbUsername = new JLabel("ID USER: "); 
        cs.gridx = 0; cs.gridy = 0; cs.gridwidth = 1; 
        panel.add(lbUsername, cs); 
        
        tfUsername = new JTextField(20); 
        cs.gridx = 1; cs.gridy = 0; cs.gridwidth = 2; 
        panel.add(tfUsername, cs); 
        
        // Così come il label e il text field dello username, anche per gli elementi che 
        // sono necessari all'inserimento della password vengono posizionati sulla griglia 
        // appena sotto quelli dello user id. Quello che cambia ovviamente è che per la 
        // password definiamo un tipo leggermente diverso di Text Field, in quanto una password
        // in quanto tale, per definizione deve essere nascosta dai classici pallini neri. 
        
        lbPassword = new JLabel("PASSWORD: "); 
        cs.gridx = 0; cs.gridy = 1; cs.gridwidth = 1; 
        panel.add(lbPassword, cs); 
        
        pfPassword = new JPasswordField(20); 
        cs.gridx = 1; cs.gridy = 1; cs.gridwidth = 2; 
        panel.add(pfPassword, cs); 
        
        // Settiamo in questo modo lo sfondo del pannello superiore di colore bianco, 
        // per semplice chiarezza grafica. Ed infine aggiungiamo il bottone di Login, 
        // collegato ad un determinato evento, ovvero quello di eseguire l'accesso grazie
        // alla classe di "Login". 
        
        panel.setBackground(Color.white);
        
        btnLogin = new JButton("Login"); 
        
        // Al bottone di Login è collegato un evento che istanzia la classe "Login" e grazie al 
        // metodo "startLogin" viene restituito un valore true se il login è andato a buon fine, 
        // altrimenti viene mostrato un messaggio di errore che azzerra l'inserimento fatto 
        // precedentemente dall'utente. 
        
        btnLogin.addActionListener((ActionEvent e) -> {
            Login newAccess = new Login(getUsername(), getPassword(), userChose);
            boolean AccessEx = newAccess.startLogin(); 
            if (AccessEx) {
                dispose(); 
            } else {
                // Il messaggio di errore viene mostrato attraverso una semplice finestra di dialogo.
                // Quindi poi le due Text Field per lo username e la password vengono azzerate settandone 
                // il contenuto nullo.
                
                JOptionPane.showMessageDialog(LoginWindow.this, "ID USER o PASSWORD non valido.", "Login", JOptionPane.ERROR_MESSAGE);
                tfUsername.setText("");
                pfPassword.setText(""); 
            }
        });
        
        // Nella finestra è anche presente il bottone per cancellare l'operazione, e quindi 
        // chiudere definitivamente la finestra di Login. Infatti a tale bottone è legato ad un 
        // evento che ha il compito di chiudere la finestra di dialogo, attraverso il richiamo di 
        // dispose(), che ha appunto questo compito. 
        
        btnCancel = new JButton("Cancella"); 
        btnCancel.addActionListener((ActionEvent e) -> {
            dispose();
        }); 
        
        JPanel bp = new JPanel(); 
        bp.add(btnLogin); 
        bp.add(btnCancel); 
        
        bp.setBackground(Color.white);
        
        getContentPane().add(panel, BorderLayout.CENTER); 
        getContentPane().add(bp, BorderLayout.PAGE_END); 
        
        // Anche in questo caso possiamo modificare e persnalizzare la finestra. Infatti facciamo
        // in modo (con pack()) di adattare le dimensioni della finestra agli elementi in essa contenuti, 
        // quali i label, Text Field, e bottoni. Centriamo poi questa stessa finestra ed impediamo 
        // (attraverso seResizable(false)) che questa stessa finestra possa subire modifiche alle dimensioni.
        
        pack(); 
        setLocationRelativeTo(null);
        setResizable(false); 
    }

    /**
     * Il metodo è funzionale alla classe Login, nella quale abbiamo bisogno di conoscere
     * l'ID di colui che ha effettuato l'accesso. Ritorna quindi ciò che è stato inserito 
     * nella barra di testo convertendolo in un intero. 
     * 
     * @return Ciò che viene restituito è un intero che rappresenta lo username dell'utente che sta accedendo all'applicazione. 
     */
    
    public int getUsername() {
        return Integer.parseInt(tfUsername.getText().trim()); 
    } 
    
    /**
     * Lo stesso ragionamento vale per il metodo che restituisce la password dell'utente che 
     * sta cercando di accedere al sistema. Tali informazioni saranno utilizzate per verificare 
     * che effettivamente i dati inseriti corrispondano a quelli inseriti dall'utente. 
     * 
     * @return Il metodo ritorna una stringa che rappresenta la password dell'utente ha inserito nella barra di testo. 
     */
    
    public String getPassword() {
        return new String (pfPassword.getPassword()); 
    }
    
    private String userChose; 
    private JTextField tfUsername; 
    private JPasswordField pfPassword; 
    private final JLabel lbUsername, lbPassword; 
    private final JButton btnLogin, btnCancel; 
}
