package com.henry.gem.entities;

import java.awt.Graphics;

import com.henry.gem.inner.Level;
import com.henry.gem.util.Images;

public class Skeleton extends Entity {
	
	private int dir = 1;
	private int speed = 2;
	private int fallSpeed = 4;
	
	private boolean falling = false;
	private boolean jumping = false;
	private boolean movingFromShield = false;
	private int jumpDistance = 0;
	private int jumpHeight = 32;
	
	private int animFrame = 0;
	private int animMax = 7;
	private int animTime = 0;
	private int animTimeBeforeChange = 2;
	
	public Skeleton(int x, int y, Level level) {
		super(x, y, level);
	}
	
	public Skeleton(int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}

	@Override
	public void update() {
		
		if(!level.player.isShielded() && testCollision(level.player)) {
			level.player.removeLife();
			level.movePlayerToStart();
		} else if(level.player.isShielded() && testCollision(level.player)) {
			if(!movingFromShield) {
				dir = -dir;
				movingFromShield = true;
			}
		}
		
		if(movingFromShield && !testCollision(level.player)) {
			movingFromShield = false;
		}
		
		if(!level.checkCollision(x, y + height) && !level.checkCollision(x + width, y + height)) {
			falling = true;
		} else {
			falling = false;
		}
		
		if(falling) {
			y += fallSpeed;
		} else if(jumping) {
			if(jumpDistance < jumpHeight) {
				jumpDistance += fallSpeed;
				y -= speed;
			} else {
				jumpDistance = 0;
				jumping = false;
				falling = true;
			}
		}
		
		if(!falling) {
			if(dir == 1) {
				boolean move = true;
				
				if(level.checkCollision(x, y + height / 2)) {
					move = false;
					if(!level.checkCollision(x, y - height / 2)) jumping = true;
				} else if(!level.checkCollision(x, y + height)) {
					if(!level.checkCollision(x, y + height * 2)) {
						move = false;
					}
				}
				
				if(move) {
					x -= speed;
				} else if(!jumping) {
					dir = -dir;
				}
			} else if(dir == -1) {
				boolean move = true;
				
				if(level.checkCollision(x + width, y + height / 2)) {
					move = false;
					if(!level.checkCollision(x + width, y - height / 2)) jumping = true;
				} else if(!level.checkCollision(x + width, y + height)) {
					if(!level.checkCollision(x + width, y + height * 2)) {
						move = false;
					}
				}
				
				if(move) {
					x += speed;
				} else if(!jumping) {
					dir = -dir;
				}
			}
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
		
		g.drawImage(Images.skeleton, 
				x - level.xOffs, y - level.yOffs, x + width - level.xOffs, y + height - level.yOffs, 
				animFrame * width, 0, animFrame * width + width, height, null);
	}

}