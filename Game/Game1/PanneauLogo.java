package Game1;

import java.awt.*;
import javax.swing.JPanel;
 
public class PanneauLogo extends JPanel {
  public void paintComponent(Graphics g){
    
    g.setColor(Color.RED);
    g.fillOval((getWidth()/2)-20, getHeight()/4, 40, 40);	
    
    g.setColor(Color.GREEN);
    g.fill3DRect(getWidth()*3/4 ,0,80,300,true);
    
    g.setColor(Color.BLACK);
    for (int i = 0; i<7;i++){
        g.drawLine(getWidth()/3, getHeight()/2+i+50, (getWidth()/3)*2 , getHeight()/2+i+100);
    }
  }               
}
