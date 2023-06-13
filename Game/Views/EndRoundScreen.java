package Game.Views;

/*  EndRoundScreen
 *  Andy Dai
 *  June 12 2023
 *  Screen that shows when a round ends
 */

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Model;
import Game.Controllers.ContinueController;

public class EndRoundScreen extends JPanel {

    private Model model;
    private JLabel gameOver;
    private JLabel time;
    private JLabel meteors;
    private JButton proceed;

    // constructor
    public EndRoundScreen() {
        model = Model.getInstance();

        // info
        gameOver = new JLabel("You Crashed!");
        gameOver.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 75));

        time = new JLabel();
        time.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));

        meteors = new JLabel();
        meteors.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));

        proceed = new JButton("Continue");
        proceed.setMaximumSize(new Dimension(200, 100));

        this.layoutView();
        this.registerControllers();
    }// constructor

    // adds components to itself
    private void layoutView() {
        JPanel info = new JPanel();
        BoxLayout boxLayout = new BoxLayout(info, BoxLayout.Y_AXIS);
        info.setLayout(boxLayout);
        info.add(gameOver);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        info.add(time);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        info.add(meteors);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(info);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(proceed);
    }// layout view

    // sets up controllers
    private void registerControllers() {
        ContinueController continueController = new ContinueController();
        proceed.addActionListener(continueController);
    }

    // updates gui
    public void update() {
        time.setText(String.format("Time Survived: %.2f", model.getTimeSurvived()));
        meteors.setText(String.format("Meteors Dodged: %d", model.getMeteorsDodged()[model.getCurrentRound() - 1]));
    }

}
