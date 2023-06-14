package Game.Controllers;

/*  ContinueController
 *  Andy Dai
 *  June 12 2023
 *  controller for continue button of EndRoundScreen
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Model;

public class ContinueController implements ActionListener {

    private Model model;

    // constructor
    public ContinueController() {
        model = Model.getInstance();
    }

    // continues game when an action is detected
    @Override
    public void actionPerformed(ActionEvent e) {
        model.continueGame();
    }

}// class
