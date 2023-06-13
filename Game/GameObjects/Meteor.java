package Game.GameObjects;

/*  Meteor
 *  Andy Dai
 *  June 12 2023
 *  meteor object
 */

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import Game.Util.GameConstants;
import Game.Util.Vector2D;

public class Meteor extends ColliderObject {

    // constructor
    public Meteor(int x, int y) {
        super(new Vector2D(x, y), "Game/Sprites/meteor.png");
        rotateImage();
    }

    // in bounds but only true when the icon is fully off screen
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

}// class
