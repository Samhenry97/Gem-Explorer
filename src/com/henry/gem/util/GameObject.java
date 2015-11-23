package com.henry.gem.util;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.henry.gem.inner.Level;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Level level;

	public abstract void render(Graphics g);

	public abstract void update();
	
	public boolean testCollision(GameObject go) {
		Rectangle obj1 = new Rectangle(x, y, width, height);
		Rectangle obj2 = new Rectangle(go.x, go.y, go.width, go.height);
		
		return obj1.intersects(obj2);
	}
	
	public boolean isOnScreen() {
		return x > level.xOffs - 50 && x < level.xOffs + level.getScreenWidth() + 50 && 
				y > level.yOffs - 50 && y < level.yOffs + level.getScreenHeight() + 50;
	}
	
	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}
	
	public boolean contains(Point p) {
		return new Rectangle(x, y, width, height).contains(p);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}