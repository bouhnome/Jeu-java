package Game1;

import java.awt.*;
import java.awt.geom.*;

public class Barre{
	
	
	private int angle;  
	private double coeff;
	private int longueur;
	private Color couleur;
	private boolean xNegatif;  
	private boolean yNegatif;
	private Point position;
	private Point2D.Double normale1;
	private Point2D.Double normale2;
	
	public Barre (int a,double c, Color cou, Point p, boolean xN, boolean yN){
		
		angle=a;
		coeff=c;
		couleur=cou;
		position=p;
		longueur=50;
		xNegatif=xN;
		yNegatif=yN;
		
		normale1= new Point2D.Double(Math.cos(Math.toRadians(90+angle)),Math.sin(Math.toRadians(90+angle)));
		normale2= new Point2D.Double(-Math.cos(Math.toRadians(90+angle)),-Math.sin(Math.toRadians(90+angle)));
		
	}
    
    public Barre (int a,double c,int l, Color cou, Point p, boolean xN, boolean yN){
		
		angle=a;
		coeff=c;
        longueur = l;
		couleur=cou;
		position=p;
		xNegatif=xN;
		yNegatif=yN;
		
		normale1= new Point2D.Double(Math.cos(Math.toRadians(90+angle)),Math.sin(Math.toRadians(90+angle)));
		normale2= new Point2D.Double(-Math.cos(Math.toRadians(90+angle)),-Math.sin(Math.toRadians(90+angle)));
		
	}
    
    
	public Point getPosition(){
		return position;
	}
	public int getAngle(){
		return angle;
	}
	public Color getCouleur(){
		return couleur;
	}
	public double getCoeff(){
		return coeff;
	}
	public int getLongueur(){
		return longueur;
	}
	public boolean getXNeg(){
		return xNegatif;
	}
	public boolean getYNeg(){
		return yNegatif;
	}
    public void setLongueur(int l){
		longueur = l;
	}
	public Point2D.Double getNormale(Point2D.Double v){	
         
		Point2D.Double normale=new Point2D.Double();
		if(normale1.getX()*v.getX()+normale1.getY()*v.getY()<=0){
			normale=normale1;
		}else if(normale2.getX()*v.getX()+normale2.getY()*v.getY()<0){
			normale=normale2;
		}
		return normale;
	}
    
	public String toString(){
		return "barre: angle: "+angle+" pt ancrage: "+ position;
	}
}
