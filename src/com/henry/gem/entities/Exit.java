package com.henry.gem.entities;

import java.awt.Graphics;

import com.henry.gem.inner.Level;
import com.henry.gem.util.Images;

public class Exit extends Entity {
	
	public Exit(int x, int y, Level level) {
		super(x, y, level);
	}

	public Exit(int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}

	@Override
	public void update() {
		if(testCollision(level.player)) {
			level.setComplete(true);
		}
		
		if(!level.checkCollision(x + width / 2, y + height - 1)) {
			y += 4;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.exit, x - level.xOffs, y - level.yOffs, width, height, null);
	}

}