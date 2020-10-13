package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class GameOver extends JFrame {

    private JButton rejouer;
    public GameOver() {
        super("Game Over !");
        
        rejouer=new JButton("<html> <font size = 12 color = blue> Rejouer ! </font> </html>");
        rejouer.addActionListener(new EcouteurRejouer(this));
        JPanel conteneurPrincipal = new JPanel(new BorderLayout());
        conteneurPrincipal.add(rejouer);
        
        this.setContentPane(conteneurPrincipal);
        this.setSize(300,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    

}
