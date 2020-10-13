
import java.util.*;
import java.awt.Graphics;
import java.awt.Color;
public class Test_3{
	
	static ForceRegistry registrySolide = new ForceRegistry();      
 	static LinkedList<Body> listOfSolids = new LinkedList<Body>();

 	public static void main(String [] args){

 		
		Gravity poidsSolide = new Gravity(new Apoint(0,9.8,0));
		UserForce maForce = new UserForce(-Math.PI/2,0.0001,new Apoint(80,0,0));
		UserForce maForce1 = new UserForce(Math.PI,0.0001,new Apoint(0,-80,0));
		UserForce maForce2= new UserForce(Math.PI/2,0.0001,new Apoint(-80,0,0));
		UserForce maForce3= new UserForce(0,0.0001,new Apoint(0,80,0));
		UserForce maForce4 = new UserForce(0,7,new Apoint());

		
		Apoint [] [] points = new Apoint[2][];
		points[0] = new Apoint [12];
		points[0][0]=new Apoint(-20,80,0);
		points[0][1]=new Apoint(20,80,0);
		points[0][2]=new Apoint(20,20,0);
		points[0][3]=new Apoint(80,20,0);
		points[0][4]=new Apoint(80,-20,0);
		points[0][5]=new Apoint(20,-20,0);
		points[0][6]=new Apoint(20,-80,0);
		points[0][7]=new Apoint(-20,-80,0);
		points[0][8]=new Apoint(-20,-20,0);
		points[0][9]=new Apoint(-80,-20,0);
		points[0][10]=new Apoint(-80,20,0);
		points[0][11]=new Apoint(-20,20,0);

		points[1] = new Apoint [8];
		points[1][0]=new Apoint(40,20,0);
		points[1][1]=new Apoint(60,20,0);
		points[1][2]=new Apoint(60,60,0);
		points[1][3]=new Apoint(120,100,0);
		points[1][4]=new Apoint(100,20,0);
		points[1][5]=new Apoint(100,-20,0);
		points[1][6]=new Apoint(80,0,0);
		points[1][7]=new Apoint(40,0,0);
		

		listOfSolids.add(new Body(1,points[0],Color.blue,new Apoint(400,400,0),new Apoint(20,0,0),0));
		listOfSolids.add(new Body(1,points[1],Color.blue,new Apoint(500,500,0),new Apoint(50,0,0),0.23));

 		FenetrePlotCourbe fenetre = new FenetrePlotCourbe(listOfSolids,registrySolide);
 		fenetre.lancement();

		Menu menuWindow = new Menu(listOfSolids,registrySolide);

		

		
	}
}
