

public class Matrix2 {
	public static double[] data = new double[4];

	
	public Matrix2(double a0, double a1, double a2, double a3) {
		data[0] = a0;
		data[1] = a1;
		data[2] = a2;
		data[3] = a3;
	}

	public Matrix2() {
		data[0] = 0;
		data[1] = 0;
		data[2] = 0;
		data[3] = 0;
	}

	
	public static Matrix2 mult(Matrix2 o) {
		return new Matrix2(data[1] * o.data[2] + data[0] * o.data[0], data[1] * o.data[3] + data[0] * o.data[1],
				data[3] * o.data[2] + data[2] * o.data[0], data[3] * o.data[3] + data[2] * o.data[1]

		);

	}


	public Matrix2 multByScalar(double lambda) {
		return new Matrix2(data[0] * lambda, data[1] * lambda, data[2] * lambda, data[3] * lambda);
	}

	
	public Apoint multByVec(Apoint vec) {
		Apoint result = new Apoint(data[0] * vec.getX() + data[1] * vec.getY(),
				data[2] * vec.getX() + data[3] * vec.getY(), 0);
		return result;
	}

	
	public double determinant() {
		return data[0] * data[3] - data[2] * data[1];
	}

	
	public Matrix2 inverse() {
		double det = determinant();
		if (det == 0)
			return new Matrix2();
		Matrix2 temp = new Matrix2(data[4], -data[2], -data[3], data[1]

		);
		return temp.multByScalar(1 / det);
	}


	public Matrix2 transpose() {
		return new Matrix2(data[0], data[3], data[2], data[4]);
	}


	public Matrix2 rotMat(double theta) {
		return new Matrix2(Math.cos(theta), -Math.sin(theta), Math.sin(theta), Math.cos(theta));
	}


	public Matrix2 rot(double theta) {
		return mult(rotMat(theta));
	}

	
}
