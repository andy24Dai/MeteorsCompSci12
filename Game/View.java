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

        cardLayout.show(this, "StartMenu");

    }// layoutView

    // sets up controllers for button
    private void registerControllers() {
    }

    // updates gui
    public void update() {
        repaint();
        game.update();
    }

    public void setScreen(Screens sc) {
        switch (sc) {
            case GAME:
                cardLayout.show(this, "GameScreen");
                break;

            case START:
                cardLayout.show(this, "StartMenu");
                break;

            case PAUSE:
                cardLayout.show(this, "PauseMenu");
                break;
            default:
                break;
        }
    }
}// class
