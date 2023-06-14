package Game.Controllers;

/*  ShipController
 *  Andy Dai
 *  June 12 2023
 *  controller for the ship
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Game.GameObjects.Ship;
import Game.Util.GameConstants;

public class ShipController implements KeyListener {

    // ************* variables *************
    private Ship ship; // reference to a ship object

    private int latestMoveX; // latest inputted horizontal move
    private int latestMoveY; // latest inputted horizontal move

    // ************* constructor *************
    public ShipController(Ship s) {
        ship = s;
        latestMoveX = 0;
    }

    // ************* KeyListener Methods *************
    // sets acceleration states of ship
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_D: // right
                latestMoveX = KeyEvent.VK_D;
                ship.setAccelX(GameConstants.RIGHT);
                break;
            case KeyEvent.VK_A: // left
                latestMoveX = KeyEvent.VK_A;
                ship.setAccelX(GameConstants.LEFT);
                break;
            case KeyEvent.VK_W: // up
                latestMoveY = KeyEvent.VK_W;
                ship.setAccelY(GameConstants.UP);
                break;
            case KeyEvent.VK_S: // down
                latestMoveY = KeyEvent.VK_S;
                ship.setAccelY(GameConstants.DOWN);
                break;
            default:
                break;
        }
    }// keyPressed

    // sets v to 0 if latest key pressed is released
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == latestMoveX) {
            ship.setAccelX(0);
        }

        if (key == latestMoveY) {
            ship.setAccelY(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}// class
