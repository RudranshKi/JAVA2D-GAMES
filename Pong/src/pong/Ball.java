package pong;

import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
	int xVelocity;
	int yVelocity;
	Random random;
	int speed = 2;
	Ball(int x, int y , int height , int width){
		super(x,y,width,height);
		random = new Random();
		int randomXDir = random.nextInt(3);
		int randomYDir = random.nextInt(3);
		if (randomXDir == 0) {
			randomXDir -- ;
		}
		xDirection(randomXDir*speed);
		if (randomXDir == 0) {
			randomXDir -- ;
		}
		yDirection(randomYDir*speed);
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
		g.setColor(Color.green);
		g.fillOval(x, y, width,height);
	}
}
