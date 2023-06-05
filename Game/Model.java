package Game;

import java.util.ArrayList;

import Game.GameObjects.Meteor;
import Game.GameObjects.Ship;
import Game.Util.Vector2D;
import Game.View.Screens;

public class Model {

    // variables
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
    private long prevTime;
    private long currentTime;

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

        isGameRunning = false;

        prevTime = 0;
        currentTime = System.currentTimeMillis();
    }

    public ArrayList<Meteor> getMeteors() {
        return meteors;
    }

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

    public Ship getShip() {
        return ship;
    }

    public double getDeltaT() {
        prevTime = currentTime;
        currentTime = System.currentTimeMillis();
        return (currentTime - prevTime) / 1000.0;
    }

    public double getTime() {
        return System.currentTimeMillis() / 1000.0;
    }

    public void setScreen(Screens sc) {
        gui.setScreen(sc);
    }
}// class
