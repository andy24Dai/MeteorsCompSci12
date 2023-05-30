package Game.GameObjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Util.Vector2D;

public class ColliderObject {
    private Vector2D position;
    private Vector2D velocity;

    private BufferedImage icon;

    public ColliderObject(Vector2D pos, String imagePath) {
        position = pos;
        setIcon(imagePath);
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void updatePosition(double dt) {
        Vector2D deltaPos = new Vector2D();
        deltaPos.setX(Math.ceil(Math.abs(velocity.getX() * dt)) * Math.signum(velocity.getX()));
        deltaPos.setY(Math.ceil(Math.abs(velocity.getY() * dt)) * Math.signum(velocity.getY()));
        position.add(deltaPos);
    }

    public double getVelocityAngle() {
        return velocity.getAngleAbs();
    }

    private void setIcon(String path) {
        // get Icon
        try {
            icon = ImageIO.read(new File(path)); // "Game/Sprites/eyeBot.png"
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
