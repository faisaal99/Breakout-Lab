package breakout.model;

import breakout.collision.AABB;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 * A Paddle for the Breakout game
 */
public class Paddle implements IPositionable {

    public static final double PADDLE_WIDTH = 60;  // Default values, use in constructors, not directly
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_SPEED = 5;

    // Properties
    private double x, y;
    private final double width, height;
    private AABB aabb;

    // Constructor
    public Paddle(double x, double y) {
        this.x = x;
        this.y = y;
        width = PADDLE_WIDTH;
        height = PADDLE_HEIGHT;

        aabb = new AABB(x, y, width, height);
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

    // region GETTERS N SETTERS

    public AABB getAABB() { return aabb; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    // endregion
}
