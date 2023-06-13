package Game;

import java.awt.CardLayout;
import javax.swing.JPanel;

import Game.Views.EndRoundScreen;
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
    private EndRoundScreen end;
    private SummaryScreen summary;

    private CardLayout cardLayout;
    private JPanel switchPanel;

    // states the screen can be in
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
        end = new EndRoundScreen();
        summary = new SummaryScreen();

        switchPanel = new JPanel();
        cardLayout = new CardLayout();

        this.layoutView();
        this.registerControllers();

    }// constructor

    // adds components to itself
    private void layoutView() {
        switchPanel.setLayout(cardLayout);
        switchPanel.add("Game", game);
        switchPanel.add("Start", start);
        switchPanel.add("Pause", pause);
        switchPanel.add("End", end);
        switchPanel.add("Summary", summary);

        this.add(switchPanel);
        this.setScreen(Screens.START);

    }// layoutView

    // sets up controllers for button
    private void registerControllers() {
    }

    // updates gui
    public void update() {
        game.update();
    }

    // ************ SETTERS **************

    // sets screen to be displayed
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

            case END:
                this.cardLayout.show(switchPanel, "End");
                this.end.update();
                break;

            case SUMMARY:
                this.cardLayout.show(switchPanel, "Summary");
                this.summary.update();
                break;
            default:
                break;
        }// switch

    }// setScreen

}// class
