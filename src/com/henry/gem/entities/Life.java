package com.henry.gem.entities;

import java.awt.Graphics;

import com.henry.gem.inner.Level;
import com.henry.gem.util.Images;

public class Life extends Entity {
	
	public Life(int x, int y, Level level) {
		super(x, y, level);
	}
	
	public Life(int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}

	@Override
	public void update() {
		if(testCollision(level.player)) {
			if(level.player.addLife()) {
				level.removeEntity(this);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.life, x - level.xOffs, y - level.yOffs, null);
	}

}