public class Apoint {    
    
    private double x;
    private double y;
    private double z;
    
    public Apoint(){
        this(0.0,0.0,0.0);
    }
    
    public Apoint(Apoint r){      
        this.x = r.x;
        this.y = r.y;
        this.z = r.z;
    }
    
    public Apoint(double x, double y, double z){     
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getZ(){
        return this.z;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double x){
        this.y=y;
    }
    public void setZ(double x){
        this.z=z;
    }




    
    public static double magnitude(Apoint a){
        return Math.sqrt(a.x*a.x+a.y*a.y+a.z*a.z);
    }
    
    public static double magnitudeSquare(Apoint a){
        return a.x*a.x+a.y*a.y+a.z*a.z;
    }
    
    public static Apoint add(Apoint a, Apoint b){
        return new Apoint(a.x+b.x,a.y+b.y,a.z+b.z);
    }
    
    public static Apoint multByScalar(Apoint r ,double a){ 
        return new Apoint(r.x*a,r.y*a,r.z*a);
    }
    
    public static Apoint opposite(Apoint a){
        return multByScalar(a,-1);
    }
    
    public static Apoint substract(Apoint a , Apoint b){
        return add(a,opposite(b));
    }
    
    public static double distance( Apoint a , Apoint b) {
        return magnitude(substract(a,b)) ;
    }
    
    
    
    public static Apoint normalize(Apoint a){
        return multByScalar(a,1/magnitude(a));
    }
    
    public static Apoint componentProduct(Apoint a, Apoint b){
        return new Apoint(a.x*b.x,a.y*b.y,a.z*b.z);
    }
    
    public static double dotProduct(Apoint a, Apoint b){
        double s = a.x*b.x+a.y*b.y+a.z*b.z;
        return s;
    }
    
    public static Apoint crossProduct(Apoint a, Apoint b){
        return new Apoint(   a.y*b.z-a.z*b.y   ,   a.z*b.x-a.x*b.z   ,   a.x*b.y-a.y*b.x  );
    }
    
    public String toString() {
        return  "[" +  x  + "," + y  + ","+z+"]";
    }    
    
}

