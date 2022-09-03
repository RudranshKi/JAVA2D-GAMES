package snekman;

import java.awt.Color;
import javax.swing.*;

public class snekframe extends JFrame {
	snekpanel sp = new snekpanel();
	ImageIcon icon = new ImageIcon("snake.png");
	snekframe(){
		this.setTitle("Snek Man strikes again");
		this.setIconImage(icon.getImage());
		this.add(sp);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.pack();
	}
}
