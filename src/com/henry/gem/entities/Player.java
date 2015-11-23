package com.henry.gem.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.henry.gem.inner.Level;
import com.henry.gem.input.Keyboard;
import com.henry.gem.items.Gun;
import com.henry.gem.items.Item;
import com.henry.gem.util.Constants;
import com.henry.gem.util.Images;

public class Player extends Entity {
	
	private int speed = 4;
	private int dir = 1;
	private int lives = Constants.DEFAULT_LIVES;
	private int gemsCollected = 0;
	private int score = 0;
	
	private int animFrame = 0;
	private int animMax = 7;
	private int animTime = 0;
	private int animTimeBeforeChange = 2;
	
	private int jumpHeight = 100;
	private int jumpDistance = 0;
	private int jumpSpeed = 4;
	private boolean falling = false;
	private boolean jumping = false;
	
	private boolean shielded = false;
	private double shieldTime = 0;
	private double shieldMaxTime = Constants.SHIELD_TIME;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Player(Level level) {
		super(level);
	}
	
	public Player(int x, int y, Level level) {
		super(x, y, level);
	}
	
	public Player(int x, int y, int width, int height, Level level) {
		super(x, y, width, height, level);
	}
	
	public boolean addLife() {
		if(lives < Constants.MAX_LIVES) {
			lives++;
			return true;
		} else {
			return false;
		}
	}
	
	public void removeLife() {
		lives--;
		items.add(new Gun(this, level));
	}
	
	public void addToScore(int amt) {
		score += amt;
	}
	
	public void collectGem() {
		gemsCollected++;
	}

	@Override
	public void update() {
		boolean right = Keyboard.isKeyDown(KeyEvent.VK_RIGHT) || Keyboard.isKeyDown(KeyEvent.VK_D);
		boolean left = Keyboard.isKeyDown(KeyEvent.VK_LEFT) || Keyboard.isKeyDown(KeyEvent.VK_A);
		boolean jump = Keyboard.isKeyDown(KeyEvent.VK_UP) || Keyboard.isKeyDown(KeyEvent.VK_W);
		boolean shield = Keyboard.isKeyDown(KeyEvent.VK_DOWN) || Keyboard.isKeyDown(KeyEvent.VK_S);
		
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			for(int i = 0; i < items.size(); i++) {
				if(items.get(0) instanceof Gun) {
					((Gun) items.get(0)).shoot();
				}
			}
		}
		
		if(shield && shieldTime <= shieldMaxTime) {
			shielded = true;
			shieldTime++;
			
			right = left = jump = false;
		} else {
			shielded = false;
		}
		
		if(!right && !left) {
			animTime = 0;
			animFrame = 0;
		}
		
		if(!(right && left)) {
			if(right) {
				dir = 1;
				if(!level.checkCollision(x + width + 1, y + height - 1) && !level.checkCollision(x + width + 1, y + 1)) {
					x += speed;
					if(animTime <= animTimeBeforeChange) {
						animTime++;
					} else {
						animFrame++;
						animTime = 0;
					}
					if(animFrame > animMax) animFrame = 0;
				} else {
					animFrame = 0;
					animTime = 0;
				}
			} else if(left) {
				dir = -1;
				if(!level.checkCollision(x - 1, y + height - 1) && !level.checkCollision(x - 1, y + 1)) {
					x -= speed;
					if(animTime <= animTimeBeforeChange) {
						animTime++;
					} else {
						animFrame++;
						animTime = 0;
					}
					if(animFrame > animMax) animFrame = 0;
				} else {
					animFrame = 0;
					animTime = 0;
				}
			}
		}
		
		if(jump) {
			if(!falling) {
				jumping = true;
			}
		} else {
			if(jumping) {
				jumpDistance = 0;
				jumping = false;
				falling = true;
			}
		}
		
		if(jumping) {
			if(!level.checkCollision(x + 4, y - 1) && !level.checkCollision(x + width - 4, y - 1)) {
				if(jumpDistance <= jumpHeight) {
					jumpDistance += jumpSpeed;
					y -= jumpSpeed;
				} else {
					jumpDistance = 0;
					jumping = false;
					falling = true;
				}
			} else {
				jumping = false;
				falling = true;
				jumpDistance = 0;
			}
		} else if(falling) {
			if(!level.checkCollision(x + 4, y + height) && !level.checkCollision(x + width - 4, y + height)) {
				y += jumpSpeed;
			} else {
				falling = false;
			}
		} else {
			if(!level.checkCollision(x + 4, y + height) && !level.checkCollision(x + width - 4, y + height)) {
				falling = true;
			}
		}
		
		for(int i = 0; i < items.size(); i++) {
			items.get(i).update();
		}
		
		System.out.println(x);
	}

	@Override
	public void render(Graphics g) {
		if(shielded) {
			g.drawImage(Images.block, x - level.xOffs, y - level.yOffs, null);
		} else if(dir == 1) {
			if(jumping) {
				g.drawImage(Images.playerJumpRight, x - level.xOffs, y - level.yOffs, null);
			} else {
				g.drawImage(Images.playerRight, 
						x - level.xOffs, y - level.yOffs, x - level.xOffs + width, y - level.yOffs + height,
						animFrame * width, 0, animFrame * width + width, height, null);
			}
		} else if(dir == -1) {
			if(jumping) {
				g.drawImage(Images.playerJumpLeft, x - level.xOffs, y - level.yOffs, null);
			} else {
				g.drawImage(Images.playerLeft, 
						x - level.xOffs, y - level.yOffs, x - level.xOffs + width, y - level.yOffs + height,
						animFrame * width, 0, animFrame * width + width, height, null);
			}
		} else {
			System.out.println("Player direction should either be 1 or -1, not " + dir);
		}
		
		for(int i = 0; i < items.size(); i++) {
			items.get(i).render(g);
		}
	}
	
	public boolean isShielded() {
		return shielded;
	}
	
	public void setLevel(Level level) {
		this.level = level;
		
		shieldTime = 0;
	}
	
	public void setPos(Point point) {
		x = point.x;
		y = point.y;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getGemsCollected() {
		return gemsCollected;
	}
	
	public double getShieldTime() {
		return shieldTime;
	}
	
	public double getShieldMaxTime() {
		return shieldMaxTime;
	}
	
	public int getDir() {
		return dir;
	}

}