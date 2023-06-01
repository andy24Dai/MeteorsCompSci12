package Game;

import java.awt.BorderLayout;
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

public class View extends JPanel {

    // variables
    private Model model;
    private JGameDisplay display;
    private JButton text;

    // constructor
    public View(Model m) {
        model = m;
        this.model.setGUI(this);

        display = new JGameDisplay(model, GameConstants.DISPLAY_WIDTH, GameConstants.DISPLAY_HEIGHT);
        text = new JButton("Button");
        text.setPreferredSize(new Dimension(100, 20));
        text.setFocusable(false);

        this.layoutView();
        this.registerControllers();
        this.update();
    }

    // adds components to itself
    private void layoutView() {
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));

        this.setLayout(new BorderLayout());
        this.add(text, BorderLayout.WEST);
        this.add(display, BorderLayout.CENTER);
    }// layoutView

    // sets up controllers for button
    private void registerControllers() {
        display.addKeyListener(new ShipController(model.getShip()));
    }

    // updates gui
    public void update() {
        repaint();

        model.getShip().printInfo();
    }
}// class
