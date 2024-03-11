package core;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Panel extends JPanel {
    Game game;

    Panel(Game game) {
        super();
        this.game = game;
        addKeyListener(new TKeyAdapter());
        addMouseListener(new TMouseAdapter());
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setHints(g2d);

        game.screen.render(g2d);
    }

    public static void setHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

        // will cause extreme lag
        // g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        // g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    class TKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.screen.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            game.screen.keyReleased(e);
        }
    }

    class TMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            game.screen.mouseClicked(e);
        }
    }
}