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
    public final static int POINTS = 10;

    public StationaryEntity(String statEntityFile) {
        this.statEntityImage = new Image(statEntityFile);
        this.statEntityHeight = statEntityImage.getHeight();
        this.statEntityWidth = statEntityImage.getWidth();
    }

    /**
     * Creates an array of X-Coordinates of the Stationary Entity, which is read
     * from the CSV.
     */
    public void setStatEntityXCoordinates(double statEntityX) {
        statEntityXCoordinates[xCount] = statEntityX;
        xCount++;
    }

    /**
     * Creates an array of Y-Coordinates of the Stationary Entity, which is read
     * from the CSV.
     */
    public void setStatEntityYCoordinates(double statEntityY) {
        statEntityYCoordinates[yCount] = statEntityY;
        yCount++;
    }

    /**
     * Resets the Stationary Entity for the next level.
     */
    public void resetStatEntity() {
        statEntityXCoordinates = new double[MAX_STATENTITY];
        statEntityYCoordinates = new double[MAX_STATENTITY];
        xCount = 0;
        yCount = 0;
    }

    public double[] getStatEntityXCoordinates() {
        return statEntityXCoordinates;
    }

    public double[] getStatEntityYCoordinates() {
        return statEntityYCoordinates;
    }

    public int getCollisionPointX() {
        return collisionPointX;
    }

    public int getCollisionPointY() {
        return collisionPointY;
    }

    /**
     * Draws the stationary entity and checks for collision.
     */
    public void drawStationaryEntity(Player player, BlueGhost blueGhost, RedGhost redGhost, GreenGhost greenGhost,
            PinkGhost pinkGhost, boolean isActive) {
        for (int i = 0; i < xCount; i++) {
            statEntityImage.drawFromTopLeft(statEntityXCoordinates[i], statEntityYCoordinates[i]);
            statEntityRectangle = new Rectangle(statEntityXCoordinates[i], statEntityYCoordinates[i],
                    statEntityWidth, statEntityHeight);
            if (statEntityRectangle.intersects(player.getPlayerRectangle())) {
                collisionPointX = collisionPointY = i;
                collisionAction(statEntityRectangle, player);
            } else if (blueGhost.getEnemyRectangle().intersects(statEntityRectangle) && isActive) {
                blueGhost.changeEnemyDirection();
            } else if (redGhost.getEnemyRectangle().intersects(statEntityRectangle) && isActive) {
                redGhost.changeEnemyDirection();
            } else if (greenGhost.getEnemyRectangle().intersects(statEntityRectangle) && isActive) {
                greenGhost.changeEnemyDirection();
            } else if (pinkGhost.getEnemyRectangle().intersects(statEntityRectangle) && isActive) {
                pinkGhost.changeEnemyDirection();
                pinkGhost.stopIntersection(pinkGhost.getLastPoint().x, pinkGhost.getLastPoint().y);
            }
        }
    }

    public abstract void collisionAction(Rectangle statEntityRectangle, Player player);

}
