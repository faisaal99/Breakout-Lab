package breakout.view;

import breakout.event.EventBus;
import breakout.event.IEventHandler;
import breakout.event.ModelEvent;
import breakout.model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;
import static breakout.model.Brick.BRICK_HEIGHT;
import static breakout.model.Brick.BRICK_WIDTH;
import static breakout.model.Breakout.PaddleMovement.MOVE_LEFT;
import static breakout.model.Breakout.PaddleMovement.MOVE_RIGHT;
import static breakout.model.Breakout.PaddleMovement.STOP_PADDLE;
import static breakout.model.Paddle.PADDLE_WIDTH;
import static java.lang.System.exit;
import static java.lang.System.out;

/*
 * The GUI for the Breakout game (except the menu).
 * No application logic here just GUI and event handling
 *
 * Run this to run the game
 *
 * See: https://en.wikipedia.org/wiki/Breakout_(video_game)
 *
 */
public class BreakoutGUI extends Application implements IEventHandler {

    private Breakout breakout;          // The only reference to the model allowed
    private boolean running = false;    // Is game running?

    // ------- Keyboard events ----------------------------------

    private void keyPressed(KeyEvent event) {
        if (!running) {
            return;
        }

        KeyCode kc = event.getCode();
        switch (kc) {
            case LEFT -> breakout.movePaddle(MOVE_LEFT);
            case RIGHT -> breakout.movePaddle(MOVE_RIGHT);
            case SPACE -> renderDebug = !renderDebug;
        }
    }

    private void keyReleased(KeyEvent event) {
        if (!running) {
            return;
        }

        KeyCode kc = event.getCode();
        switch (kc) {   // No break, fall through
            case LEFT, RIGHT -> breakout.movePaddle(STOP_PADDLE);
        }
    }

    // ---------- Menu actions ---------------------

    private void newGame() {
        // GUI handling
        menu.fixMenusNewGame();
        renderBackground();

        // --- Build the model -----
        // TODO Build the model (also: see methods below)
        Ball b = new Ball();
        Paddle p = new Paddle(GAME_WIDTH / 2 - PADDLE_WIDTH, GAME_HEIGHT - 30);
        p.setDx(Paddle.PADDLE_SPEED);
        List<Brick> bricks = getBricks(6, 16);
        List<Wall> walls = getWalls();

        breakout = new Breakout(b, p, walls, bricks);

        // Bind bricks to images
        bindBricks(bricks);

        // Start game
        timer.start();
        running = true;
    }

    private void killGame() {
        timer.stop();
        menu.fixMenusKillGame();
        renderSplash();
        running = false;
    }

    // ---------- Helper to build model ------------

    // Create all walls
    private List<Wall> getWalls() {
        Wall left = new Wall(-20, 0, 20, GAME_HEIGHT, Wall.Dir.VERTICAL);
        Wall top = new Wall(0, -20, 0, 20, Wall.Dir.HORIZONTAL);
        Wall right = new Wall(GAME_WIDTH, 0, 20, GAME_HEIGHT, Wall.Dir.VERTICAL);

        return Arrays.asList(left, top,right);
    }

    // Create the formation of bricks
    private List<Brick> getBricks(int nRows, int nCols) {
        List<Brick> bricks = new ArrayList<>();

        int bw = (int) BRICK_WIDTH;
        int bh = (int) BRICK_HEIGHT;

        int offset = 5;
        int points = 300;

        for (int y = 10 * offset; y < nRows * (bh + offset); y += bh + offset) {
            for (int x = offset - 2; x < nCols * (bw + offset); x += bw + offset) {
                Brick b = new Brick(x, y);
                b.setPoints(points);
                bricks.add(b);
            }

            points -= 100;
        }

        return bricks;
    }

    // Bind bricks to images
    private void bindBricks(List<Brick> bricks) {
        for (Brick b : bricks) {
            switch (b.getPoints()) {
                case 100 -> assets.bind(b, assets.greenTile);
                case 200 -> assets.bind(b, assets.blueTile);
                case 300 -> assets.bind(b, assets.redTile);
            }
        }
    }

    // -------- Event handling (events sent from model to GUI) -----------

    @Override
    public void onModelEvent(ModelEvent evt) {
        switch (evt.type) {
            case BALL_HIT_PADDLE -> assets.ballHitPaddle.play();
            case BALL_HIT_BRICK  -> assets.ballHitBrick.play();
            case GAME_OVER       -> killGame();
        }
    }


    // TODO Optional
    // Program will jump to here when clicking menu item
    private void handleMenuLevels(ActionEvent e) {
        // OPTIONAL: You decide what to do!
        RadioMenuItem i = (RadioMenuItem) e.getSource();

        if (i.isSelected()) {
            out.println(i.getText());
        }
    }

    // ***********  Nothing to do below ********************'''''

    // Program will jump to here when clicking menu item
    private void handleMenuFile(ActionEvent e) {
        String s = ((MenuItem) e.getSource()).getText();
        switch (s) {
            case "New"  -> newGame(); // Using text on menu item
            case "Stop" -> killGame();
            case "Exit" -> exit(0);
            default -> throw new IllegalArgumentException("No such menu choice " + s);
        }
    }

    private Assets assets;
    // For debugging, see render()
    private boolean renderDebug = false; //true;

    private void render() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);    // Clear everything
        fg.setStroke(Paint.valueOf("fff")); // White stroke

        for (IPositionable d : breakout.getPositionables()) {
            if (renderDebug) {
                fg.strokeRect(d.getX(), d.getY(), d.getWidth(), d.getHeight());
            } else {
                fg.drawImage(assets.get(d), d.getX(), d.getY(), d.getWidth(), d.getHeight());
            }
        }

        fg.setFill(Assets.colorFgText);
        fg.setFont(Font.font(14));
        fg.fillText("Points: " + breakout.getPlayerPoints(), 10, GAME_HEIGHT - 5);
        fg.fillText("Balls Left: " + breakout.getNBalls(), 300, GAME_HEIGHT - 5);
    }

    private void renderBackground() {
        if (!renderDebug) {
            bg.drawImage(assets.background, 0, 0, GAME_WIDTH, GAME_HEIGHT);
        }
    }

    private void renderSplash() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        bg.drawImage(assets.splash, 0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

    // -------------- Build Scene and start graphics ---------------

    private AnimationTimer timer;
    private GraphicsContext fg;
    private GraphicsContext bg;
    private final BreakoutMenu menu = new BreakoutMenu(this::handleMenuFile, this::handleMenuLevels);

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        root.setTop(menu);

        // Drawing areas
        Canvas background = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        bg = background.getGraphicsContext2D();
        Canvas foreground = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        fg = foreground.getGraphicsContext2D();

        Pane pane = new Pane(background, foreground);
        root.setCenter(pane);

        timer = new AnimationTimer() {
            public void handle(long now) {
                breakout.update(now);
                render();
            }
        };

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Breakout");

        // Set assets, splash (order matters) and initial menu state
        assets = new Assets();
        menu.fixMenusKillGame();
        renderSplash();

        // Make it possible to send events from model to this
        EventBus.INSTANCE.register(this);

        // Show on screen
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
