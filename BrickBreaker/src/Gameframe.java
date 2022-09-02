import java.awt.Color;

import javax.swing.*;
public class Gameframe extends JFrame {
	GamePanel gp = new GamePanel();
	Gameframe(){
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
