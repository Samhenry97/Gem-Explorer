package com.henry.gem.items;

import java.awt.Graphics;

import com.henry.gem.entities.Bullet;
import com.henry.gem.entities.Entity;
import com.henry.gem.inner.Level;

public class Gun extends Item {
	
	private int reloadTime = 10;
	private int curReloadTime = 0;

	public Gun(Entity entity, Level level) {
		super(entity, level);
	}
	
	public void update() {
		x = entity.getX() + entity.getWidth() - 15;
		y = entity.getY() + entity.getHeight() - 10;
		
		if(curReloadTime < reloadTime) {
			curReloadTime++;
		}
	}
	
	public void render(Graphics g) {
		g.fillRect(x - level.xOffs, y - level.yOffs, width, height);
	}
	
	public void shoot() {
		if(curReloadTime >= reloadTime) {
			level.addEntity(new Bullet(entity, x, y, 20, 10, level));
			curReloadTime = 0;
		}
	}
	
}