package main.tile;

import main.core.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int[][] baseLayer;    // camada do terreno
	public int[][] objectLayer;  // árvores, pedras, etc.

	final int originalTileSize = 16;
	final int scale = 4;

	public final int tileSize = originalTileSize * scale;

	public TileManager(GamePanel gp) {
		this.gp = gp;

		// inicializa tiles
		tile = new Tile[10];
		getTileImage();

		// inicializa layers de acordo com tamanho do mapa
		baseLayer = new int[gp.maxWorldCol][gp.maxWorldRow];
		objectLayer = new int[gp.maxWorldCol][gp.maxWorldRow];

		loadLayer("/maps/terrain.txt", baseLayer);
		loadLayer("/maps/objects.txt", objectLayer);
	}

	private void getTileImage() {
		TilesConstants tiles = new TilesConstants();

		setTile(1, tiles.WATER, true);
		setTile(2, tiles.SAND, false);
		setTile(3, tiles.GRASS, false);
		setTile(4, tiles.STONE_IN_WATER, true);
		setTile(5, tiles.BUSH, true);
		setTile(6, tiles.STOP, true);
	}

	private void setTile(int index, BufferedImage tile_texture, boolean collision) {
		tile[index] = new Tile();
		tile[index].image = tile_texture;
		tile[index].collission = collision;
	}

	private void loadLayer(String path, int[][] layer) {
		try (InputStream is = getClass().getResourceAsStream(path);
			 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			for (int row = 0; row < gp.maxWorldRow; row++) {
				String line = br.readLine();
				String[] numbers = line.split(" ");
				for (int col = 0; col < gp.maxWorldCol; col++) {
					layer[col][row] = Integer.parseInt(numbers[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		drawLayer(g2, baseLayer);
		drawLayer(g2, objectLayer);
	}

	private void drawLayer(Graphics2D g2, int[][] layer) {
		for (int row = 0; row < gp.maxWorldRow; row++) {
			for (int col = 0; col < gp.maxWorldCol; col++) {
				int tileNum = layer[col][row];

				if (layer == objectLayer && tileNum == 0) {
					continue;
				}

				if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null && tile[tileNum].image != null) {
					BufferedImage img = tile[tileNum].image;

					int width = tileSize;
					int height = img.getHeight() * width / img.getWidth();

					int x = col * tileSize;
					int y = row * tileSize;

					g2.drawImage(img, x, y, width, height, null);
				}
			}
		}
	}
}
