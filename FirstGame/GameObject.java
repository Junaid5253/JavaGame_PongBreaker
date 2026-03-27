import java.awt.Color;
abstract class GameObject{
	// common things 
	// color , x, y , length, width
	protected Color color;
	protected int x, y;
	protected int length, width;
	GameObject(Color color, int x, int y, int width, int length){
		this.color  = color;
		this.x = x;
		this.y = y;
		this.length = length;
		this.width  = width;
	}

	public Color getColor(){
        return color;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getLength(){
        return length;
    }
    public int getWidth(){
        return width;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setWidth(int width){
    	this.width = width;
    }
    public void setLength(int length){
    	this.length = length;
    }
    public void setColor(Color color) {this.color = color;}
	// making method to achieve polymorphism
	// this method would be overrided in every gameObject sub class
	public abstract void move();
}