import java.util.Random;

public class GreenGhost extends Enemy {
    private double stepSize = 4;
    private double stepSizeFrenzy = 3.5;
    private String[] directions = { "Vertical", "Horizontal", "Horizontal", "Vertical" };
    private int currDirection = new Random().nextInt(4);

    public GreenGhost(String greenGhostFile) {
        super(greenGhostFile);
    }

    public void changeEnemyDirection() {
        this.stepSize *= -1;
        this.stepSizeFrenzy += 1;
    }

    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveGhost(directions[currDirection], stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveGhost(directions[currDirection], stepSizeFrenzy);
        }
        drawEnemies(player);
    }
}
