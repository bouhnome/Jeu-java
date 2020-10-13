

public class Gravity implements ForceGenerator{
		public Apoint gravity;
		
		public Gravity(Apoint gravity){
			this.gravity = new Apoint(gravity);	  
		}
		
		public void updateForce(Body body, double duration){
				if (!body.hasFiniteMass()) return;
				body.addForce(Apoint.multByScalar(gravity,1.0/body.inverseMass));
		}
}
