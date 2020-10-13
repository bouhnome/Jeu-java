package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class EcouteurTimer implements ActionListener{
   
    private TerrainDeJeu ter;
   
		public EcouteurTimer(TerrainDeJeu t) {
			ter=t;
		}
    
		public void actionPerformed(ActionEvent e){
			ter.getPanneauTerrain().repaint();
		}
			

    
    }
