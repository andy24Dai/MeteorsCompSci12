package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Game.GameObjects.Meteor;
import Game.GameObjects.Ship;
import Game.Util.GameConstants;
import Game.Util.Vector2D;
import Game.View.Screens;

public class Model implements ActionListener {

    // ************ VARIABLES **************

    private static Model mModel;
    private View gui;

    // game objects
    private Ship ship;
    private ArrayList<Meteor> meteors;

    // game states
    private boolean isGameRunning;
    private int roundNum;
    private int currentRound;

    // time
    private Timer timer;
    private long startTime;
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

        isGameRunning = false;

        currentRound = 1;

        startTime = System.currentTimeMillis();
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

    public double getTime() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
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

    public void setGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }

    // ************ UPDATE **************

    // updates gui
    public void updateView() {
        if (isGameRunning) {
            double dt = getDeltaT();
            ship.moveShip(dt);
            updateMeteors(dt);
        }
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
        this.setGameRunning(true);

        this.timer.start();
        startTime = System.currentTimeMillis();
        gui.setScreen(Screens.GAME);
    }

    public void pauseGame() {
        this.timer.stop();
        this.setGameRunning(false);
        gui.setScreen(Screens.PAUSE);
    }

    public void resumeGame() {
        this.setGameRunning(true);

        this.timer.start();
        gui.setScreen(Screens.GAME);
    }

    // ************ OTHER **************

    @Override
    public void actionPerformed(ActionEvent e) {
        updateView();
    }

}// class
