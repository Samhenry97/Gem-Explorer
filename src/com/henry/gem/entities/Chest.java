package com.henry.gem.entities;

import java.awt.Graphics;
import java.util.Random;

import com.henry.gem.inner.Level;
import com.henry.gem.util.Images;

public class Chest extends Entity {
	
	public static Random rand = new Random();
	
	private boolean open = false;
	private boolean contentsAdded = false;
	
	private int framesBeforeObjectAppears = 10;
	private int frame = 0;
	
	public Chest(int x, int y, Level level) {
		super(x, y, level);
	}
	
	public Chest(int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}

	@Override
	public void update() {
		if(!open && testCollision(level.player)) {
			open = true;
		} else if(open) {
			if(frame < framesBeforeObjectAppears) {
				frame++;
			} else if(!contentsAdded) {
				addContents();
				contentsAdded = true;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(open) {
			g.drawImage(Images.chestOpen, x - level.xOffs, y - level.yOffs, null);
		} else {
			g.drawImage(Images.chestClosed, x - level.xOffs, y - level.yOffs, null);
		}
	}
	
	public void addContents() {
		Gem toAdd = null;
		
		int gemTypeDecider = rand.nextInt(100);
		
		if(gemTypeDecider > 96) {
			toAdd = new Gem(x, y - SIZE, width, height, Gem.GemType.DIAMOND, level);
		} else if(gemTypeDecider > 90) {
			toAdd = new Gem(x, y - SIZE, width, height, Gem.GemType.CLEAR, level);
		} else if(gemTypeDecider > 70) {
			toAdd = new Gem(x, y - SIZE, width, height, Gem.GemType.GREEN, level);
		} else if(gemTypeDecider > 40) {
			toAdd = new Gem(x, y - SIZE, width, height, Gem.GemType.BLUE, level);
		} else {
			toAdd = new Gem(x, y - SIZE, width, height, Gem.GemType.RED, level);
		}
		
		level.addEntity(toAdd);
	}

}
