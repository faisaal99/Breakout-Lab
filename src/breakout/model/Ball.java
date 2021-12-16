package breakout.model;

import java.util.Random;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 *    A Ball for the Breakout game
 */

public class Ball implements IPositionable {

    // Util
    Random r;

    // Properties
    private double x, y;                // Position
    private final double width, height; // Dimensions
    private double dx, dy;              // The delta to move in x and y
    private double mSpeed = 2;          // The speed of the ball
    private double mAngle = 0;          // The angle of the vector

    // Constructor
    public Ball() {
        width = 15;
        height = 15;

        r = new Random();

        startingRot();
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

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setDx(double dx) { this.dx = dx; }
    public void setDy(double dy) { this.dy = dy; }

    public double getDx() { return dx; }
    public double getDy() { return dy; }

    // endregion

    // region UTILITY METHODS

    // Moves the ball
    public void moveBall() {
        x += dx;
        y += dy;
    }

    // Set the delta of the ball
    public void setMoveDelta(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // When spawned, pick a random location and rotation to begin
    public void startingRot() {
        final int DISTANCE_FROM_BOTTOM = 50;
        final double MAX_ANGLE_RANGE = Math.toRadians(120);

        x = GAME_WIDTH / 2 - width;
        y = GAME_HEIGHT - DISTANCE_FROM_BOTTOM;

        double angle = r.nextDouble(MAX_ANGLE_RANGE);
        angle += (Math.PI - angle) / 2;

        mAngle = angle;

        dx = mSpeed * Math.cos(angle);
        dy = mSpeed * Math.sin(angle);
        dy *= -1;
    }

    public void setNewVelocityDirection(double angle) {
        mAngle = angle;

        dx = mSpeed * Math.cos(angle);
        dy = mSpeed * Math.sin(angle);
        dy *= -1;
    }

    public void increaseSpeedByFactor(double factor) {
        mSpeed *= factor;
        dx *= factor;
        dy *= factor;
    }

    // endregion
}
