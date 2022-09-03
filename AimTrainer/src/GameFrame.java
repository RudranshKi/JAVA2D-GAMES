import java.awt.Color;

import javax.swing.*;

public class GameFrame extends JFrame{
	GamePanel gp = new GamePanel();
	ImageIcon icon = new ImageIcon("crosshair.png");
	GameFrame(){
		this.setTitle("AIM TRAINER");
		this.setIconImage(icon.getImage());
		this.add(gp);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.pack();
	}
}
