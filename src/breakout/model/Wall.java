package breakout.model;

/*
        A wall for the ball to bounce
 */
public class Wall implements IPositionable {

    public enum Dir {
        HORIZONTAL, VERTICAL;
    }

    // Constructor
    // TODO

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

}
