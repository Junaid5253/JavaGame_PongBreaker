import java.awt.Color;
import javax.swing.*;

public class MainMenu{
	public static void main(String[] args){
		JFrame menuFrame = new JFrame("Main Menu");
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);

		JButton singlePlayerButton = new JButton("Single Player");
		JButton multiPlayerButton = new JButton("Multiplayer");

		singlePlayerButton.setBounds(100,100,200,50);
		multiPlayerButton.setBounds(100,180,200,50);

		panel.add(singlePlayerButton);
		panel.add(multiPlayerButton);

		menuFrame.setSize(400, 400);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setResizable(false);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setContentPane(panel);
		// read
		
		menuFrame.setVisible(true);
		

		singlePlayerButton.addActionListener(e ->{
			menuFrame.dispose();
			new SinglePlayerGame().start();
		});
		// add listener of multiplayer button
		multiPlayerButton.addActionListener(e -> {
			menuFrame.dispose();
			new MultiPlayerGame().start();
		});
	}
}