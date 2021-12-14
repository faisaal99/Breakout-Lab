package breakout.model;

import breakout.collision.AABB;
import static breakout.model.Breakout.GAME_WIDTH;
import static breakout.model.Breakout.GAME_HEIGHT;

import java.util.Random;

/*
 *    A Ball for the Breakout game
 */

public class Ball implements IPositionable {

    // Util
    Random r;

    // Properties
    private double x, y;                // Position
    private final double width, height; // Dimensions
    private final AABB aabb;            // Used for collision detection
    private double dx, dy;              // The delta to move in x and y

    // Constructor
    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
        width = 15;
        height = 15;

        aabb = new AABB(x, y, width, height);

        r = new Random();

        randomPosAndRot();
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

    public void setDx(double dx) { this.dx = dx; }
    public void setDy(double dy) { this.dy = dy; }

    public double getDx() { return dx; }
    public double getDy() { return dy; }

    // endregion

    // region UTILITY METHODS

    // TODO Hit detection
    public boolean hit(IPositionable p) {

        return true;
    }

    // When spawned, pick a random location and rotation to begin
    public void randomPosAndRot() {
        // Choosing random location to spawn
        final int MARGIN_WALLS = 10; // The minimum margin to keep from each side
        final int DISTANCE_FROM_BOTTOM = 50;
        int xPos = r.nextInt((int) (GAME_WIDTH - 2*MARGIN_WALLS - width)) + MARGIN_WALLS;
        int yPos = (int) GAME_HEIGHT - DISTANCE_FROM_BOTTOM;

        x = xPos;
        y = yPos;

        // Choosing random rotation | It will always point upward
        final int BEGIN_X = 2;
        final int BEGIN_Y = 2;
        dx = r.nextInt(BEGIN_X) + 1;
        if (r.nextBoolean()) // Randomly select left or right movement
            dx *= -1;

        dy = (r.nextInt(BEGIN_Y) + 1) * -1; // Multiply with -1 to point it upward
    }

    // endregion
}
