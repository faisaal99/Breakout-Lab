package breakout.model;

import java.util.Random;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 *    A Ball for the Breakout game
 */

public class Ball implements IPositionable {

    // Properties - not final!!
    private double x, y;                // Position
    private final double width, height; // Dimensions

    // Constructor
    public Ball(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Ball(double x, double y) {
        this(x, y, 20, 20);
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

    // region SETTERS
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
