package breakout.model;


import breakout.collision.AABB;
import breakout.collision.Shift;
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
public class Breakout {

    public enum PaddleMovement {MOVE_LEFT, MOVE_RIGHT, STOP_PADDLE}

    public static final double GAME_WIDTH = 400;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.05; // Increase ball speed
    public static final long SEC = 1_000_000_000;        // Nanoseconds used by JavaFX

    // Properties
    private int nBalls = 5; // Number of available balls
    int playerPoints;       // Self-explanatory
    int paddleVel = 0;      // Paddle velocity (negative value means a left-ward direction)

    // All objects needed for the model
    Ball ball;
    Paddle paddle;
    List<Wall> walls;
    List<Brick> bricks;

    public Breakout(Ball ball, Paddle paddle, List<Wall> walls, List<Brick> bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.walls = walls;
        this.bricks = bricks;
    }

    // region  GAME LOGIC

    // To avoid multiple collisions
    private long timeForLastHit;

    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here

        // Do some kind of collision detection

        updatePaddleMovement();

        collisionDetection();
        moveBall();
    }

    // Updates the x-value of the paddle, after delta is set.
    private void updatePaddleMovement() {
        paddle.setX(paddle.getX() + paddleVel);
    }

    // Moves the ball in  the given dx and dy
    private void moveBall() {
        double x = ball.getX();
        double y = ball.getY();
        double dx = ball.getDx();
        double dy = ball.getDy();

        ball.setX(x + dx);
        ball.setY(y + dy);
    }

    // Called from BreakoutGUI
    public void movePaddle(PaddleMovement pm) {
        switch (pm) {
            case MOVE_LEFT:
                paddleVel = -5;
                break;
            case MOVE_RIGHT:
                paddleVel = 5;
                break;
            case STOP_PADDLE:
                paddleVel = 0;
        }
    }

    // When the ball leaves the bottom of the screen, this should be called.
    private void lowerPoints() {
        nBalls--;
    }
    // endregion

    // region COLLISION

    // TODO Collision detection methods
    private void collisionDetection() {

        // Check collision with all walls
        for (Wall w : walls) {
            if (AABB.isCollidingWithWall(ball, w)) {
                System.out.println("Collision occurred");
                Wall.Dir dir = w.getDirection();

                if (dir == Wall.Dir.HORIZONTAL)
                    ball.setDx(ball.getDx() * -1);
                else
                    ball.setDy(ball.getDy() * -1);

                break;
            }
        }

        // Check collision with Bricks
        // TODO ...
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
}
