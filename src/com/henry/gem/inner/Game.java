package com.henry.gem.inner;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.henry.gem.entities.Player;
import com.henry.gem.input.Mouse;
import com.henry.gem.ui.GamePanel;
import com.henry.gem.util.Constants;

public class Game {
	
	private GamePanel panel;
	
	public int width;
	public int height;
	
	private int curLevel = 0;
	private Level[] levels = new Level[Map.maps.length];
	private Player player;
	
	public Mouse mouse;
	
	public Game(GamePanel panel) {
		this.panel = panel;
		mouse = new Mouse(panel);
		panel.addMouseListener(mouse);
		panel.addMouseMotionListener(mouse);
		
		width = panel.getWidth();
		height = panel.getHeight();
		
		player = new Player(null);
	}
	
	public void update() {
		width = panel.getWidth();
		height = panel.getHeight();
		
		if(levels[curLevel] == null) {
			levels[curLevel] = new Level(Map.maps[curLevel], this);
			player.setLevel(levels[curLevel]);
		}
		
		levels[curLevel].update();
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setPaint(new GradientPaint(0, 0, Constants.SUN, width / 2, height / 2, Constants.BACKGROUND));
		g2.fillRect(0, 0, width, height);
		
		if(levels[curLevel] == null) {
			levels[curLevel] = new Level(Map.maps[curLevel], this);
			player.setLevel(levels[curLevel]);
		}
		
		levels[curLevel].render(g);
	}
	
	public void nextLevel() {
		levels[curLevel] = null;
		curLevel++;
		if(curLevel > levels.length - 1) curLevel = 0; 
		player.setLevel(levels[curLevel]);
	}
	
	public Player getPlayer() {
		return player;
	}

}