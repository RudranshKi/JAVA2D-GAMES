import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GamePanel gp = new GamePanel();
	GameFrame(){
		this.setTitle("Snek Man strikes again");
//		this.setLocationRelativeTo(null);
		this.add(gp);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.pack();
	}
}