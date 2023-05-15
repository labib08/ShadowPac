import bagel.*;
import bagel.util.Rectangle;
import bagel.util.Point;

public class Enemy {
    private final int MAX_ENEMIES = 266;
    private Image enemyImage;
    private Image frenzyImage = new Image("res/ghostFrenzy.png");
    private double heightFrenzy = frenzyImage.getHeight();
    private double widthFrenzy = frenzyImage.getWidth();
    private double[] enemyXCoordinates = new double[MAX_ENEMIES];
    private double[] enemyYCoordinates = new double[MAX_ENEMIES];
    private double heightEnemy;
    private double widthEnemy;
    private int xCount = 0;
    private int yCount = 0;
    private double ghostX = -100;
    private double ghostY = -100;
    private double lastGhostX;
    private double lastGhostY;
    private double initialPosX;
    private double initialPosY;
    private boolean stopEnemy = false;
    private final String HORIZAONTAL = "Horizontal";
    private final String VERTICAL = "Vertical";
    public final static int POINTS = 30;

    public Enemy(String ghostFile) {
        this.enemyImage = new Image(ghostFile);
        this.heightEnemy = enemyImage.getHeight();
        this.widthEnemy = enemyImage.getWidth();
    }

    public void setPosition(double ghostX, double ghostY) {
        this.ghostX = ghostX;
        this.ghostY = ghostY;
        initialPosX = ghostX;
        initialPosY = ghostY;
    }

    public void moveGhost(String direction, double stepSize) {
        lastGhostX = ghostX;
        lastGhostY = ghostY;
        if (direction.equals(VERTICAL)) {
            this.ghostY += stepSize;
        } else if (direction.equals(HORIZAONTAL)) {
            this.ghostX += stepSize;
        }
    }

    public Point getLastPoint() {
        return new Point(lastGhostX, lastGhostY);
    }

    public double getGhostX() {
        return ghostX;
    }

    public double getGhostY() {
        return ghostY;
    }

    public void resetGhost() {
        enemyXCoordinates = new double[MAX_ENEMIES];
        enemyYCoordinates = new double[MAX_ENEMIES];
        xCount = 0;
        yCount = 0;
    }

    public void checkCollisionPlayer(Player player, boolean isFrenzy) {
        if (new Rectangle(ghostX, ghostY, widthEnemy, heightEnemy).intersects(player.getPlayerRectangle())) {
            if (!isFrenzy) {
                player.resetPosition();
                player.setLives(player.getLives() - 1);
            } else {
                ghostX = -100;
                ghostY = -100;
                stopEnemy = true;
                player.incrementScoreEnemy();
            }
        }
    }

    public void stopIntersection(double ghostX, double ghostY) {
        this.ghostX = ghostX;
        this.ghostY = ghostY;
    }

    public boolean getStopEnemy() {
        return stopEnemy;
    }

    public Rectangle getEnemyRectangle() {
        return new Rectangle(ghostX, ghostY, widthEnemy, heightEnemy);
    }

    /**
     * Draws the Enemy and if there is an collision, it takes one life away from the
     * player.
     */
    public void drawEnemies(Player player) {
        if (player.getFrenzy()) {
            frenzyImage.drawFromTopLeft(ghostX, ghostY);
        } else {
            if (ghostX == -100 && ghostY == -100) {
                ghostX = initialPosX;
                ghostY = initialPosY;
            }
            stopEnemy = false;
            enemyImage.drawFromTopLeft(ghostX, ghostY);
        }
        checkCollisionPlayer(player, player.getFrenzy());
    }
}
