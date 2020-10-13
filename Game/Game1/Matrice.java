package Game1;


import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Matrice {
	
	private Point2D.Double pointInit;    
	private int xInit;
	private int yInit;
	private int longueur; 
	private int hauteur;
	private Object[][] tab;           
    private LinkedList<Object[][]> liste;
    private int nBarres;           
    private final int MAX_BARRES;
    
	
	
	public Matrice(int xBalleInit,int yBalleInit){
		longueur=550;
		hauteur=550;
		tab  = new Object[hauteur][longueur];
		xInit=xBalleInit;
		yInit=yBalleInit;
		pointInit=new Point2D.Double(xBalleInit,yBalleInit);
        liste= new LinkedList<Object[][]>();
        this.ajouterBordLaser();
        nBarres=0;
        MAX_BARRES=5;
	}
	
	public Matrice(){
		longueur=550;
		hauteur=550;
		tab  = new Object[hauteur][longueur];
        xInit=longueur/2;
		yInit=20;
		pointInit=new Point2D.Double(xInit,yInit);
		liste= new LinkedList<Object[][]>();
        this.ajouterBordLaser();
        nBarres=0;
        MAX_BARRES=5;
	}
	
    
	public boolean estLibre(int x0,int y0){
		return tab[y0][x0] == null;
	}
	public boolean estLibre(Point p){
		return tab[(int)p.getX()][(int)p.getY()] == null;
	}
	
	public boolean ajouterObjet(Object o,int x, int y){
		if(!estLibre(y,x)) 
			return false;
		tab[y][x] = o;
		return true;
	}
	public boolean ajouterObjet(Object o,Point p){
		return ajouterObjet(o, (int)p.getX(), (int)p.getY());
	}
	
    public boolean ajouterBarre(Barre b){      
			
		if(nBarres<MAX_BARRES){
			double l = b.getLongueur();  
			int x0 = (int)(b.getPosition().getX());
			int y0 = (int)(b.getPosition().getY());
			double angle = b.getAngle();
			boolean espaceEstLibre = true;
			double y=y0; 
			
			if (angle >90)      angle = angle-180;
			if (angle <= -90)   angle = angle+180;
			boolean grosAngle = Math.abs(angle)>77;
			boolean tresGrosAngle = Math.abs(angle)>83;
			angle = Math.toRadians(angle);
			
			
			if(!b.getXNeg() || grosAngle){ 
				for(int i=Math.max(0,x0); i<Math.min(longueur-1,x0+(int)((l)*Math.cos(angle)));i++){
					for(int j=(int)Math.max(0.0,y-3.0); j<Math.max(0,Math.min(hauteur-1,(int)y+3));j++){                   
						if(!estLibre(i,j)) {
							espaceEstLibre = false; 
							return false;
						}
					}
					y=y+Math.tan(angle);                  
				}
				
				saveTab();
				
				if(!grosAngle){
					for(int i=Math.max(0,x0); i<Math.min(longueur-1,x0+(int)((l)*Math.cos(angle)));i++){
						for(int j=(int)Math.max(0.0,y-3.0); j<Math.max(0,Math.min(hauteur-1,(int)y+3));j++){
							ajouterObjet(b,i,j);
						}
						y=y+Math.tan(angle);
					}
				}else if(!tresGrosAngle){
					for(int i=Math.max(0,x0); i<Math.min(longueur-1,x0+(int)((l)*Math.cos(angle)));i++){
						for(int j=(int)Math.max(0.0,y-5.0); j<Math.max(0,Math.min(hauteur-1,(int)y+5));j++){
							ajouterObjet(b,i,j);
						}
						y=y+Math.tan(angle);
					}
				} else{
					for(int i=Math.max(0,x0); i<Math.min(longueur-1,x0+Math.max(5,(int)((l)*Math.cos(angle))));i++){
						for(int j=Math.max(0,y0); j<Math.max(0,Math.min(hauteur-1,y0+l));j++){
							ajouterObjet(b,i,j);
						}
					}
				}
				nBarres++;
				return true;
			}else{
				for(int i=Math.max(0,x0); i>Math.max(0,x0-(int)((l)*Math.cos(angle)));i--){
					for(int j=(int)Math.max(0.0,y-3.0); j>Math.max(0,Math.min(hauteur-1,(int)y+3));j--){                   
						if(!estLibre(i,j)) {
							espaceEstLibre = false; // 
							return false;
						}
					}
					y=y-Math.tan(angle);                  
				}
				saveTab();
				
				for(int i=Math.max(0,x0); i>Math.max(0,x0-(int)((l)*Math.cos(angle)));i--){
                    for(int j=(int)Math.max(0.0,y-3.0); j>Math.max(0,Math.min(hauteur-1,(int)y+3));j--){
                        ajouterObjet(b,i,j);
                    }
                    y=y-Math.tan(angle);
                }
				nBarres++;
				return true;
				
			}
		}else{return false;}
	}
    
    public void ajouterLaser(Point PtAncrage,double l,double angle){
       
		int x0 = (int)(PtAncrage.getX());
		int y0 = (int)(PtAncrage.getY());
		boolean espaceEstLibre = true;
        
        double y=y0;
        
        if (angle >90)      angle = angle-180;
        if (angle <= -90)   angle = angle+180;
        boolean grosAngle = Math.abs(angle)>77;
        boolean tresGrosAngle = Math.abs(angle)>83;
        angle = Math.toRadians(angle);
        
		
        if(!grosAngle){
            for(int i=Math.max(0,x0); i<Math.min(longueur-1,x0+(int)((l)*Math.cos(angle)));i++){
                for(int j=(int)Math.max(0.0,y-3.0); j<Math.max(0,Math.min(hauteur-1,(int)y+3));j++){
                    ajouterObjet(new Laser(),i,j);
                }
                y=y+Math.tan(angle);
            }
        }else if(!tresGrosAngle){
            for(int i=Math.max(0,x0); i<Math.min(longueur-1,x0+(int)((l)*Math.cos(angle)));i++){
                for(int j=(int)Math.max(0.0,y-5.0); j<Math.max(0,Math.min(hauteur-1,(int)y+5));j++){
                    ajouterObjet(new Laser(),i,j);
                }
                y=y+Math.tan(angle);
            }
        } else{
            for(int i=Math.max(0,x0); i<Math.min(longueur-1,x0+Math.max(5,(int)((l)*Math.cos(angle))));i++){
                for(int j=Math.max(0,y0); j<Math.max(0,Math.min(hauteur-1,y0+l));j++){
                    ajouterObjet(new Laser(),i,j);
                }
            }
        }
        
        
    }
	
	public void ajouterBordLaser(){
        for(int i=0;i<tab.length;i++){
			for(int j=0; j<tab[0].length;j++){
                if(i<5||j<5||i>(tab.length-6)||j>(tab[0].length-6))
                    tab[i][j]=new Laser();
			}
		}
	}
	
    public void ajouterGobelet(Point PtAncrage,int longueur,int hauteur){  
        for(int i=(int)PtAncrage.getX();i<((int)PtAncrage.getX()+longueur);i++){
			for(int j=(int)PtAncrage.getY(); j<((int)PtAncrage.getY()+hauteur);j++){
                tab[i][j]=new Gobelet();
			}
		}
	}
    
	
	public	int 		getLongeur()	{return longueur;}
	public 	int			getHauteur()	{return hauteur;}
	public 	int			getXInit() 		{return xInit;}
	public 	int			getYInit()		{return yInit;}
	public 	Point2D.Double		getPointInit()	{return pointInit;}
	public 	Object[][]	getMatrice()	{return tab;}
	
	
	public	void	setLongeur(int l)		{longueur	= l;}
	public	void	setHauteur(int h)		{hauteur	= h;}
	public 	void	setXInit(int x)			{xInit		= x;}
	public 	void	setYInit(int y)			{yInit		= y;}
	public 	void	setPointInit(Point2D.Double p)	{pointInit = p;}

	public void printMat(){
		char [][] tabS=new char[hauteur][longueur];
		for(int i=0;i<tab.length;i++){
			for(int j=0; j<tab[0].length;j++){
				if(tab[i][j] instanceof Barre){
					tabS[i][j]='b';
				}else if(tab[i][j] instanceof Laser){
					tabS[i][j]='l';
				}else if(tab[i][j]==null){
					tabS[i][j]='.';
				}
			}
		}
		
		for (char[] is : tabS) {
			System.out.println(Arrays.toString(is));
		}
	}
    
    public void saveTab(){
        Object[][] temp=new Object[hauteur][longueur];
        for(int i=0;i<tab.length;i++){
			for(int j=0; j<tab[0].length;j++){
				temp[i][j]=tab[i][j];
			}
		}
        liste.add(temp);
    }
    
    public void effacerBarre(){
        if(liste.size()>=1){
            tab=liste.getLast();
            liste.removeLast();  
            nBarres--;  
        }
    }
    
    public int getBarresRestantes(){ 
		return MAX_BARRES-nBarres;
	}
	
}
