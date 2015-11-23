package com.henry.gem.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.henry.gem.entities.Player;
import com.henry.gem.inner.Level;
import com.henry.gem.input.Keyboard;
import com.henry.gem.util.Constants;
import com.henry.gem.util.Images;

public class LevelGUI {
	
	private Player player;
	private Level level;
	private int padding = 15;
	
	private Font shieldFont = new Font("Arial", Font.PLAIN, 12);
	private Font scoreFont = new Font("Arial", Font.PLAIN, 20);
	
	private boolean showGUI = true;
	private boolean changed = false;
	
	public LevelGUI(Player player, Level level) {
		this.player = player;
		this.level = level;
	}
	
	public void render(Graphics g) {
		if(Keyboard.isKeyDown(KeyEvent.VK_E)) {
			if(!changed) {
				showGUI = !showGUI;
				changed = true;
			}
		} else {
			changed = false;
		}
		
		if(showGUI) {
			g.setColor(new Color(20, 20, 20, 200));
			g.fillRect(0, 0, 20 + Constants.MAX_LIVES * player.getWidth(), 120);
			g.setColor(Color.WHITE);
			g.drawRect(0, 0, 20 + Constants.MAX_LIVES * player.getWidth(), 120);
			
			for(int i = 0; i < player.getLives(); i++) 
				g.drawImage(Images.life, padding / 2 + i * player.getWidth(), padding, null);
			for(int i = player.getLives(); i < Constants.MAX_LIVES; i++)
				g.drawImage(Images.lifeFaded, padding + i * player.getWidth(), padding, null);
			
			int shieldBarWidth = 250;
			int shieldX = 15;
			
			g.setFont(shieldFont);
			g.setColor(Color.GRAY);
			g.fillRect(shieldX, 50, shieldBarWidth + 4, 24);
			
			g.setColor(Color.GREEN);
			g.fillRect(shieldX + 2, 52, (int) ((((player.getShieldMaxTime() - player.getShieldTime()) / player.getShieldMaxTime()) * 100) * shieldBarWidth / 100), 20);
			
			g.setColor(new Color(50, 50, 50, 200));
			g.drawString("S     H     I     E     L     D", shieldX + 60, 67);
			
			g.setFont(scoreFont);
			g.setColor(Color.BLACK);
			g.drawString("Score: " + player.getScore(), padding + 1, 101);
			g.setColor(new Color(50, 150, 50));
			g.drawString("Score: " + player.getScore(), padding, 100);
		}
	}

}