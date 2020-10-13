package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class EcouteurStart implements ActionListener{
   
    private FenetrePrincipale fen;
   
		public EcouteurStart(FenetrePrincipale f) {
			fen=f;
		}
    
		public void actionPerformed(ActionEvent e){
            
			TerrainDeJeu f1 = new TerrainDeJeu (0); 
			fen.dispose();

    
    }

}
