import bagel.*;
import bagel.Font;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Ghost {
    private final int MAX_GHOSTS = 4;
    private final Image ghostImage = new Image("res/ghostRed.png");
    private double[] ghostXCoordinates = new double[MAX_GHOSTS];
    private double[] ghostYCoordinates = new double[MAX_GHOSTS];
    private double heightGhost = ghostImage.getHeight();
    private double widthGhost = ghostImage.getWidth();
    private int xCount = 0;
    private int yCount = 0;

    public void setGhostXCoordinates(double ghostX) {
        ghostXCoordinates[xCount] = ghostX;
        xCount++;
    }

    public void setGhostYCoordinates(double ghostY) {
        ghostYCoordinates[yCount] = ghostY;
        yCount++;
    }

    public void resetGhost() {
        ghostXCoordinates = new double[MAX_GHOSTS];
        ghostYCoordinates = new double[MAX_GHOSTS];
        xCount = 0;
        yCount = 0;
    }

    protected void update(Player player) {
        for (int i = 0; i < MAX_GHOSTS; i++) {
            ghostImage.drawFromTopLeft(ghostXCoordinates[i], ghostYCoordinates[i]);
            if (new Rectangle(ghostXCoordinates[i], ghostYCoordinates[i], widthGhost, heightGhost)
                    .intersects(player.getPlayerRectangle())) {
                player.resetPosition();
                player.setLives(player.getLives() - 1);
            }
        }
    }
}
