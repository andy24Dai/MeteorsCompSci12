package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Game.Model;

public class ResumeController implements ActionListener {

    private Model model;

    public ResumeController(JButton resumeButton) {
        model = Model.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.resumeGame();
    }

}
