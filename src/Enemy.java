import bagel.*;
import bagel.util.Rectangle;
import bagel.util.Point;

public class Enemy {
    private final Image enemyImage;
    private final Image frenzyImage = new Image("res/ghostFrenzy.png");
    private double heightEnemy;
    private double widthEnemy;
    private double enemyX = -100;
    private double enemyY = -100;
    private double lastGhostX;
    private double lastGhostY;
    private double initialPosX;
    private double initialPosY;
    private boolean stopEnemy = false;
    private final String HORIZONTAL = "Horizontal";
    private final String VERTICAL = "Vertical";

    /**
     * The points gained by the player if it eats the enemy during frenzy mode.
     */
    public final static int POINTS = 30;

    /**
     * Constructs a new Enemy object, with the specified filepath of the enemy image, and determines
     * the height and width of the image.
     * @param ghostFile this parameter is the filepath of the enemy image.
     */
    public Enemy(String ghostFile) {
        this.enemyImage = new Image(ghostFile);
        this.heightEnemy = enemyImage.getHeight();
        this.widthEnemy = enemyImage.getWidth();
    }

    /**
     * Sets the position of the enemy and also records their initial position.
     * @param enemyX This parameter is the X-Coordinate of the enemy.
     * @param enemyY This parameter is the Y-Coordinate of the enemy.
     */
    public void setPosition(double enemyX, double enemyY) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        initialPosX = enemyX;
        initialPosY = enemyY;
    }

    /**
     * This method moves the enemy in a specific direction, ie, either Vertically or Horizontally.
     * @param direction This parameter is the specific direction of the enemy.
     * @param stepSize This parameter is the step size of the enemy. It determines the
     * speed per frame of the enemy.
     */
    public void moveEnemy(String direction, double stepSize) {
        lastGhostX = enemyX;
        lastGhostY = enemyY;
        if (direction.equals(VERTICAL)) {
            this.enemyY += stepSize;
        } else if (direction.equals(HORIZONTAL)) {
            this.enemyX += stepSize;
        }
    }

    //

    /**
     * @return This method returns the last Point of the enemy before moving to the next Point
     */
    public Point getLastPoint() {
        return new Point(lastGhostX, lastGhostY);
    }

    /**
     * This method checks whether the player collides with the enemy. The player loses one life
     * upon collision with the enemy, however if the game is in frenzy mode,
     * the enemy can be eaten by the player, and the player will gain
     * points instead of losing life, and the enemy will be removed from the board.
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     * @param isFrenzy This parameter determines whether the game is in frenzy mode or not.
     */
    public void checkCollisionPlayer(Player player, boolean isFrenzy) {
        if (new Rectangle(enemyX, enemyY, widthEnemy, heightEnemy).intersects(player.getPlayerRectangle())) {
            if (!isFrenzy) {
                player.resetPosition();
                player.reduceLives();
            } else {
                enemyX = -100;
                enemyY = -100;
                stopEnemy = true;
                player.incrementScoreEnemy();
            }
        }
    }

    /**
     * This method stops the enemy from colliding with the wall.
     * @param enemyX This parameter is the X-Coordinate of the enemy.
     * @param enemyY This parameter is the Y-Coordinate of the enemy.
     */
    public void stopIntersection(double enemyX, double enemyY) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
    }

    /**
     * A getter method
     * @return This method returns the variable stopEnemy, which determines whether the enemy should move.
     */
    public boolean getStopEnemy() {
        return stopEnemy;
    }

    /**
     * A getter method.
     * @return This method returns the rectangle created by the enemy, which takes in the X and Y Coordinate of
     * the enemy and its width and height.
     */
    public Rectangle getEnemyRectangle() {
        return new Rectangle(enemyX, enemyY, widthEnemy, heightEnemy);
    }

    /**
     * Draws the Enemy and if there is a collision, it takes one life away from the
     * player. Also, if the enemy was eaten during the frenzy mode, it will be rendered
     * again to its initial position once the frenzy mode ends.
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     */
    public void drawEnemies(Player player) {
        if (player.getFrenzy()) {
            frenzyImage.drawFromTopLeft(enemyX, enemyY);
        } else {
            if (enemyX == -100 && enemyY == -100) {
                enemyX = initialPosX;
                enemyY = initialPosY;
            }
            stopEnemy = false;
            enemyImage.drawFromTopLeft(enemyX, enemyY);
        }
        checkCollisionPlayer(player, player.getFrenzy());
    }
}
