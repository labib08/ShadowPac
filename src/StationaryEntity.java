import bagel.*;
import bagel.util.Rectangle;

public abstract class StationaryEntity {
    private final int MAX_STATENTITY = 266;
    private Image statEntityImage;
    private double[] statEntityXCoordinates = new double[MAX_STATENTITY];
    private double[] statEntityYCoordinates = new double[MAX_STATENTITY];
    private Rectangle statEntityRectangle;
    private double statEntityWidth;
    private double statEntityHeight;
    private int xCount = 0;
    private int yCount = 0;
    private int collisionPointX;
    private int collisionPointY;

    /**
     * Constructs a new Stationary Entity object, with the specified filepath of the stationary object image,
     * and determines the height and width of the image.
     * @param statEntityFile this parameter is the filepath of the stationary entity image.
     */
    public StationaryEntity(String statEntityFile) {
        this.statEntityImage = new Image(statEntityFile);
        this.statEntityHeight = statEntityImage.getHeight();
        this.statEntityWidth = statEntityImage.getWidth();
    }

    /**
     * Creates an array of X-Coordinates of the Stationary Entity, which is read
     * from the CSV.
     * @param statEntityX This parameter is the X-Coordinate of the Stationary Entity.
     */
    public void setStatEntityXCoordinates(double statEntityX) {
        statEntityXCoordinates[xCount] = statEntityX;
        xCount++;
    }

    /**
     * Creates an array of Y-Coordinates of the Stationary Entity, which is read
     * from the CSV.
     * @param statEntityY This parameter is the Y-Coordinate of the Stationary Entity.
     */
    public void setStatEntityYCoordinates(double statEntityY) {
        statEntityYCoordinates[yCount] = statEntityY;
        yCount++;
    }

    /**
     * This method resets the Stationary Entity and its array for the next level.
     */
    public void resetStatEntity() {
        statEntityXCoordinates = new double[MAX_STATENTITY];
        statEntityYCoordinates = new double[MAX_STATENTITY];
        xCount = 0;
        yCount = 0;
    }

    /**
     * Getter method.
     * @return This method returns the array of X-Coordinate of the stationary entity
     */
    public double[] getStatEntityXCoordinates() {
        return statEntityXCoordinates;
    }

    /**
     * Getter method.
     * @return This method returns the array of Y-Coordinate of the stationary entity
     */
    public double[] getStatEntityYCoordinates() {
        return statEntityYCoordinates;
    }

    /**
     * Getter method.
     * @return This method returns X-Coordinate of the point where the player collided with the stationary entity.
     */
    public int getCollisionPointX() {
        return collisionPointX;
    }

    /**
     * Getter method.
     * @return This method returns Y-Coordinate of the point where the player collided with the stationary entity.
     */
    public int getCollisionPointY() {
        return collisionPointY;
    }

    /**
     * This method draws the stationary entity.
     * @param player This is the object of the class Player, which is represented by the PacMan.
     * @param blueGhost This is the object of the class BlueGhost, which is represented by the Blue Ghost in the game.
     * @param redGhost This is the object of the class RedGhost, which is represented by the Red ghost in the game.
     * @param greenGhost This is the object of the class GreenGhost, which is represented by the Green ghost.
     * @param pinkGhost This is the object of the class PinkGhost, which is represented by the Pink Ghost in the game.
     * @param isReverseDirection This parameter determines whether the enemy should reverse its direction upon
     *                           collision with the stationary entity.
     */
    public void drawStationaryEntity(Player player, BlueGhost blueGhost, RedGhost redGhost, GreenGhost greenGhost,
            PinkGhost pinkGhost, boolean isReverseDirection) {
        for (int i = 0; i < xCount; i++) {
            statEntityImage.drawFromTopLeft(statEntityXCoordinates[i], statEntityYCoordinates[i]);
            statEntityRectangle = new Rectangle(statEntityXCoordinates[i], statEntityYCoordinates[i],
                    statEntityWidth, statEntityHeight);
            if (statEntityRectangle.intersects(player.getPlayerRectangle())) {
                collisionPointX = collisionPointY = i;
                collisionAction(statEntityRectangle, player);
                // When the ghosts collide with the stationary entity, the ghosts only reverse direction
                // if the stationary entity is a wall, otherwise they continue their  movements.
            } else if (blueGhost.getEnemyRectangle().intersects(statEntityRectangle) && isReverseDirection) {
                blueGhost.changeEnemyDirection();
            } else if (redGhost.getEnemyRectangle().intersects(statEntityRectangle) && isReverseDirection) {
                redGhost.changeEnemyDirection();
            } else if (greenGhost.getEnemyRectangle().intersects(statEntityRectangle) && isReverseDirection) {
                greenGhost.changeEnemyDirection();
            } else if (pinkGhost.getEnemyRectangle().intersects(statEntityRectangle) && isReverseDirection) {
                pinkGhost.changeEnemyDirection();
                pinkGhost.stopIntersection(pinkGhost.getLastPoint().x, pinkGhost.getLastPoint().y);
            }
        }
    }

    /**
     * Abstract method, which will decide the outcome of the collision between the player and the Stationary
     * entity.
     * @param statEntityRectangle This parameter is the rectangle box of the stationary entity.
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     */
    public abstract void collisionAction(Rectangle statEntityRectangle, Player player);
}
