package Game;

import java.awt.CardLayout;
import javax.swing.JPanel;

import Game.Views.EndGameScreen;
import Game.Views.GameScreen;
import Game.Views.PauseMenu;
import Game.Views.StartMenu;
import Game.Views.SummaryScreen;

public class View extends JPanel {

    // ************ VARIABLES **************

    private static View mView;
    private Model model;
    private GameScreen game;
    private StartMenu start;
    private PauseMenu pause;
    private EndGameScreen end;
    private SummaryScreen summary;
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private Screens currentScreen;

    public enum Screens {
        GAME,
        START,
        PAUSE,
        END,
        SUMMARY
    }

    // ************ METHODS **************
    public static View getInstance() {
        if (mView == null) {
            mView = new View();
        }
        return mView;
    }

    // constructor
    private View() {
        model = Model.getInstance();
        this.model.setGUI(this);

        game = new GameScreen();
        start = new StartMenu();
        pause = new PauseMenu();
        end = new EndGameScreen();
        summary = new SummaryScreen();

        switchPanel = new JPanel();
        cardLayout = new CardLayout();

        currentScreen = Screens.START;

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
        switchPanel.add("End", end);
        switchPanel.add("Summary", summary);

        this.add(switchPanel);

        this.setScreen(currentScreen);

    }// layoutView

    // sets up controllers for button
    private void registerControllers() {
    }

    // updates gui
    public void update() {
        game.update();
    }

    // ************ GETTERS **************

    public Screens getCurrentScreen() {
        return currentScreen;
    }

    // ************ SETTERS **************

    public void setScreen(Screens sc) {
        switch (sc) {
            case GAME:
                this.cardLayout.show(switchPanel, "Game");
                game.requestFocusInWindow();
                this.currentScreen = Screens.GAME;

                break;

            case START:
                this.cardLayout.show(switchPanel, "Start");
                this.currentScreen = Screens.START;
                break;

            case PAUSE:
                this.cardLayout.show(switchPanel, "Pause");
                this.currentScreen = Screens.PAUSE;
                break;

            case END:
                this.cardLayout.show(switchPanel, "End");
                this.end.update();
                this.currentScreen = Screens.END;
                break;

            case SUMMARY:
                this.cardLayout.show(switchPanel, "Summary");
                this.summary.update();
                this.currentScreen = Screens.SUMMARY;
                break;
            default:
                break;
        }

    }

}// class
