package Game1;

import java.awt.*;
public class Laser{

    private Point ptAncrage;
    private double longueur;
    double angle;
    
    
    public Laser (){
        
    }
    public Laser (double longueur, Point ptAncrage, double angle){
        this.longueur=longueur;
        this.ptAncrage=ptAncrage;
        this.angle=angle;
    }

}
