package com.henry.gem.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.henry.gem.entities.Entity;
import com.henry.gem.inner.Level;

public class PointText extends Entity {
	
	public static int SIZE = 50;
	
	private int fontSize = SIZE;
	private String text;
	private Color color;
	
	public PointText(String text, int x, int y, Color color, Level level) {
		super(x, y, SIZE, SIZE, level);
		this.text = text;
		this.color = color;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));
		
		g.drawString(text, x - level.xOffs, y - level.yOffs);
	}

	@Override
	public void update() {
		y--;
		fontSize--;
		
		if(fontSize < 2) {
			removeText();
		}
	}
	
	public void removeText() {
		level.removePointText(this);
	}
	
}