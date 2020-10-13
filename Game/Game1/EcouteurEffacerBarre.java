package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class EcouteurEffacerBarre implements ActionListener{
   
    private TerrainDeJeu terr;
   
		public EcouteurEffacerBarre(TerrainDeJeu t) {
			terr=t;
		}
    
		public void actionPerformed(ActionEvent e){
			if(!terr.timerActif){
                terr.getMatrice().effacerBarre();
                terr.getPanneauTerrain().repaint();
                terr.refreshButton();
            }
		}

}
