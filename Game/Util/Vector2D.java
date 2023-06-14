package Game.Util;

/*  Vector2D
 *  Andy Dai
 *  June 12 2023
 *  stores information and methods relating to 2D vector operations
 */

public class Vector2D {
    private double x;
    private double y;

    // constructors
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0, 0);
    }

    // ********* getters *********
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // returns the angle of the vector ranging between 0 and pi radians
    public double getAngleAbs() {
        return Math.atan(Math.abs(y / x));
    }

    // ********* setters ********* 
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXY(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    // ********* vector operations ********* 
    public void add(Vector2D v) {
        this.setX(this.getX() + v.getX());
        this.setY(this.getY() + v.getY());
    }

    public void subtract(Vector2D v) {
        this.setX(this.getX() - v.getX());
        this.setY(this.getY() - v.getY());
    }

    public void scale(double n) {
        this.x *= n;
        this.y *= n;
    }

    // ********* other ********* 
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "(" + x + ", " + y + ")";
    }
} // class
