package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class EcouteurCommencer implements ActionListener{
   
    private TerrainDeJeu terr;
   
    public EcouteurCommencer(TerrainDeJeu t) {
        terr=t;
    }
    
    public void actionPerformed(ActionEvent e){
        terr.startTimer();
        terr.getPanneauTerrain().removeMouseListener(terr.getEcouteurMouse()); 
    }
}
