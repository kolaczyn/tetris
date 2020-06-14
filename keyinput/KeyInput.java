package first.tetris.keyinput;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import first.tetris.Handler;


public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		for(int i = 0; i < 4; i++)
			keyDown[i] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		if(!handler.gameOver) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			keyDown[0] = true;
			handler.tryRotate();
			break;
		case KeyEvent.VK_A:
			keyDown[1] = true;
			handler.blockLeft();
			break;
		case KeyEvent.VK_S:
			keyDown[2] = true;
			handler.blockDown();
			break;
		case KeyEvent.VK_D:
			keyDown[3] = true;
			handler.blockRight();
			break;
		default:
			break;
		}
		}
		else if(e.getKeyCode() == KeyEvent.VK_R)
			handler.resetGame();
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			keyDown[0] = false;
			break;
		case KeyEvent.VK_A:
			keyDown[1] = false;
			break;
		case KeyEvent.VK_S:
			keyDown[2] = false;
			break;
		case KeyEvent.VK_D:
			keyDown[3] = false;
			break;
		default:
			break;
		}	}
	
}
