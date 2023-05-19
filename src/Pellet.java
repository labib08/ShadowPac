import bagel.util.Rectangle;

public class Pellet extends StationaryEntity {
    private final boolean REVERSE_DIRECTION = false;

    /**
     * Constructs a new Pellet object, with the specified filepath of the pellet image, and refers
     * to its superclass.
     * @param pelletFile this parameter is the filepath of the pellet image.
     */
    public Pellet(String pelletFile) {
        super(pelletFile);
    }

    /**
     * This method removes the pellet from the game screen once it has been eaten by the player,
     * and sends the game into frenzy mode.
     * @param statEntityRectangle This parameter is the rectangle box of the dot.
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     */
    public void collisionAction(Rectangle statEntityRectangle, Player player) {
        getStatEntityXCoordinates()[getCollisionPointX()] = -100;
        getStatEntityYCoordinates()[getCollisionPointY()] = -100;
        player.setFrenzy(true);
    }

    /**
     * Performs a state update.
     * Draws the pellet.
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
