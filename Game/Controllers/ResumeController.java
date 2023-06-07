package Game.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Game.Model;

public class ResumeController implements ActionListener {

    private Model model;
    private JButton resume;

    public ResumeController(JButton resumeButton) {
        model = Model.getInstance();
        resume = resumeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.resumeGame();
    }

}
