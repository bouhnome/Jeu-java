package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class EcouteurMouse implements MouseListener{
   
    private Point PtAncrage;
    private TerrainDeJeu terr; 
    
		public EcouteurMouse(TerrainDeJeu t) {
			terr=t;
		}
    
		public void mousePressed(MouseEvent e){
			PtAncrage=e.getPoint();
		}
        
		public void mouseReleased(MouseEvent e){
			int larg=terr.getPanneauTerrain().getWidth();
			int haut=terr.getPanneauTerrain().getHeight();     
			boolean yN = (e.getY()-PtAncrage.getY()) < 0;
			boolean xN = (e.getX()-PtAncrage.getX()) < 0;
			
			terr.getMatrice().ajouterBarre(new Barre((int)(Math.toDegrees(Math.atan((e.getY()-PtAncrage.getY())/(e.getX()-PtAncrage.getX())))),0.8,Color.BLACK,new Point((int)(PtAncrage.getX()),(int)(PtAncrage.getY())),xN,yN));
			terr.getPanneauTerrain().repaint();
			terr.refreshButton();
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		
}
