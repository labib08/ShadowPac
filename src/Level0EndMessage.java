import bagel.Window;
import bagel.util.Point;
import bagel.Font;

public class Level0EndMessage {

    private final Font gameNameFont = new Font("res/FSO8BITR.TTF", 64);
    private double fontWidth = gameNameFont.getWidth("res/FSO8BITR.TTF");
    private final String LEVEL_COMPLETE_TEXT = "LEVEL COMPLETE!";
    private final Point END_MESSAGE_TEXT_POINT = new Point(Window.getWidth() - fontWidth - 70, Window.getHeight() / 2);

     /**
     * Draws the Level0EndMessage.
     */
    protected void update() {
        gameNameFont.drawString(LEVEL_COMPLETE_TEXT, END_MESSAGE_TEXT_POINT.x, END_MESSAGE_TEXT_POINT.y);
    }
}
