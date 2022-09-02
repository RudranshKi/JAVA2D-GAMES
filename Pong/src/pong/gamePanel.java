package pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class gamePanel extends JPanel implements Runnable{
	final int game_length=  1000;
    final int game_width= (int)(game_length/1.8);
    static final int ball_diameter = 20;
	static final int paddle_width = 25;
	static final int paddle_height = 100;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    Graphics graphics;
    Image image;
    Thread thread;
    Random random;
    
	gamePanel(){
		this.setPreferredSize(new Dimension(game_length,game_width));
		newBall();
		newPaddle();
		score = new Score(game_length,game_width);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void newBall() {
		random = new Random();
		ball = new Ball((game_length/2) - (ball_diameter/2), random.nextInt(game_width) , ball_diameter, ball_diameter);
	}
	
	public void newPaddle() {
		paddle1 = new Paddle(0, (game_width /2)-paddle_height , paddle_height , paddle_width ,1);
		paddle2 = new Paddle(game_length-paddle_width, (game_width /2)-paddle_height , paddle_height , paddle_width ,2);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
    public void checkCollison() {
    	// BALL WALL COLLISION
    	
    	if (ball.getY() >= game_width - ball_diameter) {
    		ball.yVelocity = -ball.yVelocity;
    	}   
    	else if (ball.getY() <= 0) {
    		ball.yVelocity = -ball.yVelocity;
    	}
    	
    	// BALL PADDLE COLLISION
    	
    	if (ball.intersects(paddle1)) {
    		ball.xVelocity = - (ball.xVelocity);
    		ball.xVelocity= ball.xVelocity + 2;
    		if (ball.yVelocity > 0) {
    			ball.yVelocity= ball.yVelocity + 2;
    		}
    		else {
    			ball.yVelocity--;
    		}
    		ball.xDirection(ball.xVelocity);
    		ball.yDirection(ball.yVelocity);
    	}
    	
    	if (ball.intersects(paddle2)) {
    		ball.xVelocity = - (ball.xVelocity);
    		ball.xVelocity= ball.xVelocity + 2;
    		if (ball.yVelocity > 0) {
    			ball.yVelocity= ball.yVelocity + 2;
    		}
    		else {
    			ball.yVelocity--;
    		}
    		ball.xDirection(-ball.xVelocity);
    		ball.yDirection(ball.yVelocity);
    	}
    	
    	//PLAYER SCORE (BALL MISS ON PADDLES)
    	
    	if (ball.getX() <= 0) {
    		score.player2++;
    		newBall();
    	}
    	
    	if (ball.getX() >= game_length) {
    		score.player1++;
    		newBall();
    	}
    	
    	// PADDLE COLLISION
    	
    	if (paddle1.getY() <= 0 ) {
    		paddle1.y = 0 ;
    	}   
    	
    	else if (paddle1.getY() >= game_width - paddle_height) {
    		paddle1.y = game_width - paddle_height;
    	}
    	
    	if (paddle2.getY() <= 0 ) {
    		paddle2.y = 0 ;
    	}   
    	
    	else if (paddle2.getY() >= game_width - paddle_height) {
    		paddle2.y = game_width - paddle_height;
    	}
    }

    public void run() {
    	long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
//				System.out.println(ball.getX()+ " "+ball.getY() );
				checkCollison();
				repaint();
				delta--;
			}
		}
    }
    
    public class AL extends KeyAdapter{
    	public void keyPressed(KeyEvent e) {
    		paddle1.KeyPressed(e);
    		paddle2.KeyPressed(e);
    	}
    	
    	public void keyReleased(KeyEvent e) {
    		paddle1.KeyReleased(e);
    		paddle2.KeyReleased(e);
    	}
    }
}
