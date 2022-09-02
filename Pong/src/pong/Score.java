package pong;
import java.awt.*;
public class Score extends Rectangle {
	static int game_width;
	static int game_height;
	int player1;
	int player2;
	Score(int game_height,int game_width){
		this.game_height = game_height;
		this.game_width = game_width;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.drawLine(game_height/2,10 , game_height/2, game_width);
		g.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 20));
		g.setColor(Color.magenta);
		g.drawString("score",(game_height/2)-25,10);
		g.setColor(Color.white);
		g.drawString(String.valueOf(player1), (game_height/2)-50, 50);
		g.drawString(String.valueOf(player2), (game_height/2)+30, 50);
	}
}
