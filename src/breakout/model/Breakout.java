package breakout.model;


import java.util.ArrayList;
import java.util.List;

/*
 *  Overall all logic for the Breakout Game
 *  Model class representing the full game
 *  This class should use other objects delegate work.
 *
 *  NOTE: Nothing visual here
 *
 */
public class Breakout {

    public static final double GAME_WIDTH = 400;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.05; // Increase ball speed
    public static final long SEC = 1_000_000_000;  // Nanoseconds used by JavaFX

    private int nBalls = 5;
    int playerPoints;

    Ball b;

    // TODO Constructor that accepts all objects needed for the model
    public Breakout() {
        b = new Ball(50, 10, 20, 20);
    }

    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions
    double dX = 3;
    double dY = 1;
    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here



        if (isCollision(b.getX(), dX, b.maxXExtendable(GAME_WIDTH)))
            dX *= -1;

        if (isCollision(b.getY(), dY, b.maxYExtendable(GAME_HEIGHT)))
            dY *= -1;

        b.setX(b.getX() + dX);
        b.setY(b.getY() + dY);
    }

    // ----- Helper methods--------------

    double clamp(double value, double min, double max) {
        if (value > max)
            return max;
        else if (value < min)
            return min;

        return value;
    }

    boolean isCollision(double current, double delta, double max) {
        double newPos = current + delta;

        return newPos < 0 || newPos > max;
    }


    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> items = new ArrayList<>();
        items.add(b);
        return items;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public int getnBalls() {
        return nBalls;
    }




}
