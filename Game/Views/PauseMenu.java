package Game.Views;

import java.awt.Dimension;

import javax.swing.Box;

/*  PauseMenu
 *  Andy Dai
 *  June 12 2023
 *  Screen that shows when the game is paused
 */

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Model;
import Game.Controllers.ExitController;
import Game.Controllers.ResumeController;

public class PauseMenu extends JPanel {
    private Model model;
    private JButton resume;
    private JButton exit;

    // constructor
    public PauseMenu() {
        model = Model.getInstance();
        resume = new JButton("Resume");
        resume.setMaximumSize(new Dimension(200, 100));

        exit = new JButton("Exit");
        exit.setMaximumSize(new Dimension(200, 100));

        this.layoutView();
        this.registerControllers();
        this.update();
    }// constructor

    // adds components to itself
    private void layoutView() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(resume);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(exit);
    }

    // sets up controllers
    private void registerControllers() {
        ResumeController resumeControl = new ResumeController(resume);
        resume.addActionListener(resumeControl);

        exit.addActionListener(ExitController.getInstance());
    }

    private void update() {
    }

}
