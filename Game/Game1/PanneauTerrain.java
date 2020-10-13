package Game1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class PanneauTerrain extends JPanel {
	
  private Matrice matrice;
  private Baballe balle;
  private TerrainDeJeu terr;
  private int l;
  private int h;
  
    public PanneauTerrain(Matrice matrice, Baballe balle, TerrainDeJeu t) {
        this.matrice =matrice;    
        this.balle=balle;
        terr=t;
        int haut = getHeight();
        int larg = getWidth();
      
        
	}
    
    public Matrice getMatrice() {return matrice;}
    public int getL() {return l;}
    public int getH() {return h;}
    public TerrainDeJeu getTerrain(){return terr;}
     
    public void paintComponent(Graphics g) {			
        super.paintComponent(g);
        
        for(int i=0;i<matrice.getMatrice().length;i++) {
            
            int haut = getHeight();
            int larg = getWidth();
            
            l = larg/matrice.getMatrice()[0].length;  
            h = haut/matrice.getMatrice().length;    
            
            int y1 = i*h;                    
            
            for(int j=0;j<matrice.getMatrice()[0].length;j++) {
                if       (matrice.getMatrice()[i][j]==null) {
                    g.setColor(Color.WHITE);

                } else if(matrice.getMatrice()[i][j] instanceof Barre) {
                    g.setColor(((Barre)matrice.getMatrice()[i][j]).getCouleur());


                } else if(matrice.getMatrice()[i][j] instanceof Laser) {
                    g.setColor(Color.ORANGE);
                    
                } else if(matrice.getMatrice()[i][j] instanceof Gobelet) {
                    g.setColor(Color.GREEN);
                }
                
                int x1 = j*l;         
                
                g.fillRect(x1,y1,l,h);
            }
        }
        balle.detectImpact();
        if(terr.timerActif){
            balle.refresh(); 
        }

        balle.dessiner(g); 
    }
	

}
