import java.awt.Color;

class PowerUps extends GameObject{
    
    
    // private final int length = 10;
    // private final int width = 10;
    // private final Color color = Color.RED;
    private final int speed = 2;
    PowerUps(int x, int y){
        super(Color.BLUE, x, y , 10, 10);
    }
    public int getSpeed(){return speed;}
    
    @Override
    public void move(){
        if(y<=700){
            y += speed;
        }
        
    }
    
    public void applyPowerUp(GameObject object){
        if(object instanceof Paddle){
            ((Paddle) object).increaseWidth();
        }
        if(object instanceof Ball){
            ((Ball) object).increaseSpeed();
        }
    }
}
