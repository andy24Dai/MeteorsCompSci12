package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Game.GameObjects.Meteor;
import Game.GameObjects.Ship;
import Game.Util.GameConstants;
import Game.View.Screens;

public class Model implements ActionListener {

    // ************ VARIABLES **************

    private static Model mModel;
    private View gui;

    // game objects
    private Ship ship;
    private ArrayList<Meteor> meteors;

    // game states
    private int roundNum;
    private int currentRound;

    // time
    private double timeSurvived;
    private Timer timer;
    private long prevTime;
    private long currentTime;

    // ************ METHODS **************

    public static Model getInstance() {
        if (mModel == null) {
            mModel = new Model();
        }
        return mModel;
    }

    private Model() {
        ship = new Ship(0, 0);

        meteors = new ArrayList<Meteor>();
        meteors.add(new Meteor(200, 100));
        meteors.add(new Meteor(600, 100));

        timer = new Timer(GameConstants.REFRESH_RATE_MILISECONDS, this);

        timeSurvived = 0;

        currentRound = 1;

        prevTime = 0;
        currentTime = System.currentTimeMillis();
    }

    // ************ GETTERS **************
    public ArrayList<Meteor> getMeteors() {
        return meteors;
    }

    public Ship getShip() {
        return ship;
    }

    public double getDeltaT() {
        prevTime = currentTime;
        currentTime = System.currentTimeMillis();
        return (currentTime - prevTime) / 1000.0;
    }

    public double getTimeSurvived() {
        return timeSurvived;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public Screens getCurrentScreen() {
        return gui.getCurrentScreen();
    }

    // ************ SETTERS **************

    // sets gui reference
    public void setGUI(View g) {
        gui = g;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    // ************ UPDATE **************

    // updates gui
    public void updateView() {
        double dt = getDeltaT();
        ship.moveShip(dt);
        updateMeteors(dt);

        System.out.println("updating " + dt);
        timeSurvived += dt;

        gui.update();
    }

    private void updateMeteors(double dt) {
        for (Meteor meteor : meteors) {
            meteor.updatePosition(dt);
            // System.out.print(ship.isColliding(meteor) + " ");
        }

        // System.out.println();

    }

    // ************ GAME STATE CONTROL **************

    public void startGame(int num) {
        this.setRoundNum(num);
        this.timer.start();
        gui.setScreen(Screens.GAME);
    }

    public void pauseGame() {
        this.timer.stop();
        gui.setScreen(Screens.PAUSE);
    }

    public void resumeGame() {
        this.timer.start();
        this.currentTime = System.currentTimeMillis();
        gui.setScreen(Screens.GAME);
    }

    public void endGame() {
        this.timer.stop();
        gui.setScreen(Screens.SUMMARY);
    }

    public void endRound() {
        this.timer.stop();
        gui.setScreen(Screens.END);
    }

    public void continueGame() {
        if (currentRound < roundNum) {
            this.resetGame();
            this.resumeGame();
            currentRound++;
        } else {
            endGame();
        }
    }

    private void resetGame() {
        timeSurvived = 0;
        ship.setPosition(GameConstants.DISPLAY_WIDTH / 2, GameConstants.DISPLAY_HEIGHT / 2);
        ;
        meteors.clear();
    }

    // ************ OTHER **************

    @Override
    public void actionPerformed(ActionEvent e) {
        updateView();
    }

}// class
