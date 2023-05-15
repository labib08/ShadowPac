import bagel.util.Rectangle;

public class Cherry extends StationaryEntity {

    public Cherry(String cherryFile) {
        super(cherryFile);
    }

    /**
     * The cherry is removed from the screen when the player collides with it
     * and the player's score increases by 10.
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
