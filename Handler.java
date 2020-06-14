package first.tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import first.tetris.array.Array;
import first.tetris.array.ColorsArray;
import first.tetris.array.block.Block;
import first.tetris.array.block.BlockType;

public class Handler {
	int PIXEL = 20;
	int score = 0;
	int tickCounter = 0;
	public boolean gameOver;
	
	private Game game;
	
	Array A;
	ColorsArray CA;
	
	Color backgroundColor = Color.black;
	Color gridColor = Color.white;
	
	Block block = new Block();
	Block nextBlock = new Block();
	
	
	
	public Handler(Game game){
		A = new Array(10, 18);
		CA = new ColorsArray(10, 18);
		gameOver = false;
		
		
	}
	
	public void tick() {
		if(gameOver) return;
		tickCounter++;
		if(tickCounter == 25)
		{
			tickCounter = 0;
			blockDown();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, game.WIDTH, game.HEIGHT);
		
		drawGrid(g, 0, 0, A.getWidth(), A.getHeight());
		if(!gameOver)
		drawBlock(g);
		g.setColor(Color.WHITE);
		drawNextBlock(g);
		drawArray(g);
		
		g.setColor(Color.WHITE);
		g.drawString("Next Block:", 225, 18);
		g.drawString("Score: " + score, 225, 120);
		
		if(gameOver) {
			g.drawString("Game Over.", 225, 150);
			g.drawString("Press 'R' to start", 225, 175);
			g.drawString("a new game.", 225, 190);
		}

	}
	
	private void drawArray(Graphics g) {
		g.setColor(Color.red);
		
		for(int i = 0; i < A.getWidth(); i++)
			for(int j = 0; j < A.getHeight(); j++)
				if(A.getValue(i, j)) {
					g.setColor(CA.getColor(i, j));
					drawPixel(g, i, j);
				}
	}
	
	private void drawBlock(Graphics g) {
		g.setColor(block.getColor());
		
		for(int i = 0; i < block.getSizeX(); i++)
			for(int j = 0; j < block.getSizeY(); j++)
				if(block.A.getValue(i, j)) 
					drawPixel(g, i + block.getPosX(), j + block.getPosY());
	}
	
	private void drawPixel(Graphics g, int i, int j) {
		int offsetX = 5;
		int offsetY = 5;
		
		int x = offsetX + i * PIXEL;
		int y = offsetY + j * PIXEL;
		
		g.fillRect(x + 1, y + 1, PIXEL - 1, PIXEL - 1);
	}
	
	private void drawGrid(Graphics g, int gridX, int gridY, int gridWidth, int gridHeight) {
		g.setColor(gridColor);
		int offsetX = 5;
		int offsetY = 5;
		
		int x = offsetX + gridX * PIXEL;
		int y = offsetY + gridY * PIXEL;
		
		for(int i = 0; i < gridWidth; i++)
			for(int j = 0; j < gridHeight; j++)
				g.drawRect(x + i * PIXEL,y + j * PIXEL, PIXEL, PIXEL);
	}
	
	private void drawNextBlock(Graphics g) {
		g.setColor(Color.white);
		
		int gridX = A.getWidth() + 1;
		int gridY = 1;
				
		int gridWidth = 6;
		int gridHeight = 4;
		
		drawGrid(g, gridX, gridY, gridWidth, gridHeight);
		
		g.setColor(nextBlock.getColor());
		
		for(int i = 0; i < nextBlock.getSizeX(); i++)
			for(int j = 0; j < nextBlock.getSizeY(); j++)
				if(nextBlock.A.getValue(i, j))
					drawPixel(g, i + gridX + 1, j + gridY + 1);
	}
	
	public void blockDown() {
		
		if (block.getPosY() + block.getSizeY() == A.getHeight()) { //we hit the floor
			putBlock();
			return;
		}
		
		//next we're checking if for each column, there is something under the lowest point of the block
		for(int i = 0; i < block.getSizeX(); i++) {
			int lowest = 0;
			for(int j = 1; j < block.getSizeY(); j++) {
				if(block.A.getValue(i, j))
					lowest = j;
			}
			if(A.getValue(i + block.getPosX(), lowest + block.getPosY() + 1 )) {//if there's something under, we put the block down
				putBlock();
				return;
		}
		}
		block.increasePosY();;
	}
	
