package first.tetris.array.block;
import java.awt.Color;
import java.util.Random;

import first.tetris.array.Array;

public class Block {
	private BlockType type;
	private int posX, posY;
	public Array A;
	
	private Color color;
	
	Random r = new Random();

		
	public Block(){
		posX = 4;
		posY = 1;
		
		type = generateType();
		
		switch(type)
		{
		case I:
			A = new Array(4, 1);
			color = Color.CYAN;
			A.setTrue (0, 0); A.setTrue (1, 0); A.setTrue (2, 0); A.setTrue (3, 0); // 1111
		break;
		case O:
			A = new Array(2, 2);
			color = Color.YELLOW;
			A.setTrue (0, 0); A.setTrue (1, 0); // 11
			A.setTrue (0, 1); A.setTrue (1, 1); // 11
		break;
		case T:
			A = new Array(3, 2);
			color = Color.MAGENTA;
			A.setFalse(0, 0); A.setTrue(1, 0); A.setFalse(2, 0); // 010
			A.setTrue (0, 1); A.setTrue(1, 1); A.setTrue (2, 1); // 111
		break;
		case S:
			A = new Array(3, 2);
			color = Color.GREEN;
			A.setFalse(0, 0); A.setTrue (1, 0); A.setTrue (2, 0); // 011
			A.setTrue (0, 1); A.setTrue (1, 1); A.setFalse(2, 1); // 110
		break;
		case Z:
			A = new Array(3, 2);
			color = Color.RED;
			A.setTrue (0, 0); A.setTrue (1, 0); A.setFalse(2, 0); // 110
			A.setFalse(0, 1); A.setTrue (1, 1); A.setTrue (2, 1); // 011		
		break;
		case J:
			A = new Array(3, 2);
			color = Color.BLUE;
			A.setTrue (0, 0); A.setFalse(1, 0); A.setFalse(2, 0); // 100
			A.setTrue (0, 1); A.setTrue (1, 1); A.setTrue (2, 1); // 111
		break;
		case L:
			A = new Array(3, 2);
			color = Color.ORANGE;
			A.setFalse(0, 0); A.setFalse(1, 0); A.setTrue (2, 0); // 001
			A.setTrue (0, 1); A.setTrue (1, 1); A.setTrue (2, 1); // 111
	
		break;
		default:
			System.out.println("Invalid block type!");
		break;
		}
	}
	
	BlockType generateType() {
		int a = r.nextInt(7);
		
		switch (a) {
		case 0:
			return BlockType.I;
		case 1:
			return BlockType.O;
		case 2:
			return BlockType.T;
		case 3:
			return BlockType.S;
		case 4:
			return BlockType.Z;
		case 5:
			return BlockType.J;
		case 6:
			return BlockType.L;
		}
		return BlockType.L;
	}
	
	
	
	public int uppest() {
		return posY;
	}
	
	public int downest() {
		return posY + A.getHeight();
	}
	
	public int leftest() {
		return posX;
	}
	
	public int rightest() {
		return posX + A.getWidth();
	}
	
	
	
	public void increasePosX() {
		posX++;
	}
	
	public void increasePosY() {
		posY++;
	}
	
	public void decreasePosX() {
		posX--;
	}
	
	public void decreasePosY() {
		posY--;
	}
	
	
	
	public int getSizeX() {
		return A.getWidth();
	}
	
	public int getSizeY() {
		return A.getHeight();
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public Color getColor() {
		return color;
	}
	
	public BlockType getType() {
		return type;
	}
}

