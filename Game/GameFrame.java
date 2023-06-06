package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Game.Controllers.ShipController;

public class GameFrame extends JFrame {
    Model model;
    View gui;

    public GameFrame() {
        super("Game");
        model = Model.getInstance();
        gui = View.getInstance();

        this.setContentPane(gui);
        this.pack();
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
