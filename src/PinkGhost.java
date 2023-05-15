import java.util.Random;
import bagel.util.Vector2;

public class PinkGhost extends Enemy {
    private double stepSize = 3;
    private double stepSizeFrenzy = 2.5;
    private String[] directions = { "Vertical", "Horizontal", "Horizontal", "Vertical" };
    private int randDirection;

    public PinkGhost(String pinkGhostFile) {
        super(pinkGhostFile);
    }

    public void changeEnemyDirection() {
        randDirection = new Random().nextInt(4);
        stepSize *= -1;
        stepSizeFrenzy *= -1;
    }

    protected void update(Player player) {
        if (!getStopEnemy()) {
            moveGhost(directions[randDirection], stepSize);
        } else if (!getStopEnemy() && player.getFrenzy()) {
            moveGhost(directions[randDirection], stepSizeFrenzy);
        }
        drawEnemies(player);
    }
}
