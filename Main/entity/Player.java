package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;

	public int worldX;
	public int worldY;

	public int screenX;
	public int screenY;



	private boolean isWalking = false;
	BufferedImage spritesheet;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			spritesheet = ImageIO.read(getClass().getResourceAsStream("/Player/player_spritesheet.png"));
			
			up = selectSprite(spritesheet, 4, 4, 2, 4);
			down = selectSprite(spritesheet, 4, 4, 2, 1);
			left = selectSprite(spritesheet, 4, 4, 1, 3);
			right = selectSprite(spritesheet, 4, 4, 2, 2);
			
			up_gif = selectSprite(spritesheet, 4, 4, 2, 4);
			down_gif = selectSprite(spritesheet, 4, 4, 2, 1);
			left_gif = selectSprite(spritesheet, 4, 4, 1, 3);
			right_gif = selectSprite(spritesheet, 4, 4, 2, 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
	    if(keyH.wPressed || keyH.aPressed || keyH.sPressed || keyH.dPressed) {
			if (keyH.wPressed && keyH.aPressed) {
				direction = "left";
				x -= speed;
				y -= speed;

			}
			else if (keyH.wPressed && keyH.dPressed) {
				direction = "right";
				x += speed;
				y -= speed;
			}
			else if (keyH.sPressed && keyH.aPressed) {
				direction = "left";
				x -= speed;
				y += speed;
			}
			else if (keyH.sPressed && keyH.dPressed) {
				direction = "right";
				x += speed;
				y += speed;
			}
			else if(keyH.wPressed == true) {
				direction = "up";
				y -= speed;
			}
			else if (keyH.sPressed == true) {
				direction = "down";
				y += speed;
			}
			else if (keyH.aPressed == true) {
				direction = "left";
				x -= speed;
			}
			else if (keyH.dPressed == true) {
				direction = "right";
				x += speed;
			}

			spriteCounter++;
			if (spriteCounter > spriteSpeed) {
			    spriteNum = (spriteNum % 5) + 1;
			    spriteCounter = 0;
			}
	    } else {
	    	spriteNum = 1;
	    }
	}

	public BufferedImage animateSpriteSheet(BufferedImage defaultImg, int col) {
    	if(spriteNum == 1) {
    		return defaultImg;
    	}
    	
    	if(spriteNum == 2) {
    		return selectSprite(spritesheet, 4, 4, 1, col);
    	}
    	
    	if(spriteNum == 3) {
    		return selectSprite(spritesheet, 4, 4, 2, col);
    	}
    	
    	if(spriteNum == 4) {
    		return selectSprite(spritesheet, 4, 4, 3, col);
    	}
    	
    	if(spriteNum == 5) {
    		return selectSprite(spritesheet, 4, 4, 4, col);
    	}
    	
    	return null;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
        switch(direction) {
            case "up":
            	image = animateSpriteSheet(up, 4);
                break;
            case "down":
            	image = animateSpriteSheet(down, 1);
                break;
            case "left":
            	image = animateSpriteSheet(left, 3);
                break;
            case "right":
            	image = animateSpriteSheet(right, 2);
                break;
        }
	    
		g2.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
		
	}
	
}
