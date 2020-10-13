
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.Color;

public class PolygonClickListener extends MouseAdapter {
	LinkedList<Integer> xCoor;
	LinkedList<Integer> yCoor;
	LinkedList<Body> mesSolides;
	ForceRegistry registrySolide;

	public PolygonClickListener(LinkedList<Body> mesSolides, ForceRegistry registrySolide) {
		super();
		this.mesSolides = mesSolides;
		this.registrySolide = registrySolide;
		xCoor = new LinkedList<Integer>();
		yCoor = new LinkedList<Integer>();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		xCoor.add(e.getX());
		yCoor.add(e.getY());

	}

	public void createPolygon() {

		int dice = (int) (Math.random() * 5);
		Color couleur;
		if (dice == 0) {
			couleur = Color.white;
		} else if (dice == 1) {
			couleur = Color.red;
		} else if (dice == 2) {
			couleur = Color.yellow;
		} else if (dice == 3) {
			couleur = Color.green;
		} else if (dice == 4) {
			couleur = Color.orange;
		} else {
			couleur = Color.black;
		}

		Apoint[] points = new Apoint[xCoor.size()];
		for (int i = 0; i < xCoor.size(); i++) {
			points[i] = new Apoint(xCoor.get(i), yCoor.get(i), 0);
		}
		mesSolides.add(new Body(1, points, couleur,
				new Apoint((int) (Math.random() * 1400), (int) (Math.random() * 800 * 0.8), 0), new Apoint(200, 200, 0),
				0.25));
		registrySolide.add(mesSolides.get(mesSolides.size() - 1), new Gravity(new Apoint(0, 9.8, 0)));
	}

	public void addForce() {

	}

}
