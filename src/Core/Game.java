package Core;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import Core.Level.Level;
import Core.Level.LevelFactory;

public class Game {
    final static int delay = 20;
    public JFrame frame;
    public Panel panel;
    public Level level;

    private Game() {
        frame = new JFrame();
        panel = new Panel(this);
        level = LevelFactory.createLevel(1);
        frame.add(panel);
        frame.pack();
        frame.setTitle("Game");
        frame.setBackground(Color.black);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Timer timer = new Timer(Game.delay, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // room.process();
                panel.repaint();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Game();
    }

}