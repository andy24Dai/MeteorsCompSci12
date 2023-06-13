package Game.Views;

/*  StartMenu
 *  Andy Dai
 *  June 12 2023
 *  Screen that shows when the game starts
 */

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Game.Model;
import Game.Controllers.StartMenuController;

public class StartMenu extends JPanel {

    private Model model;
    private JLabel roundsLabel;
    private JTextField numRounds;
    private JButton start;

    // constructor
    public StartMenu() {
        model = Model.getInstance();

        roundsLabel = new JLabel("How Many Rounds:");
        numRounds = new JTextField("1");
        start = new JButton("START");
        start.setPreferredSize(new Dimension(200, 200));
        start.setFocusable(false);

        this.layoutView();
        this.registerControllers();
    }// constructor

    // adds components to itself
    private void layoutView() {
        this.setLayout(new BorderLayout());
        this.add(roundsLabel, BorderLayout.WEST);
        this.add(numRounds, BorderLayout.CENTER);
        this.add(start, BorderLayout.SOUTH);
    }

    // sets up controllers
    private void registerControllers() {
        StartMenuController controller = new StartMenuController(numRounds);
        start.addActionListener(controller);
    }

}
