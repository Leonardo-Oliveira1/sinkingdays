package main;


import main.core.GamePanel;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("joguinho");

		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		GamePanel gamePanel = new GamePanel();
		add(gamePanel);

		pack();

		setLocationRelativeTo(null);
		setVisible(true);
    }
	
}
