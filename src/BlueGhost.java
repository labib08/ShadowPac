public class BlueGhost extends Enemy {
    private double stepSize = 2;
    private double stepSizeFrenzy = 1.5;
    private final static String VERTICAL = "Vertical";

    /**
     * Constructs a new BlueGhost object with the specified filepath of the blue ghost image, and refers
     * to its superclass.
     * @param blueGhostFile This parameter is the filepath of the blue ghost image.
     */
    public BlueGhost(String blueGhostFile) {
        super(blueGhostFile);
    }

    /**
     * This method reverses the direction of the ghost, once it collides with the wall.
     */
    public void changeEnemyDirection() {
        this.stepSize *= -1;
        this.stepSizeFrenzy *= 1;
    }

    /**
     * This method performs a state update.
     * The enemy only moves if it is not eaten by the player, during frenzy mode.
     * @param player This is the object of the class Player, which is represented by the PacMan.
     */
    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveEnemy(VERTICAL, stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveEnemy(VERTICAL, stepSizeFrenzy);
        }
        drawEnemies(player);
    }
}
