package Game.GameObjects;

/*  Ship
 *  Andy Dai
 *  June 12 2023
 *  ship object
 */

import Game.Util.GameConstants;
import Game.Util.Vector2D;

public class Ship extends ColliderObject {

    // ************* variables *************

    private int accelX; // if ship is accelerating in X axis and direction (-1,0,1)
    private int accelY; // if ship is accelerating in Y axis and direction (-1,0,1)

    // ************* constructor *************
    public Ship(int x, int y) {
        // set shipPos
        super(new Vector2D(x, y), "Game/Sprites/ship.png");

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
        // ----------- x -----------
        if (accelX != 0) {
            accelVx(accelX, deltaT);
        } // if
        else {
            deccelVx(deltaT);
        } // else

        // set ship pos to the opposite side when out of bounds x
        if (isInBoundsX() != 0) { // if ship is out of bounds x
            this.setPosition((isInBoundsX() == GameConstants.RIGHT) ? 0 + this.getIconSize()[0] / 2
                    : GameConstants.DISPLAY_WIDTH + this.getIconSize()[0] / 2, this.getPosition().getY());
        } // if

        // ----------- y -----------

        // set ship pos to along the edge of the screen if the ship goes out of bounds
        if (isInBoundsY() == 0) { // if ship is in bounds y
            if (accelY != 0) {
                accelVy(accelY, deltaT);
            } else {
                deccelVy(deltaT);
            }
        } // if

        else { // if ship is out of bounds y
            if (accelY == -isInBoundsY()) {
                accelVy(accelY, deltaT);
            } else {
                this.setVelocity(this.getVelocity().getX(), 0);
                this.setPosition(this.getPosition().getX(),
                        (isInBoundsY() == GameConstants.UP) ? 0 + this.getIconSize()[1] / 2
                                : GameConstants.DISPLAY_HEIGHT - this.getIconSize()[1] / 2);
            }
        } // else

        // ----------- update -----------

        this.updatePosition(deltaT);

    } // moveShip

    // accelerates ship in the x direction
    public void accelVx(int dir, double deltaT) {

        if (Math.abs(this.getVelocity().getX()) >= GameConstants.SPEED_PIXEL_PER_SECOND
                && Math.signum(this.getVelocity().getX()) == dir) {
            this.getVelocity().setX(dir * GameConstants.SPEED_PIXEL_PER_SECOND);
        } else {
            this.getVelocity().setX(
                    (int) (this.getVelocity().getX() + dir * deltaT * GameConstants.ACCEL_PIXEL_PER_SECOND_SQUARED));
        }

    }

    // deccelerates ship in the x direction
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

    // accelerates ship in the y direction
    public void accelVy(int dir, double deltaT) {
        if (Math.abs(this.getVelocity().getY()) >= GameConstants.SPEED_PIXEL_PER_SECOND
                && Math.signum(this.getVelocity().getY()) == dir) {
            this.getVelocity().setY(dir * GameConstants.SPEED_PIXEL_PER_SECOND);
        } else {
            this.getVelocity().setY(
                    (int) (this.getVelocity().getY() + dir * deltaT * GameConstants.ACCEL_PIXEL_PER_SECOND_SQUARED));
        }
    }

    // deccelerates ship in the y direction
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

    // prints debug ship info
    public void printInfo() {
        System.out.printf("   V: %2s P: %4s Angle: %4.2f In Bounds: %4d\n", this.getVelocity(), this.getPosition(),
                this.getVelocity().getAngleAbs(), isInBoundsX());
    }

    // resets ship position and velocity
    public void reset() {
        this.setPosition(GameConstants.DISPLAY_WIDTH / 2, GameConstants.DISPLAY_HEIGHT / 2);
        this.setVelocity(new Vector2D());
        this.accelX = 0;
        this.accelY = 0;
    }

}// class
