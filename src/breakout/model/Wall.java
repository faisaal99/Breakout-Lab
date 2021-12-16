package breakout.model;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
        A wall for the ball to bounce
 */
public class Wall extends Drawable {

    public enum Dir { HORIZONTAL, VERTICAL }

    // Properties
    private final Dir direction;

    // Constructor
    public Wall(double x, double y, double width, double height, Dir direction) {
        super(x, y, width, height);
        this.direction = direction;
    }

    // region GETTERS N SETTERS

    public Dir getDirection() { return direction; }

    // endregion
}
