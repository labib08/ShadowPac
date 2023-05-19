import java.util.Random;

public class GreenGhost extends Enemy {
    private double stepSize = 4;
    private double stepSizeFrenzy = 3.5;
    private final int MAX_DIRECTION = 2;
    private String[] directions = { "Vertical", "Horizontal" };
    private int currDirection = new Random().nextInt(MAX_DIRECTION);

    /**
     * Constructs a new GreenGhost object with the specified filepath of the green ghost image, and refers
     * to its superclass.
     * @param greenGhostFile This parameter is the filepath of the green ghost image.
     */
    public GreenGhost(String greenGhostFile) {
        super(greenGhostFile);
    }

    /**
     * Reverses its direction once it collides with the wall.
     */
    public void changeEnemyDirection() {
        this.stepSize *= -1;
        this.stepSizeFrenzy += 1;
    }

    /**
     * Performs state update.
     * The enemy only moves if it is not eaten by the player during frenzy mode, and
     * its initial direction is randomly chosen. It does not change its direction once the level starts
     * @param player This parameter is the object of the class Player, which is represented by the PacMan.
     */
    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveEnemy(directions[currDirection], stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveEnemy(directions[currDirection], stepSizeFrenzy);
        }
        drawEnemies(player);
    }
}
