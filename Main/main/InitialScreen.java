package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InitialScreen extends JFrame{
	public InitialScreen() {
		setTitle("joguinho");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLocationRelativeTo(null);
		setVisible(true);

		this.startGame();
//		this.buttonPlay();
//		this.buttonExit();
	}
	
	public void buttonExit() {
		JButton bt = new JButton("Sair");
		
		int width = 250;
		int height = 70;
		int x = (getWidth() - width) / 2;
		int y = ((getHeight() - height) / 2) + 40;
		
		bt.setBounds(x, y, width, height);
		bt.setFont(new Font("Arial", Font.BOLD, 40));
		add(bt);
		
		bt.addActionListener(this::quit);
	}
	
	public void buttonPlay() {
		JButton bt = new JButton("Jogar");
		
		int width = 250;
		int height = 70;
		int x = (getWidth() - width) / 2;
		int y = ((getHeight() - height) / 2) - 40;
		
		bt.setBounds(x, y, width, height);
		bt.setFont(new Font("Arial", Font.BOLD, 40));
		add(bt);
		
		bt.addActionListener(this::play);
	}
	

	public void quit(ActionEvent e) {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public void play(ActionEvent e) {
		MainFrame hs = new MainFrame();
		this.dispose();
	}

	public void startGame() {
		new MainFrame();
		dispose();
	}
}
