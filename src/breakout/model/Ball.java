package breakout.model;

import breakout.collision.AABB;

/*
 *    A Ball for the Breakout game
 */

public class Ball implements IPositionable {

    // Properties
    private double x, y;                // Position
    private final double width, height; // Dimensions
    private final AABB aabb;            // Used for collision detection

    // Constructor
    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
        width = 20;
        height = 20;

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

    // region UTILITY METHODS

    // TODO These two methods are for experimentation and are most likely disappearing
    public double maxXExtendable(double windowWidth) { return windowWidth - width; }
    public double maxYExtendable(double windowHeight) { return windowHeight - width; }

    // TODO Hit detection
    public boolean hit(IPositionable p) {

        return true;
    }

    // endregion
}
