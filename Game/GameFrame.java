package Game;

/*  GameFrame
 *  Andy Dai
 *  June 12 2023
 *  Modified frame that sets up the game
 */

import javax.swing.JFrame;

import Game.Controllers.ExitController;

public class GameFrame extends JFrame {
    Model model;
    View gui;

    // constructor
    public GameFrame() {
        super("Game");

        model = Model.getInstance();
        gui = View.getInstance();

        ExitController.getInstance().setFrameInstance(this); // sets ExitController frame reference to this

        this.setContentPane(gui);
        this.pack();
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}// class
