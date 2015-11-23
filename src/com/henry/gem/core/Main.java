package com.henry.gem.core;

import java.awt.EventQueue;

import com.henry.gem.ui.Frame;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Frame frame = new Frame();
				frame.setVisible(true);
			}
		});
	}

}