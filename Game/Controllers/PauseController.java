package Game.Controllers;

/*  PauseController
 *  Andy Dai
 *  June 12 2023
 *  controller for pausing the game of GameScreen
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.Model;

public class PauseController implements KeyListener {

    private Model model;

    // constructor
    public PauseController() {
        model = Model.getInstance();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // if escape is pressed
            model.pauseGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
