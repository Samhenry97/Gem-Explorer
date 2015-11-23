package com.henry.gem.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.henry.gem.util.Constants;

public class Keyboard extends KeyAdapter {

	public static boolean isKeyDown(int keyCode) {
		if (keyCode < 0 || keyCode > keys.length - 1) return false;

		return keys[keyCode];
	}

	public static void loseFocus() {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
	}

	private static boolean[] keys = new boolean[Constants.TOTAL_KEY_COMBOS];

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length - 1) return;

		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length - 1) return;

		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}