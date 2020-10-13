package Game1;

import java.awt.*;
import java.awt.geom.*;
public class Baballe {
	
	private Point2D.Double posBalle;
	private Point2D.Double posBalleFut;
	private Point2D.Double vitesse; 
	private double acc=10;
	private final double deltaT=Math.pow(10,-1);
	private int Rayon;
    private PanneauTerrain pan;
	
	
	
	public Baballe(Point2D.Double pos, int r) {
		System.out.println("Voici la baballe!");
		posBalle = pos;
		Rayon = r;
		vitesse=new Point2D.Double(0.0,0.0);
        posBalleFut= new Point2D.Double();
	}
	public Baballe(Point2D.Double pos, int r,Point2D.Double vit,PanneauTerrain p) {
		System.out.println("Voici la baballe! "+vit);
		posBalle = pos;
		Rayon = r;
		vitesse = vit;
        pan = p;
        posBalleFut= new Point2D.Double();
	}
	
	public Point2D.Double getPosBalle(){
		return posBalle;
	}	
	public int getRayon(){
		return Rayon;
	}
    public void setPanneau(PanneauTerrain p){
        pan=p;
    }
	public Point2D.Double getVitesse(){
		return vitesse;
	}
	public void setVitesse(Point2D.Double vitesse){
		this.vitesse = 	vitesse;
	}
	
	public void refresh(){
        posBalle.setLocation(posBalle.getX()+vitesse.getX()*deltaT,posBalle.getY()+vitesse.getY()*deltaT);		
        vitesse.setLocation(vitesse.getX(),vitesse.getY()+acc*deltaT);
	}
    
    public boolean detectImpact(){
        
        posBalleFut.setLocation(posBalle.getX()+vitesse.getX()*deltaT,posBalle.getY()+vitesse.getY()*deltaT);
        int deltaVitX;
        int deltaVitY;
        if(Math.abs(vitesse.getX())>=Math.abs(vitesse.getY())){
            double y = posBalle.getY();
            if (vitesse.getX() == 0){
            deltaVitX = 0;
            }else{ 
                deltaVitX = (int)(vitesse.getX()/Math.abs(vitesse.getX()));
                double alpha = Math.atan2(vitesse.getY(),vitesse.getX());
                for (int i = (int)posBalle.getX()/pan.getL();i!=(int)posBalleFut.getX(); i+=deltaVitX){
                    for(int j=(int)(y-1.0); j<Math.max(0,(int)(y+1.0));j++){
                        if (pan.getMatrice().getMatrice()[j][i] instanceof Object){
                            impact(pan.getMatrice().getMatrice()[j][i]);
                            return true;
                            
                        }
                    }
                    y=y+Math.tan(alpha);
                }
            }
        }else{
            double x = posBalle.getX();
            if (vitesse.getY() == 0){
                deltaVitY = 0;
            }else{ 
                deltaVitY = (int)(vitesse.getY()/Math.abs(vitesse.getY()));
                double alpha = Math.atan2(vitesse.getX(),vitesse.getY());
                for (int j = (int)posBalle.getY()/pan.getH();j!=(int)posBalleFut.getY(); j+=deltaVitY){
                    for(int i=(int)(x-1.0); i<Math.max(0,(int)(x+1.0));i++){
                        if (pan.getMatrice().getMatrice()[j][i] instanceof Object){
                            impact(pan.getMatrice().getMatrice()[j][i]);
                            return true;
                        }
                    }
                    x=x+Math.tan(alpha);
                }
            }
            
        }
        
        return false;
    }
    
    public void impact(Object o){
        if (o instanceof Barre)
            impact((Barre)o);
        if (o instanceof Laser)
            impact((Laser)o);
        if (o instanceof Gobelet)
            impact((Gobelet)o);
        
    }
    
	public void impact(Barre b){
		Point2D.Double norm=b.getNormale(vitesse);
		double nouvVitX;
		double nouvVitY;
		Point2D.Double vitT=new Point2D.Double();
		Point2D.Double vitN=new Point2D.Double();
		double prodS=norm.getX()*vitesse.getX()+norm.getY()*vitesse.getY();
		vitN.setLocation(prodS*norm.getX(),prodS*norm.getY());
		vitT.setLocation(vitesse.getX()-prodS*norm.getX(),vitesse.getY()-prodS*norm.getY());
		nouvVitX=b.getCoeff()*(vitT.getX()-vitN.getX());
		nouvVitY=b.getCoeff()*(vitT.getY()-vitN.getY());
		
		vitesse.setLocation(nouvVitX,nouvVitY);
		
	}
    
    public void impact(Laser l){
        defaite(); 
    }
    public void impact(Gobelet g){
        victoire();
    }
    
	public void dessiner(Graphics g) {	
		g.setColor(Color.RED);
		g.fillOval((int)(posBalle.getX())-Rayon, (int)(posBalle.getY())-Rayon, Rayon, Rayon);
	}
	
	public void defaite(){
        GameOver g=new GameOver();
        pan.getTerrain().dispose();
    }
    
    public void victoire(){
        pan.getTerrain().dispose(); 
        TerrainDeJeu t=new TerrainDeJeu(pan.getTerrain().getNiveau()+1);
    }
	
}
