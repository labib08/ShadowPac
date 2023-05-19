import bagel.*;
import bagel.util.Rectangle;

public class Ghost {
    private final int MAX_GHOSTS = 4;
    private Image ghostImage = new Image("res/ghostRed.png");
    private double[] ghostXCoordinates = new double[MAX_GHOSTS];
    private double[] ghostYCoordinates = new double[MAX_GHOSTS];
    private double heightGhost = ghostImage.getHeight();
    private double widthGhost = ghostImage.getWidth();
    private int xCount = 0;
    private int yCount = 0;

    /**
     * Creates an array of X-Coordinates of the ghost, which is read
     * from the CSV.
     * @param ghostX This parameter is the X-Coordinate of the ghost.
     */
    public void setGhostXCoordinates(double ghostX) {
        ghostXCoordinates[xCount] = ghostX;
        xCount++;
    }

    /**
     * Creates an array of Y-Coordinates of the ghost, which is read
     * from the CSV.
     * @param ghostY This parameter is the Y-Coordinate of the ghost.
     */
    public void setGhostYCoordinates(double ghostY) {
        ghostYCoordinates[yCount] = ghostY;
        yCount++;
    }

    /**
     * This method resets the array once the level is completed.
     */
    public void resetGhost() {
        ghostXCoordinates = new double[MAX_GHOSTS];
        ghostYCoordinates = new double[MAX_GHOSTS];
        xCount = 0;
        yCount = 0;
    }

    /**
     * Performs a state update.
     * This method draws the ghosts.
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     */
    protected void update(Player player) {
        for (int i = 0; i < MAX_GHOSTS; i++) {
            ghostImage.drawFromTopLeft(ghostXCoordinates[i], ghostYCoordinates[i]);
            /*
              If the player and the ghost intersects, the position of the player is reset
              to its initial Coordinates and the player loses one life.
             */
            if (new Rectangle(ghostXCoordinates[i], ghostYCoordinates[i], widthGhost, heightGhost)
                    .intersects(player.getPlayerRectangle())) {
                player.resetPosition();
                player.reduceLives();
            }
        }
    }
}