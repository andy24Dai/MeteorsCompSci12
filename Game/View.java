package Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Game.Controllers.ShipController;
import Game.GameObjects.Ship;
import Game.Util.GameConstants;
import Game.Views.GameScreen;
import Game.Views.PauseMenu;
import Game.Views.StartMenu;

public class View extends JPanel {

    // variables
    private Model model;
    private GameScreen game;
    private StartMenu start;
    private PauseMenu pause;
    private CardLayout cardLayout;

    // constructor
    public View(Model m) {
        model = m;
        this.model.setGUI(this);

        game = new GameScreen(m);
        start = new StartMenu(m);
        pause = new PauseMenu(m);

        cardLayout = new CardLayout();

        this.layoutView();
        this.registerControllers();
        this.update();
    }

    // adds components to itself
    private void layoutView() {
        this.setLayout(cardLayout);
        this.add("GameScreen", game);
        this.add("StartMenu", start);
        this.add("PauseMenu", pause);

        cardLayout.show(this, "GameScreen");

    }// layoutView

    // sets up controllers for button
    private void registerControllers() {
    }

    // updates gui
    public void update() {
        repaint();
        game.update();
    }
}// class
