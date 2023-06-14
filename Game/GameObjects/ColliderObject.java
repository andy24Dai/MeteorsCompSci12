package Game.GameObjects;

/*  ColliderObject
 *  Andy Dai
 *  June 12 2023
 *  object that can collide and move
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Util.GameConstants;
import Game.Util.Vector2D;

public class ColliderObject {

    private Vector2D position; // position of the object
    private Vector2D velocity; // velocity of the object

    protected BufferedImage icon; // icon of the object

    // constructor
    public ColliderObject(Vector2D pos, String imagePath) {
        position = pos;
        velocity = new Vector2D();

        setIcon(imagePath);
    }

    // ************* getters *************

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public int[] getIconSize() {
        return new int[] { icon.getWidth(), icon.getHeight() };
    }

    public double getVelocityAngle() {
        return velocity.getAngleAbs();
    }

    // returns leftmost x value of the icon when drawn
    public int getLeftX() {
        return (int) (position.getX() - getIconSize()[0] / 2);
    }

    // returns rightmost x value of the icon when drawn
    public int getRightX() {
        return (int) (position.getX() + getIconSize()[0] / 2);
    }

    // returns top y value of the icon when drawn
    public int getTopY() {
        return (int) (position.getY() - getIconSize()[1] / 2);
    }

    // returns bottom y value of the icon when drawn
    public int getBottomY() {
        return (int) (position.getY() + getIconSize()[1] / 2);
    }

    // ************* setters *************

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(double x, double y) {
        velocity.setX(x);
        velocity.setY(y);
    }

    // sets icon to specified image
    private void setIcon(String path) {
        // get Icon
        try {
            icon = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ************* other *************

    // adds displacement to position based on velocity and time
    public void updatePosition(double dt) {
        Vector2D deltaPos = new Vector2D();
        deltaPos.setX(Math.ceil(Math.abs(velocity.getX() * dt)) * Math.signum(velocity.getX()));
        deltaPos.setY(Math.ceil(Math.abs(velocity.getY() * dt)) * Math.signum(velocity.getY()));
        position.add(deltaPos);
    }

    // checks if objects are colliding
    public boolean isColliding(ColliderObject obj) {
        return this.getLeftX() <= obj.getRightX() &&
                this.getRightX() >= obj.getLeftX() && this.getTopY() <= obj.getBottomY() &&
                this.getBottomY() >= obj.getTopY();
    }

    // checks if object is in bounds in the y-axis
    public int isInBoundsY() {
        if (this.getTopY() <= 0) {
            return GameConstants.UP;
        } else if (this.getBottomY() >= GameConstants.DISPLAY_HEIGHT) {
            return GameConstants.DOWN;
        }
        return 0;
    }

    // checks if object is in bounds in the x-axis
    public int isInBoundsX() {
        if (this.getRightX() <= 0) {
            return GameConstants.LEFT;
        } else if (this.getLeftX() >= GameConstants.DISPLAY_WIDTH) {
            return GameConstants.RIGHT;
        }
        return 0;
    }

}// class
