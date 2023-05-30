package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JComponent;

import Game.GameObjects.Ship;
import Game.Util.GameConstants;

public class JGameDisplay extends JComponent implements ActionListener {
    private Model model;
    private Ship ship;
    private Timer timer;

    public JGameDisplay(Model m, int w, int h) {
        super();
        model = m;
        ship = model.getShip();
        ship.setShipPos(w / 2, h / 2);
        this.setPreferredSize(new Dimension(w, h));
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        timer = new Timer(GameConstants.REFRESH_RATE_MILISECONDS, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        int[] cornerPos = posToCorner(ship.getShipPos(), ship.getImageSize());
        g.drawImage(ship.getImage(), cornerPos[0], cornerPos[1], null);

    }

    // translates center point to top left corner
    private int[] posToCorner(int[] pos, int[] size) {
        return new int[] { pos[0] - size[0] / 2, pos[1] - size[1] / 2 };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.updateView();
    }

}
