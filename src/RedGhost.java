public class RedGhost extends Enemy {
    private double stepSize = 1;
    private double stepSizeFrenzy = 0.5;
    private final static String HORIZONTAL = "Horizontal";

    /**
     * Constructs a new RedGhost object with the specified filepath of the red ghost image, and refers
     * to its superclass.
     * @param redGhostFile This parameter is the filepath of the red ghost image.
     */
    public RedGhost(String redGhostFile) {
        super(redGhostFile);
    }

    /**
     * This method reverses the direction of the ghost, once it collides with the wall.
     */
    public void changeEnemyDirection() {
        this.stepSize *= -1;
        this.stepSizeFrenzy += 1;
    }

    /**
     * This method performs a state update.
     * The enemy only moves if it is not eaten by the player, during frenzy mode.
     * @param player This is the object of the class Player, which is represented by the PacMan.
     */
    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveEnemy(HORIZONTAL, stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveEnemy(HORIZONTAL, stepSizeFrenzy);
        }
        drawEnemies(player);

    }
}