	public void blockLeft() {
		if(block.getPosX() == 0)
			return;
		
		for(int i = 0; i < block.getSizeY(); i++) {
			int leftest = block.getSizeX() - 1;
			for(int j = block.getSizeX() - 2; j >= 0; j--) {
				if(block.A.getValue(j, i))
					leftest = j;
			}
			if(A.getValue(leftest + block.getPosX() - 1, i + block.getPosY()))
				return;
		}
		block.decreasePosX();
	}
	
	public void blockRight() {
		if(block.getPosX() + block.getSizeX() == A.getWidth())
			return;

		for(int i = 0; i < block.getSizeY(); i++) {
			int rightest = 0;
			for(int j = 1; j < block.getSizeX(); j++) {
				if(block.A.getValue(j, i))
					rightest = j;
			}
			if(A.getValue(rightest + block.getPosX() + 1, i + block.getPosY() ))
				return;
		}
		block.increasePosX();
	}
	
	public void blockUp() {
		if(block.getPosY() == 0)
			return;
		
		for(int i = 0; i < block.getSizeX(); i++) {
			int uppest = block.getSizeY() - 1;
			for(int j = block.getSizeY() - 2; j >= 0; j--) {
				if(block.A.getValue(i, j))
					uppest = j;
			}
			if(A.getValue(i + block.getPosX(), uppest + block.getPosY() - 1))
				return;
		}
		block.decreasePosY();
	}
	
	public void tryRotate() {		
		block.A.rotateClockwise();
		
		boolean ok = true;
		
		if(block.leftest() < 0 || block.rightest() > A.getWidth() || block.uppest() < 0 || block.downest() > A.getHeight() ) {
			block.A.rotateCounter();
			return;
		}
		for(int i = 0; i < block.getSizeX(); i++)
			for(int j = 0; j < block.getSizeY(); j++)
				if(A.getValue(i + block.getPosX(), j + block.getPosY()))
					ok = false;
		
		if(!ok) block.A.rotateCounter();
	}
	
	private void putBlock() {
		for(int i = 0; i < block.getSizeX(); i++) //we're putting the block to the arrays A & CA
			for(int j = 0; j < block.getSizeY(); j++)
				if(block.A.getValue(i, j)) {
					A.setValue(i + block.getPosX(), j + block.getPosY(), block.A.getValue(i, j));
					CA.setColor(i + block.getPosX(), j + block.getPosY(), block.getColor());
				}
		checkForTetris(); //we're checking if we have to remove some blocks
		block = nextBlock;
		nextBlock = new Block();
		checkForGameOver(); //we're checking if the next block is in a Game Over position
	}
	
	private void checkForTetris() {
		int count = 0;
		
		int[] finished = new int[4];
		
		for(int i = 0; i < 4; i++)
			finished[i] = -1;
		
		for(int i = 0; i < A.getHeight(); i++) // we're checking each row in which the pieces of the block were put
			if(A.checkRow(i)) {
				finished[count] = i;
				count++;
			}

		for(int i = 0; i < count; i++) {
			int row = finished[count - i - 1] + i;
			A.deleteRow(row);
			CA.deleteRow(row);
		}
		
		increaseScore(count);
	}
	
	private void checkForGameOver() {
		int x = block.getPosX();
		int y = block.getPosY();
		for(int i = 0; i < block.getSizeX(); i++)
			for(int j = 0; j < block.getSizeY(); j++) {
					if(block.A.getValue(i, j))
						if(A.getValue(x + i, y + j)){
							gameOver = true;
							return;
						}
			}
	}

	private void increaseScore(int count) {
		switch(count) {
		case 0:
			score += 0;
			break;
		case 1:
			score += 120;
			break;
		case 2:
			score += 300;
			break;
		case 3:
			score += 900;
			break;
		case 4:
			score += 3600;
			break;
		default:
			score = -1;
			System.out.print("handler -> increaseScore: out of range");
			break;
		}
	}
	
	public void resetGame() {
		A = new Array(10, 18);
		CA = new ColorsArray(10, 18);
		
		score = 0;

		Block block = new Block();
		Block nextBlock = new Block();
		
		gameOver = false;
	}
}
