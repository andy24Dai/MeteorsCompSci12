package Game.GameObjects;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Game.Util.GameConstants;

public class Ship {

    // ************* variables *************
    private int[] shipPos = new int[2]; // position of ship
    private int[] shipVel = new int[2]; // velocity of the ship

    private BufferedImage image; // ship icon
    private int[] imageSize = new int[2]; // size of icon

    private int accelX; // if ship is accelerating in X axis and direction (-1,0,1)
    private int accelY; // if ship is accelerating in Y axis and direction (-1,0,1)

    // ************* constructor *************
    public Ship(int x, int y) {
        // set shipPos
        shipPos = new int[] { x, y };

        // get shipIcon
        try {
            image = ImageIO.read(new File("Game/Sprites/eyeBot.png"));
            imageSize[0] = image.getWidth();
            imageSize[1] = image.getHeight();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }// constructor

    // ************* getters *************
    public int[] getShipPos() {
        return shipPos;
    }

    public int[] getShipVel() {
        return shipVel;
    }

    public int getShipVelX() {
        return shipVel[0];
    }

    public int getShipVelY() {
        return shipVel[1];
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getImageSize() {
        return imageSize;
    }

    public int getAccelX() {
        return accelX;
    }

    public int getAccelY() {
        return accelY;
    }

    // ************* setters *************
    public void setShipPos(int x, int y) {
        shipPos[0] = x;
        shipPos[1] = y;
    }

    public void setShipVel(int vx, int vy) {
        shipVel[0] = vx;
        shipVel[1] = vy;
    }

    public void setShipVelX(int vx) {
        shipVel[0] = vx;
    }

    public void setShipVelY(int vy) {
        shipVel[1] = vy;
    }

    public void setAccelX(int accel) {
        accelX = accel;
    }

    public void setAccelY(int accel) {
        accelY = accel;
    }

    // ************* kinematics *************
    public void moveShip(double deltaT) {
        if (isInBoundsY()) {
            if (accelX != 0) {
                accelVx(accelX, deltaT);
            } else {
                deccelVx(deltaT);
            }

            if (accelY != 0) {
                accelVy(accelY, deltaT);
            } else {
                deccelVy(deltaT);
            }

            shipPos[0] += Math.ceil(Math.abs(shipVel[0] * deltaT)) * Math.signum(shipVel[0]);
            shipPos[1] += Math.ceil(Math.abs(shipVel[1] * deltaT)) * Math.signum(shipVel[1]);
        }

    }

    public void accelVx(int dir, double deltaT) {

        if (Math.abs(getShipVelX()) >= GameConstants.SPEED_PIXEL_PER_SECOND && Integer.signum(getShipVelX()) == dir) {
            setShipVelX(dir * GameConstants.SPEED_PIXEL_PER_SECOND);
        } else {
            setShipVelX((int) (getShipVelX() + dir * deltaT * GameConstants.ACCEL_PIXEL_PER_SECOND_SQUARED));
        }

    }

    public void deccelVx(double deltaT) {
        double deccel = GameConstants.DECCEL_PIXEL_PER_SECOND_SQUARED
                * Math.cos(getAngle()) * deltaT;

        if (Math.abs(getShipVelX()) >= deccel) {
            setShipVelX((int) (getShipVelX()
                    - Integer.signum(getShipVelX()) * deccel));
        } else {
            setShipVelX(0);
        }
    }

    public void accelVy(int dir, double deltaT) {
        if (Math.abs(getShipVelY()) >= GameConstants.SPEED_PIXEL_PER_SECOND && Integer.signum(getShipVelY()) == dir) {
            setShipVelY(dir * GameConstants.SPEED_PIXEL_PER_SECOND);
        } else {
            setShipVelY((int) (getShipVelY() + dir * deltaT * GameConstants.ACCEL_PIXEL_PER_SECOND_SQUARED));
        }
    }

    public void deccelVy(double deltaT) {
        double deccel = GameConstants.DECCEL_PIXEL_PER_SECOND_SQUARED
                * Math.sin(getAngle()) * deltaT;

        if (Math.abs(getShipVelY()) >= deccel) {
            setShipVelY((int) (getShipVelY()
                    - Integer.signum(getShipVelY()) * deccel));
        } else {
            setShipVelY(0);
        }
    }

    // ************* other *************
    public void printInfo() {
        System.out.printf("   V: %2d , %2d P: %4d , %4d Angle: %4.2f In Bounds: %4b\n", shipVel[0], shipVel[1],
                shipPos[0], shipPos[1],
                getAngle(), isInBoundsY());
    }

    private double getAngle() {
        return Math.atan(Math.abs((double) getShipVelY() / (double) getShipVelX()));
    }

    private boolean isInBoundsY() {
        return getShipPos()[1] + imageSize[1] / 2 <= 720 && getShipPos()[1] - imageSize[1] / 2 >= 0;
    }

}// class
