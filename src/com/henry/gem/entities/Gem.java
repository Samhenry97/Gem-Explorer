package com.henry.gem.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.henry.gem.inner.Level;
import com.henry.gem.util.Images;
import com.henry.gem.util.PointText;

public class Gem extends Entity {
	
	public static enum GemType {
		BLUE(50, Color.BLUE), 
		RED(100, Color.RED), 
		GREEN(200, Color.GREEN), 
		CLEAR(500, Color.GRAY), 
		DIAMOND(1000, Color.GRAY);
		
		private int points = 0;
		private Color color;
		
		private GemType(int points, Color color) {
			this.points = points;
			this.color = color;
		}
		
		public int getPoints() {
			return points;
		}
		
		public Color getColor() {
			return color;
		}
	};
	
	//****************************************************************************
	
	private GemType type;
	
	private int animFrame = 0;
	private int animMax = 31;
	private int animTime = 0;
	private int animTimeBeforeChange = 2;
	
	public Gem(int x, int y, Level level) {
		this(x, y, SIZE, SIZE, level);
	}
	
	public Gem(int x, int y, GemType type, Level level) {
		this(x, y, SIZE, SIZE, type, level);
	}
	
	public Gem(int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
		
		randomizeAnimation();
	}
	
	public Gem(int x, int y, int width, int height, GemType type, Level level) {
		super(x, y, width, height, level);
		this.type = type;
		
		randomizeAnimation();
	}
	
	public void randomizeAnimation() {
		Random rand = new Random();
		
		animTimeBeforeChange = rand.nextInt(4) + 1;
		animFrame = rand.nextInt(animMax);
	}

	@Override
	public void update() {
		if(testCollision(level.player)) {
			level.player.addToScore(type.getPoints());
			level.player.collectGem();
			
			level.addPointText(new PointText(Integer.toString(type.getPoints()), x, y, type.getColor(), level));
			
			level.removeEntity(this);
		}
	}

	@Override
	public void render(Graphics g) {
		if(animTime < animTimeBeforeChange) {
			animTime++;
		} else {
			animTime = 0;
			animFrame++;
			if(animFrame > animMax) animFrame = 0;
		}
		
		switch(type) {
			case RED : drawImage(Images.redGemAnim, g); break;
			case BLUE : drawImage(Images.blueGemAnim, g); break;
			case GREEN : drawImage(Images.greenGemAnim, g); break;
			case DIAMOND : drawImage(Images.diamondAnim, g); break;
			case CLEAR : drawImage(Images.clearGemAnim, g); break;
			default : System.err.println("THIS IS IMPOSSIBLE>>IN THE GEM ENUM");
		}
	}
	
	public void drawImage(BufferedImage img, Graphics g) {
		g.drawImage(img,
				x - level.xOffs, y - level.yOffs, x + width - level.xOffs, y + height - level.yOffs, 
				animFrame * width, 0, animFrame * width + width, height, null);
	}

}