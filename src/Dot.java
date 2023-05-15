import bagel.util.Rectangle;

public class Dot extends StationaryEntity {

    public Dot(String dotFile) {
        super(dotFile);
    }

    /**
     * The dot is removed from the screen when the player collides with it,
     * and the score is updated.
     */
    public void collisionAction(Rectangle statEntityRectangle, Player player) {
        getStatEntityXCoordinates()[getCollisionPointX()] = -100;
        getStatEntityYCoordinates()[getCollisionPointY()] = -100;
        player.incrementScore();
    }

    protected void update(Player player, BlueGhost blueGhost, RedGhost redGhost, GreenGhost greenGhost,
            PinkGhost pinkGhost) {
        drawStationaryEntity(player, blueGhost, redGhost, greenGhost, pinkGhost, false);
    }
}
