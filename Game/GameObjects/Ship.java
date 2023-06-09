package Game.GameObjects;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Game.Util.GameConstants;
import Game.Util.Vector2D;

public class Ship extends ColliderObject {

    // ************* variables *************

    private int accelX; // if ship is accelerating in X axis and direction (-1,0,1)
    private int accelY; // if ship is accelerating in Y axis and direction (-1,0,1)

    // ************* constructor *************
    public Ship(int x, int y) {
        // set shipPos
        super(new Vector2D(x, y), "Game/Sprites/eyeBot.png");

    }// constructor

    // ************* getters *************

    public int getAccelX() {
        return accelX;
    }

    public int getAccelY() {
        return accelY;
    }

    // ************* setters *************

    public void setAccelX(int accel) {
        accelX = accel;
    }

    public void setAccelY(int accel) {
        accelY = accel;
    }

    // ************* kinematics *************
    public void moveShip(double deltaT) {

        if (accelX != 0) {
            accelVx(accelX, deltaT);
        } else {
            deccelVx(deltaT);
        }

        if (isInBoundsX() != 0) {
            this.setPosition((isInBoundsX() == GameConstants.RIGHT) ? 0 + this.getIconSize()[0] / 2
                    : GameConstants.DISPLAY_WIDTH + this.getIconSize()[0] / 2, this.getPosition().getY());
        }

        if (isInBoundsY() == 0) {

            if (accelY != 0) {
                accelVy(accelY, deltaT);
            } else {
                deccelVy(deltaT);
            }
        }

        else {
            if (accelY == -isInBoundsY()) {
                accelVy(accelY, deltaT);
            } else {
                this.setVelocity(this.getVelocity().getX(), 0);
                this.setPosition(this.getPosition().getX(),
                        (isInBoundsY() == GameConstants.UP) ? 0 + this.getIconSize()[1] / 2
                                : GameConstants.DISPLAY_HEIGHT - this.getIconSize()[1] / 2);
            }
        }

        this.updatePosition(deltaT);

    }

    public void accelVx(int dir, double deltaT) {

        if (Math.abs(this.getVelocity().getX()) >= GameConstants.SPEED_PIXEL_PER_SECOND
                && Math.signum(this.getVelocity().getX()) == dir) {
            this.getVelocity().setX(dir * GameConstants.SPEED_PIXEL_PER_SECOND);
        } else {
            this.getVelocity().setX(
                    (int) (this.getVelocity().getX() + dir * deltaT * GameConstants.ACCEL_PIXEL_PER_SECOND_SQUARED));
        }

    }

    public void deccelVx(double deltaT) {
        double deccel = GameConstants.DECCEL_PIXEL_PER_SECOND_SQUARED
                * Math.cos(this.getVelocity().getAngleAbs()) * deltaT;

        if (Math.abs(this.getVelocity().getX()) >= deccel) {
            this.getVelocity().setX((int) (this.getVelocity().getX()
                    - Math.signum(this.getVelocity().getX()) * deccel));
        } else {
            this.getVelocity().setX(0);
        }
    }

    public void accelVy(int dir, double deltaT) {
        if (Math.abs(this.getVelocity().getY()) >= GameConstants.SPEED_PIXEL_PER_SECOND
                && Math.signum(this.getVelocity().getY()) == dir) {
            this.getVelocity().setY(dir * GameConstants.SPEED_PIXEL_PER_SECOND);
        } else {
            this.getVelocity().setY(
                    (int) (this.getVelocity().getY() + dir * deltaT * GameConstants.ACCEL_PIXEL_PER_SECOND_SQUARED));
        }
    }

    public void deccelVy(double deltaT) {
        double deccel = GameConstants.DECCEL_PIXEL_PER_SECOND_SQUARED
                * Math.sin(this.getVelocity().getAngleAbs()) * deltaT;

        if (Math.abs(this.getVelocity().getY()) >= deccel) {
            this.getVelocity().setY((int) (this.getVelocity().getY()
                    - Math.signum(this.getVelocity().getY()) * deccel));
        } else {
            this.getVelocity().setY(0);
        }
    }

    // ************* other *************
    public void printInfo() {
        System.out.printf("   V: %2s P: %4s Angle: %4.2f In Bounds: %4d\n", this.getVelocity(), this.getPosition(),
                this.getVelocity().getAngleAbs(), isInBoundsX());
    }

}// class
