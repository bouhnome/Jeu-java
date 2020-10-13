
import java.util.*;

class ForceRegistration {
	private Body a;
	private ForceGenerator fg;

	public ForceRegistration(Body a, ForceGenerator fg){
		this.a = a;
		this.fg = fg;

	}

	public ForceGenerator getFg() {
		return this.fg;
	}

	public Body get_a() {
		return this.a;
	}
}

public class ForceRegistry {

	LinkedList<ForceRegistration> registrations = new LinkedList<ForceRegistration>();

	public ForceRegistry() {  
	}

	public void add(Body a, ForceGenerator fg) {
		registrations.add(new ForceRegistration(a, fg));
	}

	public void remove(Body a, ForceGenerator fg) {                
		registrations.remove(new ForceRegistration(a, fg));
	}

	public void clear() {              
		registrations.clear();
	}

	public void updateForces(double dt) {     
		for (ForceRegistration b : registrations) {
			b.getFg().updateForce(b.get_a(), dt);
		}
	}

}
