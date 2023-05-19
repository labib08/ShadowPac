import java.util.Random;

public class PinkGhost extends Enemy {
    private double stepSize = 3;
    private double stepSizeFrenzy = 2.5;
    private final int MAX_DIRECTION = 8;

    // Increased probability of changing its direction randomly.
    private String[] directions = { "Vertical", "Horizontal", "Horizontal", "Vertical", "Vertical", "Horizontal",
                                    "Vertical", "Horizontal"};
    private int randDirection;

    /**
     * Constructs a new PinkGhost object with the specified filepath of the pink ghost image, and refers
     * to its superclass.
     * @param pinkGhostFile This parameter is the filepath of the pink ghost image.
     */
    public PinkGhost(String pinkGhostFile) {
        super(pinkGhostFile);
    }

    /**
     * This method generates a random number between 0 and MAX_DIRECTION
     * reverses the direction of the ghost, once it collides with the wall
     */
    public void changeEnemyDirection() {
        randDirection = new Random().nextInt(MAX_DIRECTION);
        stepSize *= -1;
        stepSizeFrenzy *= -1;
    }

    /**
     * This method performs a state update.
     * The enemy only moves if it is not eaten by the player, during frenzy mode, and sets off
     * in a random direction everytime it collides with the wall.
     * @param player This is the object of the class Player, which is represented by the PacMan.
     */
    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveEnemy(directions[randDirection], stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveEnemy(directions[randDirection], stepSizeFrenzy);
        }
        drawEnemies(player);
    }
}
