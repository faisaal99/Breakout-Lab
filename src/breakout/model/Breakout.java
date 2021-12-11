package breakout.model;


import breakout.event.EventBus;
import breakout.event.IEventHandler;
import breakout.event.ModelEvent;

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
public class Breakout implements IEventHandler {

    public static final double GAME_WIDTH = 400;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.05; // Increase ball speed
    public static final long SEC = 1_000_000_000;        // Nanoseconds used by JavaFX

    private int nBalls = 5;
    int playerPoints;

    // All objects needed for the model
    Ball ball;
    Paddle paddle;
    List<Wall> walls;
    List<Brick> bricks;



    // TODO Constructor that accepts all objects needed for the model
    // TODO -> THAT WOULD BE : Ball | Brick | Paddle | Wall
    public Breakout(Ball ball, Paddle paddle, List<Wall> walls, List<Brick> bricks) {
        // TODO
        this.ball = ball;
        this.paddle = paddle;
        this.walls = walls;
        this.bricks = bricks;

        // Register for events from BreakoutGUI
        EventBus.INSTANCE.register(this);
    }

    // region  GAME LOGIC

    // To avoid multiple collisions
    private long timeForLastHit;

    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here

    }

    // endregion

    // region USED BY -> GUI

    public List<IPositionable> getPositionables() {
        List<IPositionable> items = new ArrayList<>();

        items.addAll(walls);
        items.addAll(bricks);
        items.add(ball);
        items.add(paddle);

        return items;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public int getNBalls() {
        return nBalls;
    }

    // endregion

    @Override
    public void onModelEvent(ModelEvent me) {
        System.out.println("Event occurred");
    }
}
