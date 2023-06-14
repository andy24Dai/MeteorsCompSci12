package Game;

/*  View
 *  Andy Dai
 *  June 12 2023
 *  stores and updates graphical elements of the game
 */

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

    // sub screens
    private GameScreen game;
    private StartMenu start;
    private PauseMenu pause;
    private EndRoundScreen end;
    private SummaryScreen summary;

    // screen switching
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
    // get singleton instance
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
