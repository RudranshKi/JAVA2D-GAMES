import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{
	int xVelocity;
	int speed = 10;
	Paddle(int X, int Y , int height , int width){
		super(X,Y,width,height);
	}
	
	public void KeyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setXdirection(-speed);
				move();
			}
			    
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setXdirection(speed);
				move();
			}
	}
	
	public void KeyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setXdirection(0);
				move();
			}
			    
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setXdirection(0);
				move();
			}
	}
	
	public void setXdirection(int xDirection) {
		xVelocity = xDirection;
	}
	
	public void move() {
		x = x + xVelocity;
	}
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x,y,width, height);

	}
}
