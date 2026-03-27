import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class GamePanelSingle extends JPanel implements ActionListener {
    private final int PANELHEIGHT = 500;
    private final int PANELWIDTH = 700;
    private int score;
    private GameObject paddle = new Paddle(PANELWIDTH, PANELHEIGHT);;
    private GameObject ball = new Ball();
    private ArrayList<PowerUps> powerUps;
    private ArrayList<Block> blocks;
    private Timer timer;
    private boolean ballMoving;
    private final int FPS = 60;
    private KeyHandler keyHandler = new KeyHandler();
    private JFrame parentFrame;
    public GamePanelSingle(JFrame frame) {
        this.parentFrame = frame;
        setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        setFocusable(true);
        addKeyListener(keyHandler);
        setBackground(Color.BLACK);
        timer = new Timer(1000/FPS, this); // 16ms Delay
        timer.start(); // calls the actionperformed
        ballMoving = false;
        blocks = new ArrayList<>();
        powerUps = new ArrayList<>();
        createBlocks();
        score = 0;

        ((Ball)ball).positionBallOnPaddle((Paddle)paddle);
    }

    private void createBlocks() {
        int blockY = 20;
        int blockSpacing = 10;
        GameObject block = new Block(0, 0);
        int blockWidth = block.getWidth();
        int blockHeight = block.getLength();

        int startX = 10;
        int endX = 690;

        int rows = 5;

        for (int row = 0; row < rows; row++) {
            int x = startX;
            while (x + blockWidth <= endX) {
                blocks.add(new Block(x, blockY + row * (blockHeight + blockSpacing)));
                x += blockWidth + blockSpacing;
            }
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // paddle draw
        g.setColor(paddle.getColor());
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getLength());

        // Draw ball
        g.setColor(ball.getColor());
        g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getLength());

        // Draw blocks
        for (Block block : blocks) {
            g.setColor(block.getColor());
            g.fillRect(block.getX(), block.getY(), block.getWidth(), block.getLength());
        }
        // Drawing powerUps
        for (PowerUps p : powerUps) {
            g.setColor(p.getColor());
            g.fillOval(p.getX(), p.getY(), p.getWidth(), p.getLength());
        }
        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 7, 15); 
    }

    @Override
    public void actionPerformed(ActionEvent e) { //  GameLoop 
        //1. Step1:(Updates)
         
        //2. Step2:(Repaint)
        update();
        

        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getLength());
        Rectangle paddleRect = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getLength());

        // Ball hits paddle
        if (ballRect.intersects(paddleRect)) {
            ((Ball)ball).bounce();
            // Correct position to be just above paddle
            ball.setY(paddle.getY() - ball.getLength());
        }

        // Ball hits block
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            Rectangle blockRect = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getLength());
            

            if (ballRect.intersects(blockRect)) {
                Sound.playSound("sound1.wav"); // playing a sound
                ((Ball)ball).bounce();

                if(Math.random() <= 0.25){ //1/4 times 
                    powerUps.add(new PowerUps(b.getX() + b.getWidth() / 2, b.getY()));
                }
                blocks.remove(i);
                score += 10;
                if (blocks.isEmpty()) {
                    endGame("You Win!");
                }
                
                break;
            }
        }

        repaint();

    }

    public void update(){
        keyHandling();
        if (ballMoving) {
            ball.move();
            if(ball.getY() <= 0){
                ((Ball)ball).setDY(-1 * (((Ball) ball).getDY()));
            }
            if (ball.getY() > getHeight()) {
                endGame("You Lost");            }
        } else {
            // positioning ball on paddle
            ((Ball)ball).positionBallOnPaddle((Paddle)paddle);
        }

        // for powerUps
        for(int i = 0; i<powerUps.size(); i++){
            PowerUps p = powerUps.get(i);
            p.move();

            // removing if it goes off screen
            if(p.getY() > getHeight()){
                powerUps.remove(i);
                i--; // for not skipping the others (A, B, C) would skip B 
            }
            Rectangle paddleRect = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getLength());
            Rectangle powerUpRect = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getLength());

            if(paddleRect.intersects(powerUpRect)){
                // 50% chance of each powerup
                if(Math.random() < 0.5 ){
                    p.applyPowerUp(paddle);
                }
                else{
                    p.applyPowerUp(ball);
                }

                powerUps.remove(i);
                i--;
            }
        }

    }
    public void keyHandling(){
        // Move paddle left
        if (keyHandler.leftPressed1) {
            ((Paddle) paddle).moveLeft();
        }

        // Move paddle right
        if (keyHandler.rightPressed1) {
            ((Paddle) paddle).moveRight(getWidth());
        }

        // Start ball movement
        if (keyHandler.space1) {
            ballMoving = true;
        }
    }

    private void endGame(String message) {
        timer.stop();
        parentFrame.dispose();
        new GameOverPanel("single", message, score);
    }

  
}
