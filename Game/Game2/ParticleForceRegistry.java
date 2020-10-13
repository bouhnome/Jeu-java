
import java.util.*;
class ParticleForceRegistration{
  public Particle a;
  public ParticleForceGenerator fg;
  public ParticleForceRegistration(Particle a , ParticleForceGenerator fg){
    this.a = a;
    this.fg = fg;
    
  }
}


public class ParticleForceRegistry{

LinkedList<ParticleForceRegistration> registrations = new LinkedList<ParticleForceRegistration>();

public ParticleForceRegistry(){
}

public void add(Particle a, ParticleForceGenerator fg){
	registrations.add(new ParticleForceRegistration(a,fg));
}

public void remove(Particle a , ParticleForceGenerator fg){
  registrations.remove(new ParticleForceRegistration(a,fg));
}

public void clear(){
  registrations.clear();
}

public void updateForces(double dt){
  for(ParticleForceRegistration b : registrations ){
  b.fg.updateForce(b.a,dt);
  }
}

}
