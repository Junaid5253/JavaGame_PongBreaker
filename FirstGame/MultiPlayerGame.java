import javax.swing.JFrame;

public class MultiPlayerGame {
    public void start(){
        JFrame frame = new JFrame("Multiplayer");
        GamePanelMulti gamePanelMulti = new GamePanelMulti(frame);
		frame.add(gamePanelMulti);
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
