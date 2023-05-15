import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.lang.Math;

public class Player {
    private static final double STEP_SIZE = 3;
    private final int MAX_FRENZY_FRAME = 1000;
    private final Image playerImage = new Image("res/pac.png");
    private final Image playerImageOpen = new Image("res/pacOpen.png");
    private final DrawOptions options = new DrawOptions();
    private double playerX;
    private double playerY;
    private double initialPosX;
    private double initialPosY;
    private double lastPlayerX;
    private double lastPlayerY;
    private double playerHeight = playerImage.getHeight();
    private double playerWidth = playerImage.getWidth();
    private double angle;
    private double frame = 0;
    private double frameFrenzy = 0;
    private double x;
    private int lives = 3;
    private int score = 0;
    private boolean isFrenzy = false;

    public Player() {
        resetPosition();
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score += StationaryEntity.POINTS;
    }

    public void incrementScoreEnemy() {
        score += Enemy.POINTS;

    }

    /**
     * Sets and records the initial coordinates of the Pacman
     */
    public void setPosition(double playerX, double playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
        initialPosX = playerX;
        initialPosY = playerY;
    }

    /**
     * Resets the position of the player to its initial Coordinates
     */
    public void resetPosition() {
        this.playerX = initialPosX;
        this.playerY = initialPosY;
    }

    /**
     * When the player intersects with a wall, the player's position is reset to its
     * position just before collision.
     */
    public void stopIntersection(double playerX, double playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public void setFrenzy(boolean isFrenzy) {
        this.isFrenzy = isFrenzy;
    }

    public boolean getFrenzy() {
        return isFrenzy;
    }

    /**
     * Assumes the PacMan is a rectangle
     */
    public Rectangle getPlayerRectangle() {
        return new Rectangle(playerX, playerY, playerWidth, playerHeight);
    }

    /**
     * Method to get the last point of the player
     * just before collision with the wall.
     */

    public Point getLastPoint() {
        return new Point(lastPlayerX, lastPlayerY);
    }

    /**
     * Controls the movement of the player with keys UP, DOWN LEFT AND RIGHT
     * and rotates the player according to its direction
     */
    protected void update(Input input) {
        // Records the previous X and Y coordinates of the player.
        lastPlayerX = playerX;
        lastPlayerY = playerY;
        // This controls the movement of the player with arrow keys
        // and rotates the Pacman accordingly.
        if (input.isDown(Keys.LEFT)) {
            playerX -= STEP_SIZE;
            angle = Math.PI;
        }
        if (input.isDown(Keys.RIGHT)) {
            playerX += STEP_SIZE;
            angle = 2 * Math.PI;
        }
        if (input.isDown(Keys.UP)) {
            playerY -= STEP_SIZE;
            angle = (3 * Math.PI) / 2;
        }
        if (input.isDown(Keys.DOWN)) {
            playerY += STEP_SIZE;
            angle = Math.PI / 2;
        }
        /**
         * Shifts between two images of the Pacman every 15 frames
         */
        frame++;
        if ((frame % 15) == 0) {
            x++;
        }
        if (x % 2 == 0) {
            playerImage.drawFromTopLeft(playerX, playerY, options.setRotation(angle));
        } else {
            playerImageOpen.drawFromTopLeft(playerX, playerY, options.setRotation(angle));
        }
        if (isFrenzy) {
            if (frameFrenzy == MAX_FRENZY_FRAME) {
                isFrenzy = false;
            }
            frameFrenzy++;
        }
    }
}
