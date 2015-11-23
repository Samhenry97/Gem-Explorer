package com.henry.gem.inner;

import static com.henry.gem.entities.Gem.GemType.BLUE;
import static com.henry.gem.entities.Gem.GemType.CLEAR;
import static com.henry.gem.entities.Gem.GemType.DIAMOND;
import static com.henry.gem.entities.Gem.GemType.GREEN;
import static com.henry.gem.entities.Gem.GemType.RED;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import com.henry.gem.entities.Block;
import com.henry.gem.entities.Chest;
import com.henry.gem.entities.Entity;
import com.henry.gem.entities.Exit;
import com.henry.gem.entities.Gem;
import com.henry.gem.entities.Life;
import com.henry.gem.entities.Player;
import com.henry.gem.entities.Skeleton;
import com.henry.gem.ui.LevelGUI;
import com.henry.gem.util.Constants;
import com.henry.gem.util.PointText;

public class Level {
	
	public static int SIZE = Constants.OBJ_SIZE;
	
	public LevelGUI gui;
	public Player player;
	public Exit exit;
	
	public int xOffs = 0, yOffs = 0;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<PointText> pointTexts = new ArrayList<PointText>();
	private Block[][] blocks;
	
	private Game game;
	
	private Point playerStart;
	
	private boolean complete = false;
	
	public Level(String[] map, Game game) {
		player = game.getPlayer();
		gui = new LevelGUI(player, this);
		
		int bigWidth = 0;
		for(int i = 0; i < map.length; i++) {
			if(map[i].length() > bigWidth) {
				bigWidth = map[i].length();
			}
		}
		blocks = new Block[bigWidth / 2 + 1][map.length];
		
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[y].length(); x++) {
				if(map[y].charAt(x) == 'b') {
					blocks[x / 2][y] = new Block((x / 2) * SIZE, y * SIZE, SIZE, SIZE, this);
				}
			}
		}
		
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[y].length(); x++) {
				switch(map[y].charAt(x)) {
					case 'p' : player.setX((x / 2) * SIZE); player.setY(y * SIZE); 
								playerStart = new Point((x / 2) * SIZE, y * SIZE); break;
					case 'x' : exit = new Exit((x / 2) * SIZE, y * SIZE, this); break;
					case 'e' : entities.add(new Skeleton((x / 2) * SIZE, y * SIZE, this)); break;
					case 'l' : entities.add(new Life((x / 2) * SIZE, y * SIZE, this)); break;
					case '0' : entities.add(new Gem((x / 2) * SIZE, y * SIZE, BLUE, this)); break;
					case '1' : entities.add(new Gem((x / 2) * SIZE, y * SIZE, RED, this)); break;
					case '2' : entities.add(new Gem((x / 2) * SIZE, y * SIZE, GREEN, this)); break;
					case '3' : entities.add(new Gem((x / 2) * SIZE, y * SIZE, CLEAR, this)); break;
					case '4' : entities.add(new Gem((x / 2) * SIZE, y * SIZE, DIAMOND, this)); break;
					case 'c' : entities.add(new Chest((x / 2) * SIZE, y * SIZE, this)); break;
					case 'b' : break;
					case 'a' : break;
					case ' ' : break;
					default : break;
				}
			}
		}
		
		this.game = game;
	}
	
	public void render(Graphics g) {
		xOffs = player.getX() - (game.width / 2) + player.getWidth();
		yOffs = player.getY() - (game.height / 2) + player.getHeight();
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			
			if(e.isOnScreen()) {
				e.render(g);
			}
		}
		
		for(int x = 0; x < blocks.length; x++) {
			for(int y = 0; y < blocks[x].length; y++) {
				if(blocks[x][y] != null && blocks[x][y].isOnScreen()) {
					blocks[x][y].render(g);
				}
			}
		}
		
		if(exit != null) exit.render(g);
		
		for(int i = 0; i < pointTexts.size(); i++) {
			pointTexts.get(i).render(g);
		}
		
		player.render(g);
		gui.render(g);
	}
	
	public void update() {		
		player.update();
		
		if(exit != null) exit.update();
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		
		for(int i = 0; i < pointTexts.size(); i++) {
			pointTexts.get(i).update();
		}
		
		if(complete) game.nextLevel();
	}
	
	public void removeEntity(Entity e) {
		if(entities.contains(e)) {
			entities.remove(e);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removePointText(PointText t) {
		pointTexts.remove(t);
	}
	
	public void addPointText(PointText t) {
		pointTexts.add(t);
	}
	
	public boolean checkCollision(int x, int y) {
		while(x % SIZE != 0) x--;
		while(y % SIZE != 0) y--;
		
		x /= SIZE;
		y /= SIZE;
		
		if(x < 0 || x > blocks.length - 1) return false;
		if(y < 0 || y > blocks[0].length - 1) return false;
		
		return blocks[x][y] != null;
	}
	
	public void movePlayerToStart() {
		if(playerStart != null) {
			player.setPos(playerStart);
		} else {
			player.setX(0);
			player.setY(0);
		}
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public int getScreenWidth() {
		return game.width;
	}
	
	public int getScreenHeight() {
		return game.height;
	}

}