package first.tetris.array;

public class Array {
	private int WIDTH;
	private int HEIGHT;
	
	private boolean[][] elements;
	
	public Array(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		elements = new boolean[WIDTH][HEIGHT];
				
	}
	
	public void rotateClockwise() { // rotates the array by 90 degrees clockwise
		Array tempArray = new Array(HEIGHT, WIDTH);

		for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
				tempArray.setValue(HEIGHT - j - 1, i, getValue(i, j));
		
		this.copy(tempArray);
	}
	
	public void rotateCounter() { // rotates the array by 90 degrees counter clockwise
		Array tempArray = new Array(HEIGHT, WIDTH);
		
		for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
				tempArray.setValue(j, WIDTH - i - 1, getValue(i, j));
		
		this.copy(tempArray);
	}

	private void copy(Array A) {
		this.WIDTH = A.WIDTH;
		this.HEIGHT = A.HEIGHT;
		
		elements = new boolean[WIDTH][HEIGHT];
		
		for(int i = 0; i < WIDTH; i++)
			for(int j = 0; j < HEIGHT; j++)
				setValue(i, j, A.getValue(i, j));
	}
	
	public boolean checkRow(int r) { // true <=> value of each cell in the row is true
		for(int i = 0; i < WIDTH; i++)
			if(!elements[i][r]) return false;
		return true;
	}
	
	private void print() { // prints the array to the console
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(elements[j][i]) System.out.print("1");
				else System.out.print("0");			
				}
		System.out.println("");
		}
		System.out.println("");
	}
	
	public void deleteRow(int r) { // deletes r-th row and every row above it gets moved one unit down
		for(int i = r; i > 0; i--)
			copyRow(i, i - 1);
		
		for(int i = 0; i < WIDTH; i++)
			setFalse(i, 0);
	}
	
	private void copyRow(int r1, int r2) { //copies the content of the r2-th row to the r1-th row
		if(r1 == r2) return;
		for(int i = 0; i < WIDTH; i++)
			elements[i][r1] = elements[i][r2];
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public boolean getValue(int i, int j) {
		return (elements[i][j]);
	}
	
	public void setValue(int i, int j, boolean value) {
		elements[i][j] = value;
	}
	
	public void setTrue(int i, int j) {
		elements[i][j] = true;
	}
	
	public void setFalse(int i, int j) {
		elements[i][j] = false;
	}

}