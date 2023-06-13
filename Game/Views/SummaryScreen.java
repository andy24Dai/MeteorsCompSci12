package Game.Views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Model;
import Game.Controllers.ExitController;
import Game.Controllers.PlayAgainController;

public class SummaryScreen extends JPanel {

    private Model model;
    private JLabel info;
    private JButton playAgain;
    private JButton exit;

    public SummaryScreen() {
        model = Model.getInstance();
        info = new JLabel();
        playAgain = new JButton("Play Again");
        exit = new JButton("Exit");

        this.layoutView();
        this.registerControllers();

    }

    private void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(info);
        this.add(playAgain);
        this.add(exit);
    }

    private void registerControllers() {
        PlayAgainController playAgainController = new PlayAgainController();
        playAgain.addActionListener(playAgainController);

        exit.addActionListener(ExitController.getInstance());
    }

    public void update() {
        double average = 0;
        for (double n : model.getTimes()) {
            average += n;
        }

        average /= model.getRoundNum();
        this.info.setText(Double.toString(average));

    }

}
