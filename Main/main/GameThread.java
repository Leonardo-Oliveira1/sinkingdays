package main;

public class GameThread implements Runnable{

	int FPS = 60;
	Thread gameThread;
	GamePanel gamePanel;

	public GameThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //0.01666 seconds each frame
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {

			gamePanel.update();
			gamePanel.repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
