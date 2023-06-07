package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.swing.text.Position;
import javax.swing.JComponent;

import Game.GameObjects.ColliderObject;
import Game.GameObjects.Meteor;
import Game.GameObjects.Ship;
import Game.Util.GameConstants;
import Game.Util.Vector2D;

public class JGameDisplay extends JComponent {
    private Model model;
    private Ship ship;

    public JGameDisplay(Model m, int w, int h) {
        super();
        model = m;
        ship = model.getShip();
        ship.setPosition(w / 2, h / 2);
        this.setPreferredSize(new Dimension(w, h));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        drawObject(g, ship);
        drawMeteors(g);

    }

    private void drawMeteors(Graphics g) {
        for (Meteor meteor : model.getMeteors()) {
            drawObject(g, meteor);
        }
    }

    private void drawObject(Graphics g, ColliderObject object) {
        Vector2D cornerPos = posToCorner(object.getPosition(), object.getIconSize());
        g.drawImage(object.getIcon(), (int) cornerPos.getX(), (int) cornerPos.getY(), null);
    }

    // translates center point to top left corner
    private Vector2D posToCorner(Vector2D pos, int[] size) {
        return new Vector2D(pos.getX() - size[0] / 2, pos.getY() - size[1] / 2);
    }
}
