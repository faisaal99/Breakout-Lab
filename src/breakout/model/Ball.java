package breakout.model;

import java.util.Random;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 *    A Ball for the Breakout game
 */

public class Ball extends Drawable {

    // Util
    Random r;

    // Properties
    private static final double STARTING_SPEED = 3;
    private double dx, dy;              // The delta to move in x and y
    private double mSpeed;              // The speed of the ball

    // Constructor
    public Ball() {
        super(0, 0, 15, 15);

        r = new Random();
        mSpeed = STARTING_SPEED;

        newRandomStartingRot();
    }

    // region GETTERS N SETTERS

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

    // When spawned, pick a random location and rotation to begin
    public void newRandomStartingRot() {
        final int DISTANCE_FROM_BOTTOM = 50;
        final double MAX_ANGLE_RANGE = Math.toRadians(120);

        // Center of width, above the paddle
        x = GAME_WIDTH / 2 - width;
        y = GAME_HEIGHT - DISTANCE_FROM_BOTTOM;

        // Random angle
        double angle = r.nextDouble(MAX_ANGLE_RANGE);
        angle += (Math.PI - angle) / 2;

        mSpeed = STARTING_SPEED;
        setNewVelocityDirection(angle);
    }

    // Set a new velocity direction to the given angle
    public void setNewVelocityDirection(double angle) {
        dx = mSpeed * Math.cos(angle);
        dy = mSpeed * Math.sin(angle + Math.PI);
    }

    // Change the speed of the ball
    // >> factor = 1 doesn't change anything
    public void increaseSpeedByFactor(double factor) {
        mSpeed *= factor;
        dx *= factor;
        dy *= factor;
    }

    // endregion
}
