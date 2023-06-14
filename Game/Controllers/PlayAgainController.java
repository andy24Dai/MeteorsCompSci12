package Game.Controllers;

/*  PlayAgainController
 *  Andy Dai
 *  June 12 2023
 *  controller for play again button of SummaryScreen
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Model;

public class PlayAgainController implements ActionListener {

    private Model model;

    // constructor
    public PlayAgainController() {
        model = Model.getInstance();
    }

    // restarts game when an action is detected
    @Override
    public void actionPerformed(ActionEvent e) {
        model.restartGame();
    }

}// class
