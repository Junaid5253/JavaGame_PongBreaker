import java.awt.Color;

class Paddle extends GameObject{
    // private int x = panelWidth/2 - (width/2);
    // private int y = panelHeight - length;
    // private int width = 100;
    // private int length = 20;
    // private Color color = Color.WHITE;
    private int speed = 10;
    Paddle(int panelWidth, int panelHeight) {
        // int tempWidth = 100;
        // int tempLength = 20;

        // int tempX = panelWidth / 2 - (tempWidth / 2);
        // int tempY = panelHeight - tempLength;

        super(Color.WHITE, 0, 0, 100, 20);
        int tempX = panelWidth / 2 - (width / 2);
        super.setX(tempX);
        int tempY = panelHeight - length;
        super.setY(tempY);
    }

    // Overide the method
    @Override
    public void move(){}
    // setters
    
    
    public void setSpeed(int speed) {this.speed = speed;}
    // getters
    
    public int getSpeed(){return speed;}


    public void moveLeft() {
        if (x > 0) {
            x -= speed;
        }
        if (x < 0) {
            x = 0;
        }
    }
    public void moveRight(int panelWidth) {
        if (x + width < panelWidth) {
            x += speed;
        }
        if (x + width > panelWidth) {
            x = panelWidth - width;
        }
    }
    public void increaseWidth(){
        if(width < panelWidth/2){
            this.width += 20;    
        }
        
    }


}
