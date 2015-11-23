package com.henry.gem.entities;

import java.awt.Graphics;

import com.henry.gem.inner.Level;
import com.henry.gem.util.Constants;
import com.henry.gem.util.GameObject;

public abstract class Entity extends GameObject {
	
	public static int SIZE = Constants.OBJ_SIZE;
	
	public Entity(Level level) {
		this(0, 0, level);
	}
	
	public Entity(int x, int y, Level level) {
		this(x, y, SIZE, SIZE, level);
	}
	
	public Entity(int x, int y, int width, int height, Level level) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.level = level;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
}