package main.core;

import main.entities.EntityManager;

public class GameThread implements Runnable{

	private final int UPS = 60;
	private final double UPDATE_INTERVAL = 1_000_000_000.0 / UPS;

    Thread gameThread;
	GamePanel gamePanel;
	EntityManager entityManager;

	public GameThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.entityManager = new EntityManager();

		entityManager.add(gamePanel.player);
	}

	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		long previousTime = System.nanoTime();
		double deltaUpdate = 0;

		while (gameThread != null) {

			long currentTime = System.nanoTime();

            deltaUpdate += (currentTime - previousTime) / UPDATE_INTERVAL;
			previousTime = currentTime;

			while (deltaUpdate >= 1) {
				entityManager.update();
				deltaUpdate--;
			}

			gamePanel.repaint();
		}
	}
}
