package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Game.Model;

public class StartMenuController implements ActionListener {

    private Model model;
    private JTextField numRounds;

    public StartMenuController(JTextField input) {
        model = Model.getInstance();
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
