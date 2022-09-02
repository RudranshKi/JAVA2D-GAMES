import java.awt.*;

public class MapBrick {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public int x;
	public int y;
	public int row;
	public int col;
	public Brick brick;
	
	MapBrick(int row , int col){	
		this.row=row;
		this.col=col;
		map = new int[row][col];
		for (int i = 0 ; i < row ; i++  ) {
			for ( int j = 0 ; j < col ; j++) {
				map[i][j]=1;
			}
		}
		
		brickWidth =  540/col;
		brickHeight = 150/row;
		
		
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0 ; i < row ; i++  ) {
			for ( int j = 0 ; j < col ; j++) {
				if (map[i][j] > 0) {
					brick = new Brick(i,j,brickWidth,brickHeight);
					brick.draw(g);
				}
			}
		}
	} 
	
	public void setBrickValue(int value, int row, int col)
	{
		map[row][col] = value;
	}    
}

