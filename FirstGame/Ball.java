import java.awt.Color;

class Ball extends GameObject{
    // private final Color colorOfBall = Color.RED;
    // private int x;
    // private int y;
    // private int length = 15;
    // private int width = 15;
    private final int initialDx = 3;
    private final int initialDy = -5;
    private int dx = initialDx; 
    private int dy = initialDy;
    
    Ball(){
        super(Color.RED, 0, 0, 15, 15);
    }
    @Override
    public void move(){
        x += dx;
        y += dy;

        if(x <= 0 || x>= 685){
            dx = -dx;
        }   
    }
    public void bounce(){
        dy = -dy;
    }
    
    public int getDY(){return dy;}
    
    public void setDX(int dx){
        this.dx = dx;
    }
    public void setDY(int dy){
        this.dy = dy;
    }
    public void resetSpeed(){
        if(Math.random() <= 0.5){
            this.dx = -initialDx;        
        }
        else{
            this.dx = initialDx;
        }
        
        this.dy = initialDy;
    }
    
    public void positionBallOnPaddle(Paddle paddle) {
        this.setX(paddle.getX() + paddle.getWidth() / 2 - this.getWidth() / 2);
        this.setY(paddle.getY() - this.getLength());
    }

    public void increaseSpeed(){
        int maxSpeed = 15;
        if(Math.abs(dx) < maxSpeed){
            dx = (dx > 0) ? dx + 1 : dx - 1;
        }
        if(Math.abs(dy) < maxSpeed){
            dy = (dy > 0) ? dy + 1 : dy - 1;
        }
    }
}
