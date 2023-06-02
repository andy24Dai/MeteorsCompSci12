package Game;

import java.util.ArrayList;

import Game.GameObjects.Meteor;
import Game.GameObjects.Ship;
import Game.Util.Vector2D;

public class Model {

    // variables
    private View gui;
    private Ship ship;
    private ArrayList<Meteor> meteors;

    private long prevTime;
    private long currentTime;

    public Model() {
        ship = new Ship(0, 0);

        meteors = new ArrayList<Meteor>();
        meteors.add(new Meteor(200, 100));
        meteors.add(new Meteor(600, 100));

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

    // updates gui
    public void updateView() {
        double dt = getDeltaT();
        ship.moveShip(dt);

        System.out.print(ship.getRightX());
        updateMeteors(dt);
        // System.out.print(dt + " ");
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

}// class
