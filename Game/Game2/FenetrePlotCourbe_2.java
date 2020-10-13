
/**
 * La fenêtre pour faire des dessins
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 
public class FenetrePlotCourbe extends JFrame implements ActionListener{

	
    private LinkedList<Particle> mesParticules;
    private Timer monChrono;
    private int temps;
    private static int DELTA_T = 40;
    public double dt = ((double)DELTA_T)/100;
    public double limite_sol=0.80;
    public int niter =0;
    public BufferedImage canon;
	

	public FenetrePlotCourbe(LinkedList<Particle> mesParticules){
		this.mesParticules = mesParticules;
		this.setTitle("IHM Courbe - Graphisme ");
		this.setLayout(null);
		this.setSize(1400,800);
		this.setLocation(700,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
        
        monChrono = new Timer(DELTA_T,this);
        temps = 0;


        this.canon = null;    
		int imageWidth = -1;
		int imageHeight = -1;
		int fileSize = -1;
		try {
    		File imageFile = new File("./canon.png");
    		fileSize = (int) imageFile.length();
    		canon = ImageIO.read(imageFile);
    		imageWidth = canon.getWidth();
    		imageHeight = canon.getHeight();
			} catch (IOException e) {
   				 e.printStackTrace();
			}
        
		
	}
	

	
	

	public void paint(Graphics g){

		
		g.setColor(Color.blue);
		g.fillRect(0,0,this.getWidth(),(int)(limite_sol*this.getHeight()));
		g.setColor(Color.cyan);
		g.fillRect(0,(int)(limite_sol*this.getHeight()),this.getWidth(),(int)((1-limite_sol)*this.getHeight()));


		g.setColor(Color.blue);
		g.fillRect((int)(1000-30),(int)(0.80*800-5*2*30*2),(int)(2*30),(int)(10*2*30));

		if (!mesParticules.isEmpty())
			for (Particle a : mesParticules){
				g.setColor(a.myColor);
				a.dessine(g);
			}
			g.drawImage(canon,0,(int) (0.8*800)-105,this);

	}
	
	
	
	public void actionPerformed(ActionEvent e){
        temps+=DELTA_T;
        niter++;

		this.setTitle("IHM Courbe - Graphisme / temps : "+temps);

		test.simulate(dt,niter);

		repaint();		
	}
    

    public void lancement(){
        monChrono.start();
    }


	
	
}
		

		
