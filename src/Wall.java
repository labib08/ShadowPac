import bagel.util.Rectangle;

public class Wall extends StationaryEntity {
    private final boolean REVERSE_DIRECTION = true;
    /**
     * Constructs a new Wall object, with the specified filepath of the wall image, and refers
     * to its superclass.
     * @param wallFile this parameter is the filepath of the wall image.
     */
    public Wall(String wallFile) {
        super(wallFile);
    }

    /**
     * This method stops the player from colliding with the wall.
     * @param statEntityRectangle This parameter is the rectangle box of the dot.
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     */
    public void collisionAction(Rectangle statEntityRectangle, Player player) {
        player.stopIntersection(player.getLastPoint().x, player.getLastPoint().y);
    }

    /**
     * Performs a state update.
     * Draws the wall.
     * @param player This is the object of the class Player, which is represented by the PacMan.
     * @param blueGhost This is the object of the class BlueGhost, which is represented by the Blue Ghost in the game.
     * @param redGhost This is the object of the class RedGhost, which is represented by the Red ghost in the game.
     * @param greenGhost This is the object of the class GreenGhost, which is represented by the Green ghost.
     * @param pinkGhost This is the object of the class PinkGhost, which is represented by the Pink Ghost in the game.
     */
    protected void update(Player player, BlueGhost blueGhost, RedGhost redGhost, GreenGhost greenGhost,
            PinkGhost pinkGhost) {
        drawStationaryEntity(player, blueGhost, redGhost, greenGhost, pinkGhost, REVERSE_DIRECTION );
    }
}
