package pong;
import java.awt.*;
import java.awt.event.*;
public class Paddle extends Rectangle{
	int number;
	int yVelocity;
	int speed = 10;
	Paddle(int X, int Y , int height , int width , int number){
		super(X,Y,width,height);
		this.number = number;
	}
	
	public void KeyPressed(KeyEvent e) {
		switch (number) {
		case 1 :
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYdirection(-speed);
				move();
			}
			    
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYdirection(speed);
				move();
			}
		break;
		case 2 :
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYdirection(-speed);
				move();
			}
			    
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYdirection(speed);
				move();
			}
		break;
		}
	}
	
	public void KeyReleased(KeyEvent e) {
		switch (number) {
		case 1 :
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYdirection(0);
				move();
			}
			    
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYdirection(0);
				move();
			}
		break;
		case 2 :
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYdirection(0);
				move();
			}
			    
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYdirection(0);
				move();
			}
		break;
		}
	}
	
	public void setYdirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	public void move() {
		y = y + yVelocity;
	}
	public void draw(Graphics g) {
		if (number == 1) {
			g.setColor(Color.yellow);
		}
		else {
			g.setColor(Color.red);
			}
		g.fillRect(x,y,width, height);

	}
	
}
