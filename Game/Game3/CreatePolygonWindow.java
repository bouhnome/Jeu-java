
import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.LinkedList; 
 public class CreatePolygonWindow extends JFrame implements ActionListener{

 	private JButton click;
	private JLabel monLabel;
 	public PolygonClickListener clickManager;

 	
 	public CreatePolygonWindow(LinkedList<Body> mesSolides,ForceRegistry registrySolide){

		this.setTitle("Polygon Creation");
		this.clickManager = new PolygonClickListener(mesSolides,registrySolide);

 		this.setLayout(null);
 		this.setSize(1400/2,800/2);
 		this.setLocation(700,200);
 		this.setResizable(false);
 		this.getContentPane().addMouseListener(clickManager);
 		this.setVisible(true);

 		monLabel = new JLabel("Click on the screen for the different points then on \"add Solid\"");
 		monLabel.setBounds(30,30,500,30);
 		add(monLabel);

 		click = new JButton("Add solid");
 		click.setBounds(530,340,130,30);
 		click.addActionListener(this);
 		add(click);
 		
 		
 	}

 	public void actionPerformed(ActionEvent e){
 		if(e.getSource()==click){
 			clickManager.createPolygon();
 		}
 	}
 }
