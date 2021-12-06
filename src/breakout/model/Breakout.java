package breakout.model;


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
    public static final long SEC = 1_000_000_000;  // Nano seconds used by JavaFX

    private int nBalls = 5;
    int playerPoints;

    // TODO Constructor that accepts all objects needed for the model


    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here
    }

    // ----- Helper methods--------------

    // Used for functional decomposition



    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        return null;  // TODO return all objects to be rendered by GUI
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public int getnBalls() {
        return nBalls;
    }




}
