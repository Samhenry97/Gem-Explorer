package com.henry.gem.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.henry.gem.inner.Game;
import com.henry.gem.util.Constants;
import com.henry.gem.util.Time;
import com.henry.gem.util.Util;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	private Thread thread;
	private int fps = 0;
	
	private Game game;
	
	public GamePanel() {
		int width = Constants.WIDTH, height = Constants.HEIGHT;
		
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));
		
		game = new Game(this);
		
		start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.render(g);
		
		g.dispose();
	}

	public void run() {
		running = true;
		
		while(running) {
			
			int frames = 0;
			long frameCounter = 0;
			
			final double frameTime = 1.0 / Constants.FRAME_CAP;
			
			long lastTime = Time.getTime();
			double unprocessedTime = 0;		
			
			while(running) {
				boolean render = false;
				
				long startTime = Time.getTime();
				long passedTime = startTime - lastTime;
				lastTime = startTime;
				
				unprocessedTime += passedTime / (double) Time.SECOND;
				frameCounter += passedTime;
				
				while(unprocessedTime > frameTime) {
					render = true;
					
					unprocessedTime -= frameTime;
					game.update();
					
					if(frameCounter >= Time.SECOND) {
						fps = frames;
						if(fps == 0);
						frames = 0;
						frameCounter = 0;
					}
				}
				
				if(render) {
					repaint();
					frames++;
				} else {
					Util.sleep(1);
				}
			}
		}
	}
	
	public void start() {
		thread = new Thread(this, "Panel");
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}

}