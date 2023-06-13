package Game.Controllers;

/*  ResumeController
 *  Andy Dai
 *  June 12 2023
 *  controller for resume button of PauseMenu
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Game.Model;

public class ResumeController implements ActionListener {

    private Model model;

    // constructor
    public ResumeController(JButton resumeButton) {
        model = Model.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.resumeGame();
    }

}
