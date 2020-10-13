

public class UserForce implements ForceGenerator {

	public double theta;
	public double norm;
	public Apoint[] arrowPoints;
	public Apoint point;
	public Apoint force;

	public UserForce(double theta, double norm, Apoint point) {
		arrowPoints = new Apoint[7];
		this.theta = theta;
		this.norm = norm;
		this.point = new Apoint(point);
		this.force = new Apoint(norm * Math.cos(theta), norm * Math.sin(theta), 0);
	}


	public void updateForce(Body a, double dt) {
		arrowPoints[0] = new Apoint(0, 10, 0);
		arrowPoints[1] = new Apoint(0, -10, 0);
		arrowPoints[2] = new Apoint(160, -10, 0);
		arrowPoints[3] = new Apoint(160, -25, 0);
		arrowPoints[4] = new Apoint(200, 0, 0);
		arrowPoints[5] = new Apoint(160, 25, 0);
		arrowPoints[6] = new Apoint(160, 10, 0);
		for (int i = 0; i < arrowPoints.length; i++) {
			arrowPoints[i] = Apoint.multByScalar(arrowPoints[i], 0.6);

		}

		Matrix2 basis = new Matrix2(1, 0, 0, 1);
		basis.rot(theta / 2);
		for (int u = 0; u < arrowPoints.length; u++) {

			Apoint i = new Apoint(basis.data[0], basis.data[2], 0);
			Apoint j = new Apoint(basis.data[1], basis.data[3], 0);

			i = Apoint.multByScalar(i, arrowPoints[u].getX());
			j = Apoint.multByScalar(j, arrowPoints[u].getY());

			arrowPoints[u] = Apoint.add(i, j);
		}

		for (int i = 0; i < arrowPoints.length; i++) {

			arrowPoints[i] = Apoint.add(arrowPoints[i], point);
		}

		basis = new Matrix2(1, 0, 0, 1);
		basis.rot(a.orientation);
		for (int u = 0; u < arrowPoints.length; u++) {

			Apoint i = new Apoint(basis.data[0], basis.data[2], 0);
			Apoint j = new Apoint(basis.data[1], basis.data[3], 0);

			i = Apoint.multByScalar(i, arrowPoints[u].getX());
			j = Apoint.multByScalar(j, arrowPoints[u].getY());

			arrowPoints[u] = Apoint.add(a.position, Apoint.add(i, j));
		}

		a.addForce(force);
		a.addForceAtPoint(force, point);

		int[] x = new int[7];
		x[0] = (int) arrowPoints[0].getX();
		x[1] = (int) arrowPoints[1].getX();
		x[2] = (int) arrowPoints[2].getX();
		x[3] = (int) arrowPoints[3].getX();
		x[4] = (int) arrowPoints[4].getX();
		x[5] = (int) arrowPoints[5].getX();
		x[6] = (int) arrowPoints[6].getX();
		int[] y = new int[7];

		y[0] = (int) arrowPoints[0].getY();
		y[1] = (int) arrowPoints[1].getY();
		y[2] = (int) arrowPoints[2].getY();
		y[3] = (int) arrowPoints[3].getY();
		y[4] = (int) arrowPoints[4].getY();
		y[5] = (int) arrowPoints[5].getY();
		y[6] = (int) arrowPoints[6].getY();
		int[][] coor = new int[2][7];
		coor[0] = x;
		coor[1] = y;
		a.arrowsUserForce.add(coor);

	}
}
