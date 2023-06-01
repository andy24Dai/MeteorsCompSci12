package Game.GameObjects;

import Game.Util.Vector2D;

public class Meteor extends ColliderObject {

    public Meteor(int x, int y) {
        super(new Vector2D(x, y), "Game/Sprites/meteor1.png");
        this.setVelocity(0, 0);
    }

}
