
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class AddForcesWindow extends JFrame implements ActionListener {

	private JButton click;
	public PolygonClickListener clickManager;
	public LinkedList<Body> mesSolides;
	public int choix;

	public AddForcesWindow(LinkedList<Body> mesSolides, ForceRegistry registrySolide, int choix) {
		this.choix = choix;
		this.mesSolides = mesSolides;
		this.setTitle("Adding forces");
		this.clickManager = new PolygonClickListener(mesSolides, registrySolide);

		this.setLayout(null);
		this.setSize(1400, 800);
		this.setLocation(700, 200);
		this.setResizable(false);
		this.getContentPane().addMouseListener(clickManager);
		this.setVisible(true);

		click = new JButton("Add Force");
		click.setBounds(1200, 600, 130, 30);
		click.addActionListener(this);
		add(click);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == click) {
			clickManager.addForce();
			repaint();
		}
	}

	public void paint(Graphics g) {

		g.setColor(mesSolides.get(choix).myColor);
		mesSolides.get(choix).dessine(g);

	}
}
