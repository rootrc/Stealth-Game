package core;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

abstract public class Screen {
    public abstract void render(Graphics2D g2d);
    public abstract void process();
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void mouseClicked(MouseEvent e);
}
