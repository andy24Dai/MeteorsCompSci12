package Game.Views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Model;

public class SummaryScreen extends JPanel {

    private Model model;
    private JLabel info;
    private JButton playAgain;

    public SummaryScreen() {
        model = Model.getInstance();
        info = new JLabel();
        playAgain = new JButton();

        this.layoutView();
        this.registerControllers();
        this.update();
    }

    private void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(info);
        this.add(playAgain);
    }

    private void registerControllers() {
    }

    public void update() {
        this.info.setText("among us");
    }

}
