import javax.swing.*;

public class SinglePlayerGame {
	public void start() {
		JFrame frame = new JFrame("SinglePlayer");
		GamePanelSingle gamePanelSingle = new GamePanelSingle(frame);
		frame.add(gamePanelSingle);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);		
	}
}