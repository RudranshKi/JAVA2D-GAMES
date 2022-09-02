package snekman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class snekpanel extends JPanel implements ActionListener{
	final int game_width = 800;
	final int game_height = 600;
	final int blockSize = 20;
	final int game_size_unit = ((game_width * game_height)/blockSize);
	int meatX;
	int meatY;
	Random random;
	int initialBody = 6;
	char dir = 'R';
	int delay = 175;
	boolean run= false;
	Timer timer;
	int x[] =new int[blockSize];
	int y[] =new int[blockSize];
	int Score=0;
	
	snekpanel(){
		this.setPreferredSize(new Dimension(game_width,game_height));
		random = new Random();
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startNew();
	}
	
	public void startNew() {
		newMeat();
		run = true;
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void newMeat() {
		meatX= random.nextInt((int)game_width/blockSize)*blockSize;
		meatY= random.nextInt((int)game_height/blockSize)*blockSize;
	}
	
	public void checkMeat() {
       if ((x[0] == meatX) && (y[0] == meatY)) {
    	   initialBody++;
    	   Score++;
		   newMeat();
		}
	}
	
	public void checkCollision() {
		for (int i = initialBody ; i > 0 ; i--) {
			if ((x[0] == x[i]) && y[0] == y[i]) {
				run = false;
			}
		}

		if (x[0] < 0 ) {
			run = false;
		}
		if ( x[0] > game_width) {
			run = false;
		}
		if (y[0] > game_height) {
			run = false;
		}
		if (y[0] < 0) {
			run = false;
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if (run) {
			g.setColor(Color.red);
			g.fillOval(meatX,meatY,blockSize,blockSize);
			
			for (int i = 0 ; i<initialBody ; i++) {
				if (i == 0) {
					g.setColor(Color.magenta);
					g.fillRect(x[0], y[0],blockSize, blockSize);
				}
				else {
					g.setColor(Color.GREEN);
					g.fillRect(x[i], y[i],blockSize, blockSize);
				}
			}
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("SANS_SERIF", Font.ITALIC,30));
			g.drawString("SCORE-",game_width/2-100,40);
			g.drawString(Integer.toString(Score),game_width/2 + 20,40);
			//timer.stop();
		}
		else {
			g.setColor(Color.RED);
			g.setFont(new Font("SANS_SERIF", Font.ITALIC,50));
			g.drawString("THE END - "+Integer.toString(Score),game_width/2-140,game_height/2);
		}
	}
	
	public void move(){
		for(int i = initialBody;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(dir) {
		case 'U':
			y[0] = y[0] - blockSize;
			break;
		case 'D':
			y[0] = y[0] + blockSize;
			break;
		case 'L':
			x[0] = x[0] - blockSize;
			break;
		case 'R':
			x[0] = x[0] + blockSize;
			break;
		}  
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (run) {
			move();
			checkMeat();
			checkCollision();
		}
		repaint();
	}
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(dir != 'R') {
					dir = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(dir != 'L') {
					dir = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(dir != 'D') {
					dir = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(dir != 'U') {
					dir = 'D';
				}
				break;
			}
		}
	
	
}
}
