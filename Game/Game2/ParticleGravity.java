
public class ParticleGravity implements ParticleForceGenerator{

Apoint g; 

public ParticleGravity(Apoint g){
this.g = new Apoint(g); 
}

public void updateForce(Particle a, double dt){
  if(!a.hasFiniteMass())
    return;
  a.addForce( Apoint.multByScalar(g,a.getMass() ));
}

}
