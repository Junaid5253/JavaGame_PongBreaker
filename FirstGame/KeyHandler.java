import java.awt.event.*;

public class KeyHandler implements KeyListener{
    public boolean leftPressed1; 
    public boolean rightPressed1;
    public boolean leftPressed2;
    public boolean rightPressed2;
    public boolean space1;
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed1 = true; // break not used by using ->
            case KeyEvent.VK_RIGHT -> rightPressed1 = true;
            case KeyEvent.VK_A -> leftPressed2 = true;
            case KeyEvent.VK_D -> rightPressed2 = true;
            case KeyEvent.VK_SPACE -> space1 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed1 = false;
            case KeyEvent.VK_RIGHT -> rightPressed1 = false;
            case KeyEvent.VK_A -> leftPressed2 = false;
            case KeyEvent.VK_D -> rightPressed2 = false;
            case KeyEvent.VK_SPACE -> space1 = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
