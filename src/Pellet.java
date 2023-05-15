import bagel.util.Rectangle;

public class Pellet extends StationaryEntity {

    public Pellet(String pelletFile) {
        super(pelletFile);
    }

    /**
     * The dot is removed from the screen when the player collides with it.
     */
    public void collisionAction(Rectangle statEntityRectangle, Player player) {
        getStatEntityXCoordinates()[getCollisionPointX()] = -100;
        getStatEntityYCoordinates()[getCollisionPointY()] = -100;
        player.setFrenzy(true);
    }

    protected void update(Player player, BlueGhost blueGhost, RedGhost redGhost, GreenGhost greenGhost,
            PinkGhost pinkGhost) {
        drawStationaryEntity(player, blueGhost, redGhost, greenGhost, pinkGhost, false);
    }
}
