package com.henry.gem.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import com.henry.gem.ui.GamePanel;
import com.henry.gem.util.Constants;

public class Mouse implements MouseListener, MouseMotionListener {
	
	public static int BUTTON1 = MouseEvent.BUTTON1; //Left click
	public static int BUTTON2 = MouseEvent.BUTTON2; //Scroll click
	public static int BUTTON3 = MouseEvent.BUTTON3; //Right click
	
	private static boolean[] buttons = new boolean[Constants.TOTAL_MOUSE_BUTTONS];
	
	public static boolean isButtonDown(int button) {
		if(button < 0 || button > buttons.length - 1) return false;
		
		return buttons[button];
	}
	
	//************************************************************************
	
	private GamePanel panel;
	
	public Mouse(GamePanel panel) {
		this.panel = panel;
	}
	
	public int getX() {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(mouse, panel);
		
		return mouse.x;
	}
	
	public int getY() {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(mouse, panel);
		
		return mouse.y;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() < 0 || e.getButton() > buttons.length - 1) return;
		
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() < 0 || e.getButton() > buttons.length - 1) return;
		
		buttons[e.getButton()] = true;
	}

}