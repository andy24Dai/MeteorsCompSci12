package Game.Views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Model;
import Game.Controllers.ContinueController;

public class EndGameScreen extends JPanel {

    private Model model;
    private JLabel timeSurvived;
    private JLabel meteorsDodged;
    private JButton proceed;

    public EndGameScreen() {
        model = Model.getInstance();

        timeSurvived = new JLabel();
        meteorsDodged = new JLabel();
        proceed = new JButton("Continue");

        this.layoutView();
        this.registerControllers();
        this.update();
    }

    private void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(timeSurvived);
        this.add(meteorsDodged);
        this.add(proceed);
    }

    private void registerControllers() {
        ContinueController continueController = new ContinueController();
        proceed.addActionListener(continueController);
    }

    public void update() {
        timeSurvived.setText(Double.toString(model.getTimeSurvived()));
        meteorsDodged.setText("123123");
    }

}
