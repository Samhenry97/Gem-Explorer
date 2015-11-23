package com.henry.gem.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.henry.gem.inner.Level;

public class Bullet extends Entity {
	
	private int dir;
	private Entity entity;
	private int speed = 6;
	
	public Bullet(Entity entity, int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
		this.entity = entity;
		this.dir = ((Player) entity).getDir();
	}

	@Override
	public void update() {
		x += dir * speed;
		
		if(level.checkCollision(x, y)) {
			level.removeEntity(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 0));
		g.fillRect(x - level.xOffs, y - level.yOffs, width, height);
	}
	
	
	
}