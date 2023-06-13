package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
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
    private ArrayList<Meteor> removeMeteors;

    // game states
    private int roundNum;
    private int currentRound;

    // time
    private double timeSurvived;
    private Timer timer;
    private double timeSinceLastMeteor;
    private long prevTime;
    private long currentTime;

    // game Scores
    private double[] times;
    private int[] meteorsDodged;

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
        removeMeteors = new ArrayList<Meteor>();

        timer = new Timer(GameConstants.REFRESH_RATE_MILISECONDS, this);
        timer.stop();

        timeSurvived = 0;
        timeSinceLastMeteor = 0;

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

    public int getRoundNum() {
        return roundNum;
    }

    public Screens getCurrentScreen() {
        return gui.getCurrentScreen();
    }

    public double[] getTimes() {
        return times;
    }

    public int[] getMeteorsDodged() {
        return meteorsDodged;
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
        checkMeteorCollisions();
        spawnMeteors(dt);

        timeSurvived += dt;

        gui.update();
    }

    private void updateMeteors(double dt) {
        for (Meteor meteor : meteors) {
            meteor.updatePosition(dt);
        }
    }

    private void checkMeteorCollisions() {
        for (Meteor meteor : meteors) {
            if (meteor.isInBoundsY() != 0) {
                removeMeteors.add(meteor);
                meteorsDodged[currentRound - 1]++;
            } else if (ship.isColliding(meteor)) {
                this.endRound();
            }
        }

        for (Meteor meteor : removeMeteors) {
            meteors.remove(meteor);
        }
    }

    private void spawnMeteors(double dt) {
        if (timeSurvived > 3 && timeSinceLastMeteor > GameConstants.METEOR_SPAWN_RATE_SECONDS) {
            Meteor newMeteor = new Meteor((int) (Math.random() * (GameConstants.DISPLAY_WIDTH)), 0);
            double vy = Math.random() * (200) + 100;
            double vx = Math.random() * (20) - 10;
            newMeteor.setVelocity(vx, vy);
            meteors.add(newMeteor);
            timeSinceLastMeteor = 0;
        } else {
            timeSinceLastMeteor += dt;
        }

    }

    // ************ GAME STATE CONTROL **************

    public void startGame(int numRounds) {
        resetGame();
        this.setRoundNum(numRounds);
        times = new double[numRounds];
        meteorsDodged = new int[numRounds];
        gui.setScreen(Screens.GAME);
        this.timer.start();
    }

    public void pauseGame() {
        gui.setScreen(Screens.PAUSE);
        this.timer.stop();

    }

    public void resumeGame() {
        this.currentTime = System.currentTimeMillis();
        gui.setScreen(Screens.GAME);
        this.timer.start();
    }

    public void endGame() {
        gui.setScreen(Screens.SUMMARY);
        this.timer.stop();
        outputData();
    }

    public void endRound() {
        gui.setScreen(Screens.END);
        times[currentRound - 1] = timeSurvived;
        this.timer.stop();
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

    public void restartGame() {
        this.resetGame();
        timeSurvived = 0;
        timeSinceLastMeteor = 0;

        currentRound = 1;
        gui.setScreen(Screens.START);
    }

    private void resetGame() {
        timeSurvived = 0;
        this.currentTime = System.currentTimeMillis();
        ship.setPosition(GameConstants.DISPLAY_WIDTH / 2, GameConstants.DISPLAY_HEIGHT / 2);
        ship.setVelocity(0, 0);
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
    }

    public double getAverage(double[] arr) {
        double avg = 0;
        for (double num : arr) {
            avg += num;
        }

        avg /= arr.length;

        return avg;
    }

    public int getAverage(int[] arr) {
        int avg = 0;
        for (int num : arr) {
            avg += num;
        }

        avg /= arr.length;

        return avg;
    }

}// class
