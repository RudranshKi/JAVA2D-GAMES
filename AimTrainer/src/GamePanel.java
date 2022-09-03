import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements  ActionListener {

	static final int screenHeight = 400;
	static final int screenWidth = 800;
	static final int screen_size = screenHeight*screenWidth;
	int delay = 20;
	int unit_size = 25;
	int hitX;
	int hitY;
	int targetX=20;
	int targetY=20;
	int score = 0;
	int ScreenCount = 1;
	Random random;
	Timer timer;
	Boolean run = false;
	GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		random = new Random();
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new keyListener());
		this.addMouseListener(new mouseListener());
		newGame();
		
	}
	
	public void newGame() {
		target();
		//run = true;
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	public void target() {
		targetX = random.nextInt(screenWidth/unit_size)*unit_size;
		targetY = random.nextInt(screenHeight/unit_size)*unit_size;
	}
	
	public void isHit() {
		if (((hitX <= targetX+(unit_size)) &&  (hitX >= targetX-(unit_size)))) {
			if ( (hitY >= targetY- (unit_size)) && (hitY <= targetY+ (unit_size))) {
				target();
				score++;
				hitX = 0;
				hitY = 0;
			}
			
		}
		else {
			 if (hitX != 0 && hitY != 0)
				 run = false;
			     ScreenCount =2;
		}
		System.out.println(targetX+" ++  "+ targetY);
		System.out.println(hitX+" "+hitY);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draStartScreen (Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font ("SANS",Font.BOLD,30));
		g.drawString("Press ENTER to play",screenWidth/2-140,screenHeight/2);
	}
	public void draw(Graphics g) {
		if (run == true) {
			g.setColor(Color.red);
			g.fillOval(targetX,targetY,unit_size,unit_size);
		}
		else{
			if (ScreenCount == 1) { 
				g.setColor(Color.white);
				g.setFont(new Font ("SANS",Font.BOLD,30));
				g.drawString("Press ENTER to play",screenWidth/2-140,screenHeight/2);
		    }
			else 
			{
				g.setColor(Color.RED);
				g.drawString("GAME OVER -" ,screenWidth/2-40,screenHeight/2);
				g.drawString(Integer.toString(score) ,screenWidth/2+60,screenHeight/2);

			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (run) {
			isHit();
		}
		repaint();
		
	}
	
	public class mouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			hitX= e.getX();
			hitY= e.getY();
		}
	}
	
	public class keyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 10) {
				run = true;
				ScreenCount = 1;
			}
		}
	}

}
