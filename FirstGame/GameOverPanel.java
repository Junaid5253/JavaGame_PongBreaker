import javax.swing.*;
import java.awt.*;

public class GameOverPanel {
    public GameOverPanel(String mode, String wonOrLost) {
        this(mode, wonOrLost, -1); // -1 means "no score"
    }
    public GameOverPanel(String mode, String wonOrLost, int score) {
        JFrame frame = new JFrame(wonOrLost);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null);

        JLabel label = new JLabel(wonOrLost, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(100, 10, 200, 40);
        panel.add(label);

        // if single game
        if(score >= 0){
            System.out.println(score); // for testing purposes
            JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setBounds(100, 50, 200, 20);
            panel.add(scoreLabel);
        }

        JButton restartButton = new JButton("Restart");
        JButton mainMenuButton = new JButton("Main Menu");
        JButton exitButton = new JButton("Exit");

        restartButton.setBounds(100, 100, 200, 40);
        mainMenuButton.setBounds(100, 160, 200, 40);
        exitButton.setBounds(100, 220, 200, 40);

        panel.add(restartButton);
        panel.add(mainMenuButton);
        panel.add(exitButton);

        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        restartButton.addActionListener(e -> {
            frame.dispose();
            if (mode.equals("single")) {
                new SinglePlayerGame().start();
            } else { // or mode.equals("multi")
                new MultiPlayerGame().start();
            }
        });

        mainMenuButton.addActionListener(e -> {
            frame.dispose();
            MainMenu.main(null);
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}
