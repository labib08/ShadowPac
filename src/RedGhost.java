
public class RedGhost extends Enemy {
    private double stepSize = 1;
    private double stepSizeFrenzy = 0.5;
    private final static String HORIZONTAL = "Horizontal";

    public RedGhost(String redGhostFile) {
        super(redGhostFile);
    }

    public void changeEnemyDirection() {
        this.stepSize *= -1;
        this.stepSizeFrenzy += 1;
    }

    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveGhost(HORIZONTAL, stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveGhost(HORIZONTAL, stepSizeFrenzy);
        }
        drawEnemies(player);

    }
}
