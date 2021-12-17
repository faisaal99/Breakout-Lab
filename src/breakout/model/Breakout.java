package breakout.model;


import breakout.collision.AABB;
import breakout.event.EventBus;
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
    double paddleVel = 0;   // Paddle velocity (negative value means a left-direction)

    // All objects needed for the model
    Ball ball; // ............ :D
    Paddle paddle; // ........ :)
    List<Wall> walls; // ..... :|
    List<Brick> bricks; // ... :(

    public Breakout(Ball ball, Paddle paddle, List<Wall> walls, List<Brick> bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.walls = walls;
        this.bricks = bricks;

        timeForLastHit = 0;
    }

    // region  GAME LOGIC

    // To avoid multiple collisions
    private long timeForLastHit;   // This works as a timer
    private long lastNowValue = 0; // The now-value from the previous frame

    public void update(long now) {

        if (lastNowValue == 0)
            lastNowValue = now;
        else
            updateTimer(now);

        // Move the paddle
        paddle.move();
        clampPaddleLocation();

        // Check for any collisions
        if (timeForLastHit <= 0)
            collisionDetection();

        // Move the ball
        ball.move();

        // Check if the ball has left the screen
        if (ballOutOfBounds()) {
            lowerLives();

            if (nBalls > 0) {
                ball.newRandomStartingRot();
            } else {
                EventBus.INSTANCE.publish(ModelEvent.Type.GAME_OVER);
            }
        }
    }

    // Updates the x-value of the paddle, after delta is set.
    private void clampPaddleLocation() {
        // Keep the paddle within bounds
        if (paddle.getX() < 0)
            paddle.setX(0);
        else if (paddle.getX() + paddle.getWidth() > GAME_WIDTH)
            paddle.setX(GAME_WIDTH - paddle.getWidth());
    }

    // Called from BreakoutGUI | Moves the paddle in either direction
    public void movePaddle(PaddleMovement pm) {
        switch (pm) {
            case MOVE_LEFT   -> paddle.setDx(-Paddle.PADDLE_SPEED);
            case MOVE_RIGHT  -> paddle.setDx(Paddle.PADDLE_SPEED);
            case STOP_PADDLE -> paddle.setDx(0);
        }
    }

    // When the ball leaves the bottom of the screen, this should be called.
    private void lowerLives() {
        nBalls--;
    }

    // Check if the ball has left the screen
    private boolean ballOutOfBounds() {
        final double OUT_OF_BOUNDS_MARGIN = 100;
        return ball.getY() > GAME_HEIGHT + OUT_OF_BOUNDS_MARGIN;
    }

    // endregion

    // region COLLISION

    // Checks collision with walls, the paddle and all the bricks in the scene
    private void collisionDetection() {

        // Check collision with all walls
        for (Wall w : walls) {
            if (AABB.isCollidingWithObject(ball, w)) {
                setCollisionTimer();

                Wall.Dir dir = w.getDirection();
                if (dir == Wall.Dir.HORIZONTAL)
                    ball.setDy(ball.getDy() * -1);
                else
                    ball.setDx(ball.getDx() * -1);

                // Play sound
                EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_WALL);

                return;
            }
        }

        // Check collision with paddle
        if (AABB.isCollidingWithObject(ball, paddle)) {
            setCollisionTimer();
            bounceBallOnPaddle();

            // Play sound
            EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_PADDLE);

            return;
        }

        Brick brickToRemove = null;
        boolean isBrickCollision = false;

        // Check collision with any brick
        for (Brick b : bricks) {
            if (AABB.isCollidingWithObject(ball, b)) {
                setCollisionTimer();

                ball.setDy(ball.getDy() * -1);
                ball.increaseSpeedByFactor(BALL_SPEED_FACTOR);
                playerPoints += b.getPoints();

                brickToRemove = b;
                isBrickCollision = true;

                // Play sound
                EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_BRICK);

                break;
            }
        }

        if (isBrickCollision) {
            bricks.remove(brickToRemove);
        }
    }

    // When the ball bounces on the paddle,
    // determine which direction the ball should move
    // based on where on the paddle the ball hit
    private void bounceBallOnPaddle() {
        ball.setDy(ball.getDy() * -1);

        final double MAX_ANGLE = Math.toRadians(20);
        final double HALF_PADDLE_WIDTH = paddle.getWidth() / 2.0;

        double mx = paddle.getX() + HALF_PADDLE_WIDTH;   // Middle point
        double px = ball.getX() + ball.getWidth() / 2.0; // Hit point
        double mxDiffPx = px - mx;                       // Difference between points
        mxDiffPx = clamp(mxDiffPx, HALF_PADDLE_WIDTH);   // Clamp the value

        double percentOffCenter = mxDiffPx / HALF_PADDLE_WIDTH;
        double deltaAngle = Math.PI / 2 - MAX_ANGLE;
        double newAngle = Math.PI / 2 - deltaAngle * percentOffCenter;

        ball.setNewVelocityDirection(newAngle);
    }

    // Clamps the value within the limit's positive and negative value
    private double clamp(double value, double limit) {
        if (value > limit)
            return limit;
        else if (value < -limit)
            return -limit;

        return value;
    }

    // Set the timer for next collision
    private void setCollisionTimer() {
        timeForLastHit = 98_000_000_000L;
    }

    // Lower the remaining time
    private void updateTimer(long now) {
        long delta = now - lastNowValue;
        timeForLastHit -= delta;

        if (timeForLastHit < 0)
            timeForLastHit = 0;
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
