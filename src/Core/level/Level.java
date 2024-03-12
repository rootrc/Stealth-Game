package core.level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import core.Screen;
import core.level.room.Room;
import core.level.room.RoomFactory;

public class Level extends Screen {
    private Room room;

    public Level () {
        room = RoomFactory.createRoom(1);
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 1000, 1000);
        room.draw(g2d);
    }

    public void process() {
        room.process();
    }

    public void keyPressed(KeyEvent e) {
        room.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        room.keyReleased(e);
    }

    public void mouseClicked(MouseEvent e) {

    }
}
