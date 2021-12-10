package breakout.model;

/*
        A wall for the ball to bounce
 */
public class Wall implements IPositionable {

    public enum Dir {
        HORIZONTAL, VERTICAL;
    }

    // Properties
    private final Dir direction;
    private final double x, y;
    private final double width = 0, height = 0;

    // Constructor
    public Wall(double x, double y, Dir direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

}
