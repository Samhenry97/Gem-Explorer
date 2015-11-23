package com.henry.gem.items;

import java.awt.Graphics;

import com.henry.gem.entities.Entity;
import com.henry.gem.inner.Level;
import com.henry.gem.util.GameObject;

public class Item extends GameObject {

	protected Entity entity;
	
	public Item(Entity entity, Level level) {
		this.entity = entity;
		this.x = entity.getX() + entity.getWidth() - 15;
		this.y = entity.getY() + entity.getHeight() - 10;
		this.width = 30;
		this.height = 30;
		this.level = level;
	}
	
	public void render(Graphics g) {
		g.fillRect(x, y, width, height);
	}

	
	public void update() {
		
	}
	
}