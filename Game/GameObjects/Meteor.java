package Game.GameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import Game.Util.GameConstants;
import Game.Util.Vector2D;

public class Meteor extends ColliderObject {

    public Meteor(int x, int y) {
        super(new Vector2D(x, y), "Game/Sprites/meteor.png");
        this.setVelocity(0, 200);
        rotateImage();
    }

    @Override
    public int isInBoundsY() {
        if (this.getBottomY() <= 0) {
            return GameConstants.UP;
        } else if (this.getTopY() >= GameConstants.DISPLAY_HEIGHT) {
            return GameConstants.DOWN;
        }
        return 0;
    }

    // adds random rotation to sprite
    public void rotateImage() {
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.random() * 2 * Math.PI, getIconSize()[0] / 2, getIconSize()[1] / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        icon = op.filter(icon, null);

    }
}
