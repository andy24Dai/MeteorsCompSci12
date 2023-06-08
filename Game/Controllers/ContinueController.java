package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Game.Model;

public class ContinueController implements ActionListener {

    private Model model;

    public ContinueController() {
        model = Model.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.continueGame();
    }

}
