import java.awt.Color;

class Block extends GameObject{
    // private final Color colorOfBlocks = Color.GREEN;
    // private final int x;
    // private final int y;
    // private final int length = 20;
    // private final int width = 30;

    Block(int x, int y){
        super(Color.GREEN, x, y, 30, 20);
    }
    // has to override move function
    @Override
    public void move(){}
}
