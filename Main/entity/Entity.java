package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int speed;

	public int worldX;
	public int worldY;

	public Rectangle solidArea;

	public boolean collisionOn;


	public BufferedImage up, down, left, right, up_gif, down_gif, left_gif, right_gif;
	public String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int spriteSpeed = 8;
	
	
	public BufferedImage selectSprite(BufferedImage spriteSheet, int cols, int rows, int desired_col, int desired_row) {
		
		int sprite_height = spriteSheet.getHeight();
		int sprite_width = spriteSheet.getWidth();
		
		int individual_sprite_height = sprite_height / rows;
		int individual_sprite_width = sprite_width / cols;
		
		
		int y = (desired_row - 1) * individual_sprite_height;
		int x = (desired_col - 1) * individual_sprite_width;
		
		BufferedImage sprite = spriteSheet.getSubimage(x, y, individual_sprite_width, individual_sprite_height);
		
		return sprite;
	}

	public void update() {
	}
}
