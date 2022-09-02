import java.awt.*;

public class Brick extends Rectangle {
	Brick(int X, int Y, int width , int height){
		super(X,Y,width,height);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(y * width + 80, x * height + 50, width, height);
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(3));
		g.drawRect(y * width + 80, x * height + 50, width, height);		
	}
}
