package breakout.model;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
        A wall for the ball to bounce
 */
public class Wall implements IPositionable {

    public enum Dir { HORIZONTAL, VERTICAL }

    // Properties
    private final Dir direction;
    private final double x, y;
    private final double width, height;

    // Constructor
    public Wall(double x, double y, Dir direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;

        // Wall thickness
        if (direction == Dir.HORIZONTAL) {
            width = GAME_WIDTH;
            height = 10;
        } else { // VERTICAL
            width = 10;
            height = GAME_HEIGHT;
        }
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

    public Dir getDirection() { return direction; }

    // endregion
}
