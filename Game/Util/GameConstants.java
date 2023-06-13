package Game.Util;

/*  GameConstants
 *  Andy Dai
 *  June 12 2023
 *  stores constants related to the game
 */

public class GameConstants {
    public static final int REFRESH_RATE_MILISECONDS = 10;
    public static final double METEOR_SPAWN_RATE_SECONDS = 0.25;

    public static final int SPEED_PIXEL_PER_SECOND = 700;
    public static final int ACCEL_PIXEL_PER_SECOND_SQUARED = 2000;
    public static final int DECCEL_PIXEL_PER_SECOND_SQUARED = 1500;

    public static final int METEOR_VY_MAX = 300;
    public static final int METEOR_VY_MIN = 50;
    public static final int METEOR_VX_MAX = -50;
    public static final int METEOR_VX_MIN = 50;

    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int UP = -1;
    public static final int DOWN = 1;

    public static final int DISPLAY_WIDTH = 1080;
    public static final int DISPLAY_HEIGHT = 720;

}
