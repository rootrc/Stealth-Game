package core.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Stopwatch {
    private int startTime;
    private String time;

    public Stopwatch() {
        startTime = (int) (System.nanoTime() / 1000000000);
        time = "0";
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        Font currentFont = g2d.getFont();
        Font newFont = currentFont.deriveFont(28.0F);
        g2d.setFont(newFont);
        g2d.drawString(time, 512 - g2d.getFontMetrics().stringWidth(time) / 2, 40);
    }

    public void process() {
        int seconds = (int) (System.nanoTime() / 1000000000 - startTime);
        time = String.format("%02d:%02d", ((seconds / 60) % 60), ((seconds) % 60));

    }

}
