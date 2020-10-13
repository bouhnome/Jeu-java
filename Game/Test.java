
import javax.swing.*;
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*; 
public class Test{
	
	public static void main(String [] args){
		
		JLabel game1 = new JLabel ("See the instructions by clicking on the game ");
		JLabel game2 = new JLabel ("Irvin Game description");
		JLabel game3 = new JLabel ("Irvin Game description");
		JLabel[] groupGame = {game1, game2, game3};
		
		
		JButton gamers1 = new JButton("Click to enter Game 1 - Baballe");
		JButton gamers2 = new JButton ("Click to enter Game 2- Irvin Game");
		JButton gamers3 = new JButton ("Click to enter Game 3- Irvin Game");
		JButton [] gameChooser = {gamers1, gamers2, gamers3};
		
		
		Platform choix = new Platform (gameChooser, groupGame);
	}
}
