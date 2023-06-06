package Game;

import java.awt.CardLayout;
import javax.swing.JPanel;
import Game.Views.GameScreen;
import Game.Views.PauseMenu;
import Game.Views.StartMenu;

public class View extends JPanel {

    // variables
    private static View mView;
    private Model model;
    private GameScreen game;
    private StartMenu start;
    private PauseMenu pause;
    private CardLayout cardLayout;
    private JPanel switchPanel;

    public static View getInstance() {
        if (mView == null) {
            mView = new View();
        }
        return mView;
    }

    public enum Screens {
        GAME,
        START,
        PAUSE
    }

    // constructor
    private View() {
        model = Model.getInstance();
        this.model.setGUI(this);

        game = new GameScreen();
        start = new StartMenu();
        pause = new PauseMenu();

        switchPanel = new JPanel();
        cardLayout = new CardLayout();

        this.layoutView();
        this.registerControllers();
        this.update();

    }

    // adds components to itself
    private void layoutView() {
        switchPanel.setLayout(cardLayout);
        switchPanel.add("Game", game);
        switchPanel.add("Start", start);
        switchPanel.add("Pause", pause);

        this.add(switchPanel);

        cardLayout.show(switchPanel, "Start");

    }// layoutView

    // sets up controllers for button
    private void registerControllers() {
    }

    // updates gui
    public void update() {
        game.update();
    }

    public void setScreen(Screens sc) {
        switch (sc) {
            case GAME:
                this.cardLayout.show(switchPanel, "Game");
                game.requestFocusInWindow();
                break;

            case START:
                this.cardLayout.show(switchPanel, "Start");
                break;

            case PAUSE:
                this.cardLayout.show(switchPanel, "Pause");
                break;

            default:
                break;
        }
    }
}// class
