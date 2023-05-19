import bagel.Font;
import bagel.util.Point;
public class StartMessage {
    private final Font gameNameFont = new Font("res/FSO8BITR.TTF", 60);
    private final Font messageFont = new Font("res/FSO8BITR.TTF", 24);
    private static final Point GAME_TEXT_POINT = new Point(260, 250);
    private static final Point TEXT_POINT_ONE = new Point(320, 440);
    private static final Point TEXT_POINT_TWO = new Point(320, 480);
    private static final String GAME_NAME = "SHADOW PAC";
    private static final String MESSAGE_ONE = "PRESS SPACE TO START";
    private static final String MESSAGE_TWO = "USE ARROW KEYS TO MOVE";

    /**
     * This method performs state update.
     * Draws the starting message.
     */
    protected void update(){
        gameNameFont.drawString(GAME_NAME, GAME_TEXT_POINT.x, GAME_TEXT_POINT.y);
        messageFont.drawString(MESSAGE_ONE, TEXT_POINT_ONE.x, TEXT_POINT_ONE.y);
        messageFont.drawString(MESSAGE_TWO, TEXT_POINT_TWO.x, TEXT_POINT_TWO.y);
    }
}
