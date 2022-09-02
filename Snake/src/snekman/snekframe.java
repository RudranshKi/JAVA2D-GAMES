package snekman;

import java.awt.Color;

import javax.swing.JFrame;

public class snekframe extends JFrame {
	snekpanel sp = new snekpanel();
	snekframe(){
		this.setTitle("Snek Man strikes again");
//		this.setLocationRelativeTo(null);
		this.add(sp);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.pack();
	}
}
