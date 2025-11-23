package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel{

	final int originalTileSize = 16;
	final int scale = 4; 
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLDS SETTINGS
	public final int maxWorldCol = 32; //esse valor sempre deve ser relativo ao tamanho do mapa
	public final int maxWorldRow = 32; //esse valor sempre deve ser relativo ao tamanho do mapa
	GameThread gameThread;

	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //comentar isso quando for fazer o fullscreen
		this.setBackground(Color.BLACK); 
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

		gameThread = new GameThread(this);
		gameThread.start();

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		
		
		g2.dispose();
		
	}
	
}
