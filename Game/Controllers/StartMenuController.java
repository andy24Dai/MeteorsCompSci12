package Game.Controllers;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import Game.View.Screens;

public class StartMenuController extends ActionController {

    private JTextField numRounds;

    public StartMenuController(JTextField input) {
        super();
        numRounds = input;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int num = Integer.parseInt(numRounds.getText());
            model.startGame(num);

        } catch (NumberFormatException ex) {
            numRounds.setText("Invalid");
        }
    }

}
