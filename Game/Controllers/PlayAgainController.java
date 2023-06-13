package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Model;

public class PlayAgainController implements ActionListener {

    private Model model;

    public PlayAgainController() {
        model = Model.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.restartGame();
    }

}
