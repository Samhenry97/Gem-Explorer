package com.henry.gem.entities;

import java.awt.Graphics;
import java.util.Random;

import com.henry.gem.inner.Level;
import com.henry.gem.util.Images;

public class Block extends Entity {
	
	private static Random rand = new Random();
	
	private int type;
	
	public Block(int x, int y, Level level) {
		this(x, y, SIZE, SIZE, level);
	}
	
	public Block(int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
		
		int typeDecider = rand.nextInt(100);
		
		if(typeDecider > 90) {
			type = 3;
		} else if(typeDecider > 70) {
			type = 2;
		} else {
			type = 1;
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		switch(type) {
			case 1 : g.drawImage(Images.block, x - level.xOffs, y - level.yOffs, width, height, null); break;
			case 2 : g.drawImage(Images.block1, x - level.xOffs, y - level.yOffs, width, height, null); break;
			case 3 : g.drawImage(Images.block2, x - level.xOffs, y - level.yOffs, width, height, null); break;
			default : System.out.println("Somehow there's an unknown type in the Block class..");
		}
	}
	
}