package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Game.Model;

public class ExitController implements ActionListener {

    private Model model;

    public ExitController() {
        model = Model.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.endGame();
    }

}
