package Game;

/*  Model
 *  Andy Dai
 *  June 12 2023
 *  stores and updates information about the game
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.Timer;

import Game.GameObjects.Meteor;
import Game.GameObjects.Ship;
import Game.Util.GameConstants;
import Game.Util.Prompt;
import Game.View.Screens;

public class Model implements ActionListener {

    // ************ VARIABLES **************

    private static Model mModel; // singleton instance
    private View gui;

    // game objects
    private Ship ship;
    private ArrayList<Meteor> meteors;
    private ArrayList<Meteor> removeMeteors;

    // game states
    private int roundNum; // number of rounds to play
    private int currentRound;

    // time
    private double timeSurvived;
    private Timer timer;
    private double timeSinceLastMeteor;
    private long prevTime;
    private long currentTime;

    // game Scores
    private double[] times; // timer survived per round
    private int[] meteorsDodged; // meteors dodged per round

    // ************ METHODS **************

    // get singleton instance
    public static Model getInstance() {
        if (mModel == null) {
            mModel = new Model();
        }
        return mModel;
    }

    // constructor
    private Model() {
        ship = new Ship(0, 0);

        meteors = new ArrayList<Meteor>();
        removeMeteors = new ArrayList<Meteor>();

        timer = new Timer(GameConstants.REFRESH_RATE_MILISECONDS, this);
        timer.stop();

        timeSurvived = 0;
        timeSinceLastMeteor = 0;

        currentRound = 1;

        prevTime = 0;
        currentTime = System.currentTimeMillis();
    }// constructor

    // ************ GETTERS **************
    // returns array of meteors
    public ArrayList<Meteor> getMeteors() {
        return meteors;
    }

    // returns ship object
    public Ship getShip() {
        return ship;
    }

    // returns time past between frames
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

    public int getRoundNum() {
        return roundNum;
    }

    public double[] getTimes() {
        return times;
    }

    //
    public int[] getMeteorsDodged() {
        return meteorsDodged;
    }

    // ************ SETTERS **************

    // sets gui reference
    public void setGUI(View g) {
        gui = g;
    }

    // ************ UPDATE **************

    // updates gui
    public void updateView() {
        double dt = getDeltaT();
        ship.moveShip(dt);
        updateMeteors(dt);
        checkMeteorCollisions();
        spawnMeteors(dt);

        timeSurvived += dt;

        gui.update();
    }

    // updates position of all meteors
    private void updateMeteors(double dt) {
        for (Meteor meteor : meteors) {
            meteor.updatePosition(dt);
        }
    }

    // checks meteors for collision with ship, deletes meteors if not in view
    private void checkMeteorCollisions() {
        for (Meteor meteor : meteors) {
            if (meteor.isInBoundsY() != 0) { // if meteor is out of bounds
                removeMeteors.add(meteor);
                meteorsDodged[currentRound - 1]++;
            } // if

            else if (ship.isColliding(meteor)) {
                this.endRound();
            } // else if
        } // for

        // remove meteors
        for (Meteor meteor : removeMeteors) {
            meteors.remove(meteor);
        } // for
    }// checkMeteorCollisions

    // spawns meteor with a random velocity at a specified rate
    private void spawnMeteors(double dt) {
        if (timeSurvived > 2 && timeSinceLastMeteor > GameConstants.METEOR_SPAWN_RATE_SECONDS) {
            Meteor newMeteor = new Meteor((int) (Math.random() * (GameConstants.DISPLAY_WIDTH)), 0);
            double vy = Math.random() * (GameConstants.METEOR_VY_MAX - GameConstants.METEOR_VY_MIN)
                    + GameConstants.METEOR_VY_MIN;
            double vx = Math.random() * (GameConstants.METEOR_VX_MAX - GameConstants.METEOR_VX_MIN)
                    + GameConstants.METEOR_VX_MIN;
            newMeteor.setVelocity(vx, vy);
            meteors.add(newMeteor);
            timeSinceLastMeteor = 0;
        } // if
        else {
            timeSinceLastMeteor += dt;
        } // else
    }// spawn meteors

    // ************ GAME STATE CONTROL **************

    // starts game
    public void startGame(int numRounds) {
        resetGame();
        roundNum = numRounds;
        times = new double[numRounds];
        meteorsDodged = new int[numRounds];
        gui.setScreen(Screens.GAME);
        this.timer.start();
    }

    // pauses game
    public void pauseGame() {
        gui.setScreen(Screens.PAUSE);
        this.timer.stop();

    }

    // resumes game
    public void resumeGame() {
        this.currentTime = System.currentTimeMillis();
        gui.setScreen(Screens.GAME);
        this.timer.start();
    }

    // ends game
    public void endGame() {
        gui.setScreen(Screens.SUMMARY);
        this.timer.stop();
        outputData();
    }

    // ends round
    public void endRound() {
        gui.setScreen(Screens.END);
        times[currentRound - 1] = timeSurvived;
        this.timer.stop();
    }

    // continues game if there are more rounds or ends game
    public void continueGame() {
        if (currentRound < roundNum) {
            this.resetGame();
            this.resumeGame();
            currentRound++;
        } else {
            endGame();
        }
    }

    // restarts game
    public void restartGame() {
        this.resetGame();
        timeSurvived = 0;
        timeSinceLastMeteor = 0;

        currentRound = 1;
        gui.setScreen(Screens.START);
    }

    // resets game info for new round
    private void resetGame() {
        timeSurvived = 0;
        this.currentTime = System.currentTimeMillis();
        ship.reset();
        meteors.clear();
    }

    // ************ OTHER **************

    @Override
    public void actionPerformed(ActionEvent e) {
        updateView();
    }

    // writes game data to output.txt
    public void outputData() {

        PrintWriter writer = Prompt.getPrintWriter("Game/output.txt");

        writer.println("******************** Game Summary ********************\n");

        // round data
        for (int i = 0; i < roundNum; i++) {
            writer.println("Round " + (i + 1) + ":");
            writer.printf("Time survived: %.2f s\n", times[i]);
            writer.printf("Meteors Dodged: %d\n\n", meteorsDodged[i]);
        }

        // average data
        writer.println("Average:");
        writer.printf("Time survived: %.2f s\n", getAverage(times));
        writer.printf("Meteors Dodged: %d\n\n", getAverage(meteorsDodged));

        writer.close();
    }// outputData

    // gets average value of arr
    public double getAverage(double[] arr) {
        double avg = 0;
        for (double num : arr) {
            avg += num;
        }

        avg /= arr.length;

        return avg;
    }

    // gets average value of arr
    public int getAverage(int[] arr) {
        int avg = 0;
        for (int num : arr) {
            avg += num;
        }

        avg /= arr.length;

        return avg;
    }

}// class
