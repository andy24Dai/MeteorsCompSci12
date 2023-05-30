package Game.Util;

public class BoxCollider {
    private int[] topLeft;
    private int[] dimension;
    private boolean isHit;

    public BoxCollider(int[] cornerPos, int[] dim) {
        topLeft = cornerPos;
        dimension = dim;
        isHit = false;
    }

}
