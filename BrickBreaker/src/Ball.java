import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
	int xVelocity;
	int yVelocity;
	Random random;
	int speed = 2;
	Ball(int x, int y, int width , int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDir = random.nextInt(3);
		int randomYDir = random.nextInt(3);
		if (randomYDir == 0) {
			randomYDir -- ;
		}
		yDirection(randomYDir*speed);
		if (randomYDir == 0) {
			randomYDir -- ;
		}
		xDirection(randomXDir*speed);
	}
	
	public void xDirection(int randomXDir) {
		xVelocity = randomXDir;
	}
	
	public void yDirection(int randomYDir) {
		yVelocity = randomYDir;
	}
	
	public void move() {
		x = x + xVelocity;
		y = y + yVelocity;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x,y,width,height);
	}
}
