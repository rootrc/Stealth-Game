package core;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import core.level.room.Room;
import core.level.room.RoomFactory;

public class Game {
    final static int delay = 20;
    public JFrame frame;
    public Panel panel;
    public Screen screen;
    public Room room;

    private Game() {
        frame = new JFrame();
        panel = new Panel(this);
        room = RoomFactory.createRoom(1);
        screen = room;
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
                screen.process();
                panel.repaint();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Game();
    }

}