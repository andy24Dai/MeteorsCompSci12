package Game;

import Game.GameObjects.Ship;

public class Model {

    // variables
    private View gui;
    private Ship ship;

    private long prevTime;
    private long currentTime;

    public Model() {
        ship = new Ship(0, 0);

        prevTime = 0;
        currentTime = System.currentTimeMillis();
    }

    // sets gui reference
    public void setGUI(View g) {
        gui = g;
    }

    // updates gui
    public void updateView() {
        double dt = getDeltaT();
        ship.moveShip(dt);
        gui.update();
    }

    public Ship getShip() {
        return ship;
    }

    public double getDeltaT() {
        prevTime = currentTime;
        currentTime = System.currentTimeMillis();
        return (currentTime - prevTime) / 1000.0;
    }

}// class
