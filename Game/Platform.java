import javax.swing.*;
import Game1.FenetrePrincipale;
import java.awt.*;
import java.awt.event.*;


public class Platform extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	JPanel boxSelection;
	JPanel gameContainer;
	JPanel[] gameSet;
	JLabel title;
	JButton[] gameSelection;
	JLabel[] gameDescription;

	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension dimScreen = tk.getScreenSize();
	double heightScreen = dimScreen.height;
	double widthScreen = dimScreen.width;

	
	public Platform(JButton[] template, JLabel[] gameExplain) {

		this.setBounds(0, 0, (int) widthScreen, (int) heightScreen);

		this.boxSelection = new JPanel();
		boxSelection.setBounds(0, 0, (int) widthScreen, (int) heightScreen);
		boxSelection.setLayout(null);
		boxSelection.setBackground(Color.green);

		this.title = new JLabel("SELECT YOUR GAME");
		title.setBounds((int) (boxSelection.getWidth() / 2) - 150, 10, 300, 20);

		this.gameContainer = new JPanel();
		gameContainer.setBounds(0, 40, boxSelection.getWidth(), boxSelection.getHeight() - 40);
		gameContainer.setLayout(null);
		gameContainer.setBackground(Color.blue);

		this.gameSet = new JPanel[template.length];
		this.gameSelection = new JButton[template.length];
		this.gameDescription = new JLabel[gameExplain.length];

		
		int size = (int) (gameContainer.getWidth() / gameSet.length);

		for (int i = 0; i < template.length; i++) {
			try {
				this.gameSet[i] = new JPanel();
				this.gameSet[i].setBounds(i * size, 0, size, (int) gameContainer.getHeight());
				this.gameSet[i].setLayout(null);
				this.gameSelection[i] = template[i];
				this.gameSelection[i].setBounds(0, 0, size, size);
				this.gameSelection[i].addActionListener(this);
				this.gameDescription[i] = gameExplain[i];
				this.gameDescription[i].setBounds(0, size, size,
						(int) (gameSet[i].getHeight() - (gameContainer.getWidth() / gameSet.length)));
				this.gameSet[i].add(gameSelection[i]);
				this.gameSet[i].add(gameDescription[i]);
				this.gameContainer.add(gameSet[i]);

			} catch (Exception e) {
				System.out.println("wrong dimension");
			}
		}

		this.add(boxSelection);
		boxSelection.add(title);
		boxSelection.add(gameContainer);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < gameSelection.length; i++) {
			if (e.getSource() == gameSelection[i])
				System.out.println("Game " + (i + 1) + " has been selected");
			if (i == 0) {
				FenetrePrincipale f = new FenetrePrincipale();
			} else {
				System.out.println("Irvin part");
			}

		}
	}

}
