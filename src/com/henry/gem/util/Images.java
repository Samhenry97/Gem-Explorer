package com.henry.gem.util;

import static com.henry.gem.util.Util.loadImage;

import java.awt.image.BufferedImage;

public class Images {
	
	public static BufferedImage block = loadImage("block.png");
	public static BufferedImage block1 = loadImage("block1.png");
	public static BufferedImage block2 = loadImage("block2.png");
	
	public static BufferedImage playerLeft = loadImage("p_left.png");
	public static BufferedImage playerRight = loadImage("p_right.png");
	public static BufferedImage playerJumpLeft = loadImage("pj_left.png");
	public static BufferedImage playerJumpRight = loadImage("pj_right.png");
	
	public static BufferedImage exit = loadImage("exit.png");	
	
	public static BufferedImage skeleton = loadImage("skeleton.png");
	
	public static BufferedImage life = loadImage("life.png");
	public static BufferedImage lifeFaded = loadImage("life_faded.png");
	
	public static BufferedImage blueGemAnim = loadImage("b_gem.png");
	public static BufferedImage diamondAnim = loadImage("diamond_s.png");
	public static BufferedImage greenGemAnim = loadImage("g_gem.png");
	public static BufferedImage redGemAnim = loadImage("r_gem.png");
	public static BufferedImage clearGemAnim = loadImage("c_gem.png");
	
	public static BufferedImage chestOpen = loadImage("c_open.png");
	public static BufferedImage chestClosed = loadImage("c_closed.png");

}