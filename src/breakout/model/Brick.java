package breakout.model;

/*
 *   A brick for the rows of bricks
 */

import breakout.collision.AABB;

public class Brick extends Drawable {

    public static final double BRICK_WIDTH = 20;  // Default values, use in constructors, not directly
    public static final double BRICK_HEIGHT = 10;

    private int points;

    public Brick(double x, double y) {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    // region GETTERS N SETTERS

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    // endregion
}

