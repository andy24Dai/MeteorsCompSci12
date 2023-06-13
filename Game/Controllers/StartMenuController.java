package Game.Controllers;

/*  StartMenuController
 *  Andy Dai
 *  June 12 2023
 *  controller for start button of StartMenu
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Game.Model;

public class StartMenuController implements ActionListener {

    private Model model;
    private JTextField numRounds;

    // constructor
    public StartMenuController(JTextField input) {
        model = Model.getInstance();
        numRounds = input;
    }

    // gets round num from text field
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int num = Integer.parseInt(numRounds.getText());
            model.startGame(num);

        } catch (NumberFormatException ex) {
            numRounds.setText("Invalid");
            numRounds.selectAll();
        }
    }

}
