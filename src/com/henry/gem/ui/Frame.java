package com.henry.gem.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.henry.gem.input.FrameListener;
import com.henry.gem.input.Keyboard;
import com.henry.gem.util.Constants;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private FrameListener fl = new FrameListener();
	private Keyboard kil = new Keyboard();
	
	public Frame() {
		setContentPane(new GamePanel());
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(Constants.MIN_WIDTH, Constants.MIN_HEIGHT));
		setTitle("Gem Seeker Version 1.000000");
		
		addWindowFocusListener(fl);
		addWindowListener(fl);
		addKeyListener(kil);
	}
	
}