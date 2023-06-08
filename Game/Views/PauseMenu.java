package Game.Views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Model;
import Game.Controllers.ExitController;
import Game.Controllers.PauseController;
import Game.Controllers.ResumeController;

public class PauseMenu extends JPanel {
    private Model model;
    private JButton resume;
    private JButton exit;

    public PauseMenu() {
        model = Model.getInstance();
        resume = new JButton("Resume");
        exit = new JButton("Exit");

        this.layoutView();
        this.registerControllers();
        this.update();
    }

    private void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(resume);
        this.add(exit);
    }

    private void registerControllers() {
        this.addKeyListener(PauseController.getInstance());

        ResumeController resumeControl = new ResumeController(resume);
        resume.addActionListener(resumeControl);

        ExitController exitController = new ExitController();
        exit.addActionListener(exitController);
    }

    private void update() {
    }

}
