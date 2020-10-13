package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class EcouteurRejouer implements ActionListener{
   
    private GameOver ga;
    
    public EcouteurRejouer(GameOver g) {
        ga=g;
    }
    
    public void actionPerformed(ActionEvent e){
        ga.dispose();
        new TerrainDeJeu(0);
    }
}
