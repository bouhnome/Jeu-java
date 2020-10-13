package Game1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.geom.*;

public class TerrainDeJeu extends JFrame {

	private JPanel conteneurPrincipal;
	private Matrice matrice; 
	
	private PanneauTerrain jeu; 
	
	private JButton Start;
	private JButton AjouterBarre;
	private JButton EffacerBarre;
	private Timer timer;
	private int niveau;
	public boolean timerActif;
	public boolean mousseActif;
	private EcouteurMouse click;

	public TerrainDeJeu(int n) {
		super("Terrain de jeu Niveau " + n);
		timerActif = false;
		mousseActif = false;
		niveau = n;

		Start = new JButton("Commencer");
		EffacerBarre = new JButton("Effacer Barre");

		// Construction des differents niveaux
		if (niveau == 0) {
			matrice = new Matrice(200, 100);
			matrice.ajouterGobelet(new Point(100, 400), 300, 50);
			AjouterBarre = new JButton("Ajouter barre (" + matrice.getBarresRestantes() + ")");
		}
		if (niveau == 1) {
			matrice = new Matrice(200, 100);
			matrice.ajouterGobelet(new Point(400, 400), 70, 50);
			AjouterBarre = new JButton("Ajouter barre (" + matrice.getBarresRestantes() + ")");
		}
		if (niveau == 2) {
			matrice = new Matrice(200, 100);
			matrice.ajouterLaser(new Point(200, 300), 100, 0.0);
			matrice.ajouterGobelet(new Point(400, 200), 50, 50);
			AjouterBarre = new JButton("Ajouter barre (" + matrice.getBarresRestantes() + ")");
		}
		if (niveau == 3) {
			matrice = new Matrice(100, 200);
			matrice.ajouterBarre(new Barre(-45, 5.0, 70, Color.MAGENTA, new Point(400, 500), false, false));
			matrice.ajouterGobelet(new Point(100, 400), 80, 80);
			AjouterBarre = new JButton("Ajouter barre (" + matrice.getBarresRestantes() + ")");
		}
		if (niveau == 4) {
			matrice = new Matrice(100, 200);
			matrice.ajouterLaser(new Point(400, 230), 100, 0.0);
			matrice.ajouterBarre(new Barre(-45, 5.0, 70, Color.MAGENTA, new Point(400, 500), false, false));
			matrice.ajouterGobelet(new Point(100, 400), 80, 80);
			AjouterBarre = new JButton("Ajouter barre (" + matrice.getBarresRestantes() + ")");
		}
		if (niveau == 5) {
			new JFrame("Victoire");
			this.dispose();
		}

		conteneurPrincipal = new JPanel(new BorderLayout());
		JPanel monPanelBarre = new JPanel(new GridLayout(3, 1, 10, 50));
		Baballe b = new Baballe(matrice.getPointInit(), 10);
		jeu = new PanneauTerrain(matrice, b, this);
		b.setPanneau(jeu);
		conteneurPrincipal.add(monPanelBarre, BorderLayout.EAST);
		conteneurPrincipal.add(jeu, BorderLayout.CENTER);

		monPanelBarre.add(AjouterBarre);
		monPanelBarre.add(EffacerBarre);
		monPanelBarre.add(Start);

		timer = new Timer(1, new EcouteurTimer(this));
		AjouterBarre.addActionListener(new EcouteurAjouterBarre(this));
		EffacerBarre.addActionListener(new EcouteurEffacerBarre(this));
		Start.addActionListener(new EcouteurCommencer(this));

		
		this.setResizable(false);
		this.setContentPane(conteneurPrincipal);
		this.setSize(700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null); // centrage de la fenetre
	}

	public static void main(String[] a) {

		int niveau = 3; 

		new TerrainDeJeu(niveau);
	}

	public PanneauTerrain getPanneauTerrain() {
		return jeu;
	}

	public int getNiveau() {
		return niveau;
	}

	public EcouteurMouse getEcouteurMouse() {
		return click;
	}

	public void startTimer() {
		if (!timerActif) {
			timer.start();
			timerActif = true;
		}
	}

	public void ajoutBarreWizard() { 
		if (!mousseActif) {
			click = new EcouteurMouse(this); 
			jeu.addMouseListener(click);
			mousseActif = true;
		}
	}

	public Matrice getMatrice() {
		return matrice;
	}

	public void refreshButton() { 
		AjouterBarre.setText("Ajouter barre (" + matrice.getBarresRestantes() + ")");
		AjouterBarre.repaint();
	}

}
