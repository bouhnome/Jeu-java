
package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class FenetrePrincipale extends JFrame {
   
    private JButton jouer;
    private JTextArea consigne;
    private Object[][] matrice;
    private JPanel conteneurPrincipal;
    
    public FenetrePrincipale() {
        super("BABALLE");
               
        jouer  = new JButton   ("<html> <font size = 9 color = blue> Jouer ! </font> </html>");
       		
        conteneurPrincipal = new JPanel(new BorderLayout());
        JPanel monPanelCommande = new JPanel(new FlowLayout());
        conteneurPrincipal.add(monPanelCommande, BorderLayout.NORTH);
       
        monPanelCommande.add(jouer, BorderLayout.CENTER);
        jouer.addActionListener( new EcouteurStart(this));
        
        consigne = new JTextArea(10,20);
        consigne.setText("Baballe");
        conteneurPrincipal.add(consigne,BorderLayout.SOUTH);
        
        this.setContentPane(conteneurPrincipal);
        this.setSize(800,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        afficherPanneauLogo();
    }

    public void afficherPanneauLogo() {
		PanneauLogo logo = new PanneauLogo();
		conteneurPrincipal.add(logo);	
	}
	
	
	
    public static void main(String[] a) {
        FenetrePrincipale f =new FenetrePrincipale();
    }
}
