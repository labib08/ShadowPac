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

    /**
     * Constructs a new Player object and resets the position of this Player object.
     */
    public Player() {
        resetPosition();
    }

    /**
     * Setter method.
     * Sets the current remaining lives of the player.
     * @param lives This parameter is the remaining lives of the player.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Getter method.
     * @return This method returns the lives remaining of the player.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Setter method.
     * This method sets the current score of the player.
     * @param score This parameter is the score gained by the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter method.
     * @return This method returns the score gained by the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * This method increases the points of the player by 10 if it collides with a dot.
     */
    public void incrementScoreDot() {score += Dot.POINTS;}

    /**
     * This method increase the points of the player by 20 if it collides with a cherry.
     */
    public void incrementScoreCherry(){ score += Cherry.POINTS;}

    /**
     * This method increases the points of the player by 30 if it collides with the enemy during frenzy mode.
     */
    public void incrementScoreEnemy() {
        score += Enemy.POINTS;
    }

    /**
     * This method reduces the lives of the player if it collides with the enemy.
     */
    public void reduceLives() {
        lives--;
    }

    /**
     * Sets and records the initial coordinates of the Pacman
     * @param playerX This parameter is the X-Coordinate of the player.
     * @param playerY This parameter is the Y-Coordinate of the player.
     */
    public void setPosition(double playerX, double playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
        initialPosX = playerX;
        initialPosY = playerY;
    }

    /**
     * This method resets the position of the player to its initial Coordinates
     */
    public void resetPosition() {
        this.playerX = initialPosX;
        this.playerY = initialPosY;
    }

    /**
     * When the player intersects with a wall, the player's position is reset to its
     * position just before collision.
     * @param playerX This parameter is the X-Coordinate of the player.
     * @param playerY This parameter is the Y-Coordinate of the player.
     */
    public void stopIntersection(double playerX, double playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    /**
     * Setter method.
     * This method sets the variable isFrenzy.
     * @param isFrenzy This parameter determines whether the game is in frenzy mode.
     */
    public void setFrenzy(boolean isFrenzy) {
        this.isFrenzy = isFrenzy;
    }

    /**
     * Getter method.
     * @return Returns the variable isFrenzy which determines whether the game is in frenzy mode.
     */
    public boolean getFrenzy() {
        return isFrenzy;
    }

    /**
     * Getter method
     * @return This method returns the rectangle box bounding the player, PacMan.
     */
    public Rectangle getPlayerRectangle() {
        return new Rectangle(playerX, playerY, playerWidth, playerHeight);
    }

    /**
     * Getter method.
     * @return This method returns the last point of the player just before the collision.
     */
    public Point getLastPoint() {
        return new Point(lastPlayerX, lastPlayerY);
    }

    /**
     * Method that performs state update
     * This method controls the movement of the player with keys UP, DOWN LEFT AND RIGHT
     * and rotates the player according to its direction
     * @param input This parameter gives access to input devices (keyboard and mouse).
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

        // Shifts between two images of the Pacman every 15 frames.
        frame++;
        if ((frame % 15) == 0) {
            x++;
        }
        if (x % 2 == 0) {
            playerImage.drawFromTopLeft(playerX, playerY, options.setRotation(angle));
        } else {
            playerImageOpen.drawFromTopLeft(playerX, playerY, options.setRotation(angle));
        }
        // The frenzy mode lasts for 1000 frames.
        if (isFrenzy) {
            if (frameFrenzy == MAX_FRENZY_FRAME) {
                isFrenzy = false;
            }
            frameFrenzy++;
        }
    }
}
