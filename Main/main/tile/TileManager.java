package main.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.core.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int[][] mapTileNum;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
		TilesConstants tiles = new TilesConstants();
		
		setTile(0, tiles.WATER, true);
		setTile(1, tiles.SAND, false);
		setTile(2, tiles.GRASS, false);
		setTile(3, tiles.STONE_IN_WATER, true);
		setTile(4, tiles.BUSH, true);
	}
	
	public void setTile(int index, BufferedImage tile_texture, Boolean collissionBol) {
		tile[index] = new Tile();
		tile[index].image = tile_texture;
		tile[index].collission = collissionBol;
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String[] numbers = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
					
				}
				
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			
		} catch (Exception e) {
			
		}
	}
	
	public void renderTilesWhilePlayerMoves(int worldX, int worldY, int screenX, int screenY, int worldCol, int worldRow, Graphics2D g2) {
		int tileNum = mapTileNum[worldCol][worldRow];
		
		if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) 
		{
			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}

	public void draw(Graphics2D g2) {
		for(int row = 0; row < gp.maxWorldRow; row++) {
			for(int col = 0; col < gp.maxWorldCol; col++) {
				int worldX = col * gp.tileSize;
				int worldY = row * gp.tileSize;
				int tileNum = mapTileNum[col][row];

				g2.drawImage(tile[tileNum].image, worldX, worldY, gp.tileSize, gp.tileSize, null);
			}
		}
	}
	
}
