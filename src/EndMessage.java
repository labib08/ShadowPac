import bagel.Window;
import bagel.util.Point;
import bagel.Font;

public class EndMessage {
    private final int WIN_SCORE_1 = 800;
    private final Font gameNameFont = new Font("res/FSO8BITR.TTF", 64);
    private double fontWidth = gameNameFont.getWidth("res/FSO8BITR.TTF");
    private final String GAME_OVER_TEXT = "GAME OVER!";
    private final String WELL_DONE_TEXT = "WELL DONE!";
    private final Point END_MESSAGE_TEXT_POINT = new Point(Window.getWidth() - fontWidth + 64, Window.getHeight() / 2.0);

    /**
     * Performs a state update.
     * If the player reaches the winning score required for level1 then "Well Done!"
     * is written on the screen, otherwise "Game Over" is shown.
     * @param score This parameter is the score gained by the player.
     */
    protected void update(int score) {
        if (score == WIN_SCORE_1) {
            gameNameFont.drawString(WELL_DONE_TEXT, END_MESSAGE_TEXT_POINT.x, END_MESSAGE_TEXT_POINT.y);
        } else {
            gameNameFont.drawString(GAME_OVER_TEXT, END_MESSAGE_TEXT_POINT.x, END_MESSAGE_TEXT_POINT.y);
        }
    }
}
