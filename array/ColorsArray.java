package first.tetris.array;
import java.awt.Color;

public class ColorsArray {
	private int WIDTH;
	private int HEIGHT;
	
	private Color[][] colors;
	
	public ColorsArray(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		colors = new Color[WIDTH][HEIGHT];
	}
	
	public void deleteRow(int r) { // deletes r-th row and every row above it gets moved one unit down
		for(int i = r; i > 0; i--)
			copyRow(i, i - 1);
		
		for(int i = 0; i < WIDTH; i++)
			colors[i][0] = null;
	}
	
	private void copyRow(int r1, int r2) { //copies the content of the r2-th row to the r1-th row
		if(r1 == r2) return;
		for(int i = 0; i < WIDTH; i++)
			colors[i][r1] = colors[i][r2];
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public Color getColor(int i, int j) {
		return (colors[i][j]);
	}
	
	public void setColor(int i, int j, Color color) {
		colors[i][j] = color;
	}
}