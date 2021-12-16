package breakout.model;

import breakout.collision.AABB;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 * A Paddle for the Breakout game
 */
public class Paddle extends Drawable {

    // Default values, use in constructors, not directly
    public static final double PADDLE_WIDTH = 60;
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_SPEED = 5;

    // Constructor
    public Paddle(double x, double y) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

}
