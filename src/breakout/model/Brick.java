package breakout.model;

/*
 *   A brick for the rows of bricks
 */

public class Brick implements IPositionable {

    private double x, y;
    private double width, height;

    private int points;

    public static final double BRICK_WIDTH = 20;  // Default values, use in constructors, not directly
    public static final double BRICK_HEIGHT = 10;

    public Brick(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // region IMPLEMENTED METHODS
    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }
    // endregion

    // region GETTERS n SETTERS
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    // endregion
}

