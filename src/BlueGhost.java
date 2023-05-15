public class BlueGhost extends Enemy {
    private double stepSize = 2;
    private double stepSizeFrenzy = 1.5;
    private final static String VERTICAL = "Vertical";

    public BlueGhost(String blueGhostFile) {
        super(blueGhostFile);
    }

    public void changeEnemyDirection() {
        this.stepSize *= -1;
        this.stepSizeFrenzy *= 1;
    }

    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveGhost(VERTICAL, stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveGhost(VERTICAL, stepSizeFrenzy);
        }
        drawEnemies(player);
    }
}
