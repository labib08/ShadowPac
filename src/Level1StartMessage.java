import bagel.util.Point;
import bagel.Font;

public class Level1StartMessage {
    /**
     * Sets the font and font size for the starting texts.
     */
    private final Font messageFont = new Font("res/FSO8BITR.TTF", 40);
    /**
     * Sets the Coordinates of the starting texts.
     */
    private static final Point TEXT_POINT_ONE = new Point(200, 350);
    private static final Point TEXT_POINT_TWO = new Point(200, 400);
    private static final Point TEXT_POINT_THREE = new Point(200, 450);
    /**
     * Starting texts.
     */
    private static final String MESSAGE_ONE = "PRESS SPACE TO START";
    private static final String MESSAGE_TWO = "USE ARROW KEYS TO MOVE";
    private static final String MESSAGE_THREE = "EAT THE PELLET TO ATTACK";

    /**
     * Draws the starting texts.
     */
    protected void update() {

        messageFont.drawString(MESSAGE_ONE, TEXT_POINT_ONE.x, TEXT_POINT_ONE.y);
        messageFont.drawString(MESSAGE_TWO, TEXT_POINT_TWO.x, TEXT_POINT_TWO.y);
        messageFont.drawString(MESSAGE_THREE, TEXT_POINT_THREE.x, TEXT_POINT_THREE.y);
    }
}
