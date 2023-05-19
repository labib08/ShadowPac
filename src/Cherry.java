import bagel.util.Rectangle;

public class Cherry extends StationaryEntity {
    private final boolean REVERSE_DIRECTION = false;

    /**
     * The points gained by the player if it eats the cherry.
     */
    public final static int POINTS = 20;

    /**
     * Constructs a new Cherry object, with the specified filepath of the cherry image, and refers
     * to its superclass.
     * @param cherryFile this parameter is the filepath of the cherry image.
     */
    public Cherry(String cherryFile) {
        super(cherryFile);
    }

    /**
     * This method removes the cherry from the game screen once it has been eaten by the player,
     * and the score of the player increases by 20 points.
     * @param statEntityRectangle This parameter is the rectangle box of the cherry.
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     */
    public void collisionAction(Rectangle statEntityRectangle, Player player) {
        getStatEntityXCoordinates()[getCollisionPointX()] = -100;
        getStatEntityYCoordinates()[getCollisionPointY()] = -100;
        player.incrementScoreCherry();
    }

    /**
     * Performs a state update.
     * Draws the cherry.
     * @param player This is the object of the class Player, which is represented by the PacMan.
     * @param blueGhost This is the object of the class BlueGhost, which is represented by the Blue Ghost in the game.
     * @param redGhost This is the object of the class RedGhost, which is represented by the Red ghost in the game.
     * @param greenGhost This is the object of the class GreenGhost, which is represented by the Green ghost.
     * @param pinkGhost This is the object of the class PinkGhost, which is represented by the Pink Ghost in the game.
     */
    protected void update(Player player, BlueGhost blueGhost, RedGhost redGhost, GreenGhost greenGhost,
            PinkGhost pinkGhost) {
        drawStationaryEntity(player, blueGhost, redGhost, greenGhost, pinkGhost, REVERSE_DIRECTION);

    }
}
