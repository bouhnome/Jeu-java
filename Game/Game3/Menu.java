
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Menu extends JFrame implements ActionListener {

	private JButton addSolid;
	private JButton addForce;
	private JButton removeForces;
	private JButton removeSolids;
	private JButton addAirFriction;
	private JButton removeAirFriction;
	private JButton addGravity;
	private JLabel monLabel;
	private JTextField numero;
	int choix = 0;

	LinkedList<Body> mesSolides;
	ForceRegistry registrySolide;

	public Menu(LinkedList<Body> mesSolides, ForceRegistry registrySolide) {

		this.mesSolides = mesSolides;
		this.registrySolide = registrySolide;

		this.setTitle("Menu");

		this.setLayout(null);
		this.setSize(1400 / 2, 800 / 2);
		this.setLocation(700, 200);
		this.setResizable(false);
		this.setVisible(true);

		numero = new JTextField();
		numero.setBounds(50, 170, 60, 30);
		add(numero);

		monLabel = new JLabel("Number of the solid to which you want to attach the force : ");
		monLabel.setBounds(50, 130, 550, 30);
		add(monLabel);

		addSolid = new JButton("Add solid");
		addSolid.setBounds(50, 50, 130, 30);
		addSolid.addActionListener(this);
		add(addSolid);

		addForce = new JButton("Add Force");
		addForce.setBounds(50, 210, 130, 30);
		addForce.addActionListener(this);
		add(addForce);

		removeForces = new JButton("Remove forces");
		removeForces.setBounds(450, 50, 130, 30);
		removeForces.addActionListener(this);
		add(removeForces);

		removeSolids = new JButton("Remove Solids");
		removeSolids.setBounds(50, 90, 130, 30);
		removeSolids.addActionListener(this);
		add(removeSolids);

		addAirFriction = new JButton("add air friction");
		addAirFriction.setBounds(250, 50, 130, 30);
		addAirFriction.addActionListener(this);
		add(addAirFriction);

		removeAirFriction = new JButton("remove air friction");
		removeAirFriction.setBounds(250, 90, 130, 30);
		removeAirFriction.addActionListener(this);
		add(removeAirFriction);

		addGravity = new JButton("add gravity");
		addGravity.setBounds(450, 90, 130, 30);
		addGravity.addActionListener(this);
		add(addGravity);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addSolid) {
			CreatePolygonWindow creationWindow = new CreatePolygonWindow(mesSolides, registrySolide);

		}
		if (e.getSource() == addForce) {

			AddForcesWindow addForcesWindow = new AddForcesWindow(mesSolides, registrySolide, 0);
		}
		if (e.getSource() == removeForces) {
			registrySolide.clear();
		}
		if (e.getSource() == removeSolids) {
			mesSolides.clear();
		}
		if (e.getSource() == addAirFriction) {
			for (Body a : mesSolides) {
				a.linearDamping = 0.99;
				a.angularDamping = 0.99;
			}
		}
		if (e.getSource() == removeAirFriction) {
			for (Body a : mesSolides) {
				a.linearDamping = 1;
				a.angularDamping = 1;
			}
		}
		if (e.getSource() == addGravity) {
			for (Body a : mesSolides) {
				registrySolide.add(a, new Gravity(new Apoint(0, 9.8, 0)));
			}
		}
	}
}
