package Game.Util;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngleAbs() {
        return Math.atan(Math.abs(y / x));
    }

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

    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }
}
