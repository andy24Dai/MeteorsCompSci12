package Game.Views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Game.Model;
import Game.Controllers.ContinueController;

public class EndRoundScreen extends JPanel {

    private Model model;
    private JTextArea info;
    private JButton proceed;

    public EndRoundScreen() {
        model = Model.getInstance();

        info = new JTextArea();
        info.setPreferredSize(new Dimension(20, 20));

        proceed = new JButton("Continue");
        proceed.setPreferredSize(new Dimension(20, 200));

        this.layoutView();
        this.registerControllers();
    }

    private void layoutView() {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        this.add(info, BorderLayout.CENTER);
        this.add(proceed, BorderLayout.SOUTH);
    }

    private void registerControllers() {
        ContinueController continueController = new ContinueController();
        proceed.addActionListener(continueController);
    }

    public void update() {
        info.setText(String.format("Time Survived: %.2f\n Meteors Dodged: %d",
                model.getTimeSurvived(), model.getMeteorsDodged()[model.getCurrentRound() - 1]));
    }

}
