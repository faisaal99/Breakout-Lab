package breakout.model;

/*
 *   A brick for the rows of bricks
 */

public class Brick implements IPositionable {

    private double x, y;
    private final double width, height;

    private int points;

    public static final double BRICK_WIDTH = 20;  // Default values, use in constructors, not directly
    public static final double BRICK_HEIGHT = 10;

    public Brick(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = BRICK_WIDTH;
        this.height = BRICK_HEIGHT;
    }

    // region IMPLEMENTED METHODS

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    // endregion

    // region GETTERS n SETTERS

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    // endregion
}

