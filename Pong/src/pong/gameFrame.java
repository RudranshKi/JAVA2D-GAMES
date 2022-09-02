package pong;

import java.awt.Color;

import javax.swing.*;

public class gameFrame extends JFrame {
	gamePanel gp = new gamePanel();
	gameFrame(){
		this.setTitle("PONG");
//		this.setLocationRelativeTo(null);
		this.add(gp);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.pack();
	}
}
