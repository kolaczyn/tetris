package first.tetris;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import first.tetris.display.Window;
import first.tetris.keyinput.KeyInput;


public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1902147405213257243L;
	
	public static final int WIDTH  = 365;
	public static final int HEIGHT = 410;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	public Game() {
		handler = new Handler(this);
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Tetris", this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
	
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
				e.printStackTrace();
			}
		}

	public void run() {
		this.requestFocus();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int tick = 0;
		
		 while(running) {
			 now = System.nanoTime();
			 delta += (now - lastTime) / timePerTick;
			 timer +=now - lastTime;
			 lastTime = now;
			 
			 if(delta >= 1) {
			 tick();
			 render();
			 tick++;
			 delta--;
			 }
			 
			 if(timer >= 1000000000) {
				// System.out.println("Ticks and Frames: " + tick);
				 tick = 0;
				 timer = 0;
			 }
		 } 
		 stop();
	
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
