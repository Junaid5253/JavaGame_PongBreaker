import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanelMulti extends JPanel implements ActionListener{
    private final int PANELWIDTH = 700;
    private final int PANELHEIGHT = 500;
    private final int FPS = 60;
    private int player1lives = 3;
    private int player2lives = 3;
    private GameObject paddle1 = new Paddle(PANELWIDTH, PANELHEIGHT);
    private GameObject paddle2 = new Paddle(PANELWIDTH, PANELHEIGHT);
    private Timer timer;
    private KeyHandler keyHandler = new KeyHandler();
    private boolean ballMoving = false;;
    private Ball ball = new Ball();
    private JFrame parentFrame;

    GamePanelMulti(JFrame frame){
        this.parentFrame = frame;
        setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        setFocusable(true);
        addKeyListener(keyHandler);
        setBackground(Color.BLACK);
        modifyPaddles();
        timer = new Timer(1000/FPS, this);
        timer.start();

        
        ball.positionBallOnPaddle((Paddle)paddle1);
    }
    public void modifyPaddles(){
        paddle2.setY(0);
        paddle2.setColor(Color.GREEN);
        paddle1.setColor(Color.RED);
        
    }
    @Override 
    public void actionPerformed(ActionEvent e){
        // 1. update
        update();
        // 2. repaint
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // paint paddle 1
        g.setColor(paddle1.getColor());
        g.fillRect(paddle1.getX(), paddle1.getY(), paddle1.getWidth(), paddle1.getLength());

        // paint paddle 2
        g.setColor(paddle2.getColor());
        g.fillRect(paddle2.getX(), paddle2.getY(), paddle2.getWidth(), paddle2.getLength());

        // paint ball
        g.setColor(ball.getColor());
        g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getLength());

        // paint lives of 1
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Player1 Lives: " + player1lives, 5, 8);
        // paint lives of 2
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Player2 Lives: " + player2lives, 5, 18);
        

    }
    public void update(){
        keyHandling();
        if(ballMoving){
            ball.move();
            if(ball.getY() < 0 - ball.getLength()){
                // lose player 2
                player2lives--;
                ballMoving = false;
                ball.resetSpeed();
                if(player2lives <= 0){
                    endGame("Player 1 Won");    
                }
                
            }
            else if(ball.getY() > getHeight()){
                // lose player 1
                player1lives--;
                ballMoving = false;
                ball.resetSpeed();
                if(player1lives <= 0){
                    endGame("Player 2 Won");    
                }
                
            }
        }
        else{
            ball.positionBallOnPaddle((Paddle)paddle1);
        }

        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getLength());
        Rectangle paddle1Rect = new Rectangle(paddle1.getX(), paddle1.getY(), paddle1.getWidth(), paddle1.getLength());
        Rectangle paddle2Rect = new Rectangle(paddle2.getX(), paddle2.getY(), paddle2.getWidth(), paddle2.getLength());

        // intersections
        if(ballRect.intersects(paddle1Rect)){
            ball.bounce();
            ball.increaseSpeed();
            // Correct position to be just above paddle1
            ball.setY(paddle1.getY() - ball.getLength());
        } 
        else if(ballRect.intersects(paddle2Rect)){
            ball.bounce();
            ball.increaseSpeed();
            // Correct position to be just below paddle2
            ball.setY(paddle2.getY() + paddle2.getLength());
        }
            
        
    }   
    public void keyHandling(){
        if(keyHandler.leftPressed1){
            ((Paddle) paddle1).moveLeft();
        }
        if(keyHandler.rightPressed1){
            ((Paddle) paddle1).moveRight(PANELWIDTH);
        }
        if(keyHandler.leftPressed2){
            ((Paddle) paddle2).moveLeft();
        }
        if(keyHandler.rightPressed2){
            ((Paddle) paddle2).moveRight(PANELWIDTH);
        }
        if(keyHandler.space1){
            ballMoving = true;
        }
    }
    private void endGame(String message) {
        timer.stop();
        parentFrame.dispose();
        new GameOverPanel("multi", message);
    }
}
