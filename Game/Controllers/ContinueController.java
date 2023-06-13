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

    @Override
    public void actionPerformed(ActionEvent e) {
        model.continueGame();
    }

}
