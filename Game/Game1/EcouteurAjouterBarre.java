package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class EcouteurAjouterBarre implements ActionListener{
   
    private TerrainDeJeu terr;
   
		public EcouteurAjouterBarre(TerrainDeJeu t) {
			terr=t;
		}
    
		public void actionPerformed(ActionEvent e){
			if(!terr.timerActif){
                terr.ajoutBarreWizard();
            }
			
		}

}
