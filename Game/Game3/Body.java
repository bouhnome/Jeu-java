

import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

public class Body {
	public LinkedList<int[][]> arrowsUserForce = new LinkedList<int[][]>();
	public double inverseMass;
	public double linearDamping;
	public double angularDamping;
	public Apoint position;
	public Apoint velocity;
	public double orientation;
	public double angularSpeed;
	public double angularAcceleration;
	public Apoint forceAccum;
	public Apoint torqueAccum;
	public Apoint acceleration;
	public Apoint lastFrameAcceleration;
	public Apoint[] points;
	public Apoint[] pointstt;
	public Color myColor;
	public Apoint centerOfMass;
	public Matrix2 basis;
	public double i0;

	public Body(double inverseMass, Apoint[] points, Color myColor, Apoint position, Apoint velocity,
			double angularSpeed) {
		this.linearDamping = 1;
		this.angularDamping = 1;
		this.angularAcceleration = 0;
		this.i0 = 3;
		this.velocity = new Apoint(velocity);
		this.inverseMass = inverseMass;
		this.position = new Apoint(position);
		this.myColor = myColor;
		this.points = new Apoint[points.length];
		this.pointstt = new Apoint[points.length];
		this.orientation = 0;
		this.acceleration = new Apoint();
		this.forceAccum = new Apoint();
		this.torqueAccum = new Apoint();
		basis = new Matrix2(1, 0, 0, 1);
		centerOfMass = getCenterOfMass(points);
		for (int i = 0; i < points.length; i++) {
			this.points[i] = Apoint.substract(points[i], centerOfMass);
			this.pointstt[i] = new Apoint(this.points[i]);
		}
		this.angularSpeed = angularSpeed;
	}

	
	public boolean hasFiniteMass() {
		if (inverseMass == 0)
			return false;
		else
			return true;
	}

	
	public Apoint getCenterOfMass(Apoint[] points) {
		Apoint result = new Apoint();
		for (int i = 0; i < points.length; i++) {
			result = Apoint.add(result, points[i]);
		}
		result = Apoint.multByScalar(result, 1.0 / points.length);
		return result;

	}

	
	public double cmptIo() {
		double result = 0;
		for (int i = 0; i < points.length - 1; i++) {
			double xi = points[i].getX();
			double yi = points[i].getY();
			double xi1 = points[i + 1].getX();
			double yi1 = points[i + 1].getY();
			result += (xi * yi1 - xi1 * yi) * (xi * yi1 + 2 * xi * yi + 2 * xi1 * yi1 + xi1 * yi);
		}
		double xn = points[points.length - 1].getX();
		double yn = points[points.length - 1].getY();
		double x1 = points[0].getX();
		double y1 = points[0].getY();
		result += (xn * y1 - x1 * yn) * (xn * y1 + 2 * xn * yn + 2 * x1 * y1 + x1 * yn);
		result = result / 24;
		result = Math.abs(result);
		return result;
	}

	
	public void addForce(Apoint force) {
		forceAccum = Apoint.add(forceAccum, force);
	}

	
	public void addForceAtPoint(Apoint force, Apoint point) {
		forceAccum = Apoint.add(forceAccum, force);
		Apoint moment = Apoint.crossProduct(point, force);
		torqueAccum = Apoint.add(torqueAccum, moment);
	}

	
	public void clearAccumulators() {
		forceAccum = new Apoint();
		torqueAccum = new Apoint();
	}

	
	public void updatePoints() {
		basis.rot(orientation);

		for (int u = 0; u < points.length; u++) {

			Apoint i = new Apoint(basis.data[0], basis.data[2], 0);
			Apoint j = new Apoint(basis.data[1], basis.data[3], 0);

			i = Apoint.multByScalar(i, points[u].getX());
			j = Apoint.multByScalar(j, points[u].getY());

			pointstt[u] = Apoint.add(position, Apoint.add(i, j));
		}

	}

	
	public int[] getXcoordinates() {
		int[] result = new int[pointstt.length];
		for (int i = 0; i < pointstt.length; i++)
			result[i] = (int) (pointstt[i].getX());
		return result;
	}


	public int[] getYcoordinates() {
		int[] result = new int[pointstt.length];
		for (int i = 0; i < pointstt.length; i++)
			result[i] = (int) (pointstt[i].getY());
		return result;
	}

	public void dessine(Graphics g) {
		updatePoints();
		int[] coordX = getXcoordinates();
		int[] coordY = getYcoordinates();

		g.setColor(myColor);
		g.fillPolygon(coordX, coordY, coordX.length);
		g.setColor(Color.black);
		g.fillOval((int) (position.getX()), (int) (position.getY()), 4, 4);
		for (int[][] k : arrowsUserForce) {
			g.setColor(Color.black);
			g.fillPolygon(k[0], k[1], k[0].length);
		}
		arrowsUserForce.clear();

	}

	public void integrate(double dt) {
		dt = Math.abs(dt);

		acceleration = Apoint.multByScalar(forceAccum, inverseMass);
		velocity = Apoint.add(velocity, Apoint.multByScalar(acceleration, dt));
		velocity = Apoint.multByScalar(velocity, Math.pow(linearDamping, dt));
		position = Apoint.add(position, Apoint.multByScalar(velocity, dt));

		angularAcceleration = torqueAccum.getZ() / i0;
		angularSpeed = angularSpeed + angularAcceleration * dt;
		angularSpeed = angularSpeed * Math.pow(angularDamping, dt);
		orientation = orientation + angularSpeed * dt;

		clearAccumulators();

		
		int count = 0;

		for (Apoint p : pointstt) {
			if (p.getY() > 800 * FenetrePlotCourbe.limite_sol) {
				position = Apoint.add(position, new Apoint(0, 800 * FenetrePlotCourbe.limite_sol - p.getY(), 0));
				velocity = new Apoint(velocity.getX(), -velocity.getY(), velocity.getZ());
				velocity = Apoint.multByScalar(velocity, 0.6);
				count++;
			}
			if (p.getY() < 22) {        
				position = Apoint.add(position, new Apoint(0, 22 - p.getY(), 0));
				velocity = new Apoint(velocity.getX(), -velocity.getY(), velocity.getZ());
				velocity = Apoint.multByScalar(velocity, 0.6);

			}
			if (p.getX() < 0) {
				position = Apoint.add(position, new Apoint(-p.getX(), 0, 0));
				velocity = new Apoint(-velocity.getX(), velocity.getY(), velocity.getZ());
				velocity = Apoint.multByScalar(velocity, 0.6);

			}
			if (p.getX() > 1400) {
				position = Apoint.add(position, new Apoint(1400 - p.getX(), 0, 0));
				velocity = new Apoint(-velocity.getX(), velocity.getY(), velocity.getZ());
				velocity = Apoint.multByScalar(velocity, 0.6);

			}
		}
		if (count == 1)
			angularSpeed += 0.0003;
		if (count == 2) {
			angularSpeed = 0;

			linearDamping = 0.8;
		}
	}

	
}
