import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	static final int game_width = 700;
	static final int game_height = 600;
	static final Dimension dimension = new Dimension(game_width,game_height);
	static final int unitSize = 25;
	static final int BALLX = 250;
	static final int BALLY = 300;
	static final int PADDLE_WIDTH = 100;
	static final int PADDLE_HEIGHT = 25;
	static final int PADDLEX = game_width - PADDLE_WIDTH;
	static final int PADDLEY = game_height - PADDLE_HEIGHT;
	int row = 4;
	int col = 12;
	int score = 0;
	int totalBricks = row*col; 
	Boolean running = false;
	
	Ball ball;
	Paddle paddle;
    Graphics graphics;
    Image image;
    Thread thread;
    Random random;
    MapBrick mapBrick;
    Brick brack;
	GamePanel(){
		this.setPreferredSize(dimension);
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		newGame();
		this.addKeyListener(new keylistener());
		thread = new Thread(this);
		thread.start();
	}
	
	public void newBall() {
		ball = new Ball(BALLX,BALLY,unitSize,unitSize);
	}
	
	public void newPaddle() {
		paddle = new Paddle(PADDLEX,PADDLEY,PADDLE_HEIGHT,PADDLE_WIDTH);
	}
	
	public void newMap() {
		mapBrick = new MapBrick(row,col);
	}
	
	public void newGame() {
		newBall();
		newPaddle();
		newMap();
	}
	
	public void move() {
		ball.move();
		paddle.move();
	}
	
	public void checkCollision() {
		//BALL COLIION
		if (ball.x >= game_width - unitSize) {
			ball.xVelocity = -ball.xVelocity;
		}
		if (ball.x <= 0) {
			ball.xVelocity = - ball.xVelocity;
		}
		if (ball.y <= 0) {
			ball.yVelocity = - ball.yVelocity;
		}
		
		//PADDLE COLISION
		if (paddle.x <=0) {
			paddle.x = 0;
		}
		if (paddle.x >= game_width - paddle.width) {
			paddle.x = game_width - paddle.width;
		}
		
		//BALL COLISION WITH PADDLE
		if (ball.intersects(paddle)) {
			ball.yVelocity = - (ball.yVelocity);
    		if (ball.xVelocity > 0) {
    			ball.xVelocity++;
    		}
    		
    		ball.xDirection(ball.xVelocity);
    		ball.yDirection(ball.yVelocity);
    	}
		
		//BALL COLISION WITH BRICKS
		for (int i = 0 ; i < row ; i++  ) {
			for ( int j = 0 ; j < col ; j++) {
				int brickX = j * mapBrick.brickWidth+80;
				int brickY = i * mapBrick.brickHeight+50;
				int brickWidth = mapBrick.brickWidth;
				int brickHeight = mapBrick.brickHeight;
				Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);			
				if(ball.intersects(brickRect))
				{					
					mapBrick.setBrickValue(0, i, j);
					score+=5;	
					totalBricks--;
					if(ball.x+ 19 <= brickRect.x || ball.x + 1 >= brickRect.x + brickRect.width)	
					{
						ball.xVelocity = - ball.xVelocity;
					}
					// when ball hits top or bottom of brick
					else
					{
						ball.yVelocity =  - ball.yVelocity;
					}
					ball.xDirection(ball.xVelocity);
		    		ball.yDirection(ball.yVelocity);
					
				}
				if (totalBricks < 0) running = false;
			}
		}
   
	}
			
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		if (running) {
			ball.draw(g);
			paddle.draw(g);
			mapBrick.draw((Graphics2D) g); 
		}
		
		else 
		{
			g.setColor(Color.white);
			g.setFont(new Font("SANS_SERIF",Font.ITALIC,30));
			g.drawString("PRESS ENTER TO PLAY", game_width/2-150, game_height/2);
		}
	}

	@Override
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
				
				    if (running) move();
//					System.out.println("#score- "+score);
					checkCollision();
					repaint();
					delta--;
			}
		}
		
	}
	
	public class keylistener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
			paddle.KeyPressed(e);
			if (e.getKeyCode() == 10) {
				running = true;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			paddle.KeyReleased(e);
		}
	}
}
