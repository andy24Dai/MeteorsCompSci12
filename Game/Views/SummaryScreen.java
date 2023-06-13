package Game.Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;

/*  SummaryScreen
 *  Andy Dai
 *  June 12 2023
 *  Screen that shows when the game ends
 */

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Model;
import Game.Controllers.ExitController;
import Game.Controllers.PlayAgainController;

public class SummaryScreen extends JPanel {

    private Model model;
    private JLabel gameSummary;
    private JLabel time;
    private JLabel meteors;
    private JButton playAgain;
    private JButton exit;

    // constructor
    public SummaryScreen() {
        model = Model.getInstance();

        gameSummary = new JLabel("Game Summary");
        gameSummary.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 75));

        time = new JLabel();
        time.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));

        meteors = new JLabel();
        meteors.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));

        playAgain = new JButton("Play Again");
        playAgain.setMaximumSize(new Dimension(200, 100));

        exit = new JButton("Exit");
        exit.setMaximumSize(new Dimension(200, 100));

        this.layoutView();
        this.registerControllers();

    }// constructor

    // adds components to itself
    private void layoutView() {
        JPanel info = new JPanel();
        BoxLayout boxLayoutInfo = new BoxLayout(info, BoxLayout.Y_AXIS);
        info.setLayout(boxLayoutInfo);
        info.add(gameSummary);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        info.add(time);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        info.add(meteors);

        JPanel buttons = new JPanel();
        BoxLayout boxLayoutButtons = new BoxLayout(buttons, BoxLayout.X_AXIS);
        buttons.setLayout(boxLayoutButtons);
        buttons.add(playAgain);
        buttons.add(Box.createRigidArea(new Dimension(10, 0)));
        buttons.add(exit);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(info);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(buttons);
    }// layoutView

    // sets up controllers
    private void registerControllers() {
        PlayAgainController playAgainController = new PlayAgainController();
        playAgain.addActionListener(playAgainController);

        exit.addActionListener(ExitController.getInstance());
    }

    // updates gui
    public void update() {
        time.setText(String.format("Time Survived: %.2f", model.getAverage(model.getTimes())));
        meteors.setText(String.format("Meteors Dodged: %d", model.getAverage(model.getMeteorsDodged())));
    }

}
