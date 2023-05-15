import bagel.util.Rectangle;

public class Wall extends StationaryEntity {
    public Wall(String wallFile) {
        super(wallFile);
    }

    /**
     * Stops the player from intersecting with the wall.
     */
    public void collisionAction(Rectangle statEntityRectangle, Player player) {
        player.stopIntersection(player.getLastPoint().x, player.getLastPoint().y);
    }

    protected void update(Player player, BlueGhost blueGhost, RedGhost redGhost, GreenGhost greenGhost,
            PinkGhost pinkGhost) {
        drawStationaryEntity(player, blueGhost, redGhost, greenGhost, pinkGhost, true);
    }
}
