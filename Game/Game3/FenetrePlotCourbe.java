
/**
  * La fenêtre pour faire des dessins
  * 
  */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.LinkedList; 
 public class FenetrePlotCourbe extends JFrame implements ActionListener{


     private Timer monChrono;
     private int temps;
     private static int DELTA_T = 40;
     public double dt = ((double)DELTA_T)/100;
     public static double limite_sol=0.80;
     public ForceRegistry registrySolide;
     public LinkedList<Body> mesSolides;
   

 	public FenetrePlotCourbe(LinkedList<Body> mesSolides,ForceRegistry registrySolide){
 		this.registrySolide=registrySolide;
 		this.setTitle("IHM Courbe - Graphisme ");
 		this.setLayout(null);
 		this.setSize(1400,800);
 		this.setLocation(700,200);
 		this.mesSolides = mesSolides;
 		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		this.setResizable(true);
 		this.setVisible(true);

     monChrono = new Timer(DELTA_T,this);
    temps = 0;

    


 	}




 	
 	public void paint(Graphics g){
    
 		g.setColor(Color.blue);
 		g.fillRect(0,0,this.getWidth(),(int)(limite_sol*this.getHeight()));
 		g.setColor(Color.cyan);
 		g.fillRect(0,(int)(limite_sol*this.getHeight()),this.getWidth(),(int)((1-limite_sol)*this.getHeight()));

      if (!mesSolides.isEmpty())
 			for (int i=2;i<mesSolides.size();i++){
 				g.setColor(mesSolides.get(i).myColor);
 				mesSolides.get(i).dessine(g);
 			}
 		
 	}


 	
 	public void actionPerformed(ActionEvent e){
         temps+=DELTA_T;
         if(temps==5000){
         	registrySolide.clear();
         	registrySolide.add(mesSolides.get(0),new Gravity(new Apoint(0,9.8,0)));
          registrySolide.add(mesSolides.get(1),new Gravity(new Apoint(0,9.8,0)));
         }
         	
 		this.setTitle("Algodoo");
 		
 		registrySolide.updateForces(dt);
 		
	
 		repaint();

 		

   		for (int i=2;i<mesSolides.size();i++){
        mesSolides.get(i).integrate(dt);
      }

    


 	}

     
     public void lancement(){
         monChrono.start();
     }


 }

	
