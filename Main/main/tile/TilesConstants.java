package main.tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TilesConstants {
	
	public BufferedImage WATER;
	public BufferedImage SAND;
	public BufferedImage GRASS;
	public BufferedImage STONE_IN_WATER;
	public BufferedImage BUSH;
	
	public TilesConstants() {
		try {
			WATER = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			SAND = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			GRASS = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			STONE_IN_WATER = ImageIO.read(getClass().getResourceAsStream("/tiles/water_stone.png"));
			BUSH = ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
