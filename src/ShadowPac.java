import bagel.*;
import java.io.FileReader;
import java.util.Scanner;
import bagel.util.Point;

/**
 * Skeleton Code for SWEN20003 Project 1, Semester 1, 2023
 *
 * Please enter your name below
 * 
 * @author Itmam Khan Labib
 */
public class ShadowPac extends AbstractGame {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final int WIN_SCORE_0 = 1210;
    private final int WIN_SCORE_1 = 800;
    public final Player player = new Player();
    private final RedGhost redGhost = new RedGhost("res/ghostRed.png");
    private final Ghost ghost = new Ghost();
    private final BlueGhost blueGhost = new BlueGhost("res/ghostBlue.png");
    private final GreenGhost greenGhost = new GreenGhost("res/ghostGreen.png");
    private final PinkGhost pinkGhost = new PinkGhost("res/ghostPink.png");
    private final Wall wall = new Wall("res/wall.png");
    private final Dot dot = new Dot("res/dot.png");
    private final Cherry cherry = new Cherry("res/cherry.png");
    private final Pellet pellet = new Pellet("res/pellet.png");
    private final StartMessage startMessage = new StartMessage();
    private final EndMessage endMessage = new EndMessage();
    private final Level1StartMessage level1StartMessage = new Level1StartMessage();
    private final Level0EndMessage level0EndMessage = new Level0EndMessage();
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");
    private final Font scoreFont = new Font("res/FSO8BITR.TTF", 20);
    private static final Point SCORE_POINT = new Point(25, 25);
    private final Image heartImage = new Image("res/heart.png");
    private static final Point HEART_POINT_ONE = new Point(900, 10);
    private static final Point HEART_POINT_TWO = new Point(930, 10);
    private static final Point HEART_POINT_THREE = new Point(960, 10);
    private boolean flagCsv = true;
    private boolean flag = false;
    private int currLevel = 0;
    private double currScreen = 0;
    private int lives;
    private int score;
    private int frame = 0;
    private double xCoordinate;
    private double yCoordinate;

    private String[] levels = { "res/level0.csv", "res/level1.csv" };

    /**
     * Constructs a new ShadowPac game object, and refers to its superclass.
     */

    public ShadowPac() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * Method used to read file and create objects. It gets
     * the coordinates of Player, Walls, Dots, Ghosts, Cherries and pellet.
     */
    private void readCSV() {
        try (Scanner file = new Scanner(new FileReader(levels[currLevel]))) {
            while (file.hasNextLine()) {
                String[] gameData = file.nextLine().split(",");
                xCoordinate = Double.parseDouble(gameData[1]);
                yCoordinate = Double.parseDouble(gameData[2]);
                setBoardDetails(gameData[0], xCoordinate, yCoordinate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // A flag variable to make sure the program reads the csv file only once.
        flagCsv = false;
    }

    /**
     * This method sets up coordinates of the Player, Walls, Dots, Pellet, Cherries
     * and Ghosts.
     * 
     * @param entity      This parameter is the game entity, eg: Player, Walls,
     *                    Dots, Pellet, Cherries and Ghosts.
     * @param xCoordinate This parameter is the X-Coordinate of the game entity.
     * @param yCoordinate This parameter is the Y-Coordinate of the game entity.
     */
    public void setBoardDetails(String entity, double xCoordinate, double yCoordinate) {
        if (entity.equals("Player")) {
            player.setPosition(xCoordinate, yCoordinate);
        } else if (entity.equals("Wall")) {
            wall.setStatEntityXCoordinates(xCoordinate);
            wall.setStatEntityYCoordinates(yCoordinate);
        } else if (entity.equals("Ghost")) {
            ghost.setGhostXCoordinates(xCoordinate);
            ghost.setGhostYCoordinates(yCoordinate);
        } else if (entity.equals("GhostRed")) {
            redGhost.setPosition(xCoordinate, yCoordinate);
        } else if (entity.equals("GhostBlue")) {
            blueGhost.setPosition(xCoordinate, yCoordinate);
        } else if (entity.equals("GhostPink")) {
            pinkGhost.setPosition(xCoordinate, yCoordinate);
        } else if (entity.equals("GhostGreen")) {
            greenGhost.setPosition(xCoordinate, yCoordinate);
        } else if (entity.equals("Dot")) {
            dot.setStatEntityXCoordinates(xCoordinate);
            dot.setStatEntityYCoordinates(yCoordinate);
        } else if (entity.equals("Cherry")) {
            cherry.setStatEntityXCoordinates(xCoordinate);
            cherry.setStatEntityYCoordinates(yCoordinate);
        } else if (entity.equals("Pellet")) {
            pellet.setStatEntityXCoordinates(xCoordinate);
            pellet.setStatEntityYCoordinates(yCoordinate);
        }
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     * 
     * @param input This parameter gives access to input devices (keyboard and
     *              mouse).
     */
    @Override
    protected void update(Input input) {
        if (flagCsv) {
            readCSV();
        }
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

        // Decides and sets up the screen that is going to be rendered.
        if (currScreen == 0 || currScreen == 1) {
            setGameScreen(input);
        }
        if (currScreen == 0.5) {
            // Only displays this message for 300 frames.
            if (frame <= 300) {
                level0EndMessage.update();
            } else {
                level1StartMessage.update();
                if (input.wasPressed(Keys.SPACE)) {
                    currScreen = 1;
                }
            }
            frame++;
        }
    }

    /**
     * This method draws the current score of the player.
     * 
     * @param score This parameter is the points gained by the player.
     */
    public void drawScore(int score) {
        scoreFont.drawString("SCORE " + score, SCORE_POINT.x, SCORE_POINT.y);
    }

    /**
     * This method draws the remaining lives of the player.
     * 
     * @param lives This parameter is the number of lives remaining for the player.
     */
    public void drawHeart(int lives) {
        if (lives == 3) {
            heartImage.drawFromTopLeft(HEART_POINT_ONE.x, HEART_POINT_ONE.y);
            heartImage.drawFromTopLeft(HEART_POINT_TWO.x, HEART_POINT_TWO.y);
            heartImage.drawFromTopLeft(HEART_POINT_THREE.x, HEART_POINT_THREE.y);
        }
        if (lives == 2) {
            heartImage.drawFromTopLeft(HEART_POINT_TWO.x, HEART_POINT_TWO.y);
            heartImage.drawFromTopLeft(HEART_POINT_THREE.x, HEART_POINT_THREE.y);
        }
        if (lives == 1) {
            heartImage.drawFromTopLeft(HEART_POINT_THREE.x, HEART_POINT_THREE.y);
        }
    }

    /**
     * When the player advances to the next level, the board of the previous level
     * is cleared.
     */
    public void clearBoard() {
        currScreen = 0.5;
        flagCsv = true;
        currLevel = 1;
        ghost.resetGhost();
        dot.resetStatEntity();
        wall.resetStatEntity();
        player.setScore(0);
        player.setLives(3);
    }

    /**
     * This method draws the board of the current level.
     * 
     * @param input  This parameter gives access to input devices (keyboard and
     *               mouse).
     * @param player This parameter is the object of the class Player, which is
     *               represented by the PacMan.
     * @param lives  This parameter is the number of lives remaining for the player.
     * @param score  This parameter is the points gained by the player.
     */
    public void drawBoard(Input input, Player player, int lives, int score) {
        drawScore(score);
        drawHeart(lives);
        player.update(input);
        dot.update(player, blueGhost, redGhost, greenGhost, pinkGhost);
        redGhost.update(player);
        blueGhost.update(player);
        greenGhost.update(player);
        pinkGhost.update(player);
        wall.update(player, blueGhost, redGhost, greenGhost, pinkGhost);
        cherry.update(player, blueGhost, redGhost, greenGhost, pinkGhost);
        pellet.update(player, blueGhost, redGhost, greenGhost, pinkGhost);
        if (currLevel == 0) {
            ghost.update(player);
        }
    }

    /**
     * This method decides which screen to display
     * 
     * @param input This parameter gives access to input devices (keyboard and
     *              mouse).
     */
    public void setGameScreen(Input input) {
        // A flag variable is used so that the game begins when the SPACE key is
        // pressed.
        if (input.wasPressed(Keys.SPACE)) {
            flag = true;
        }
        // If SPACE is pressed, the game begins.
        if (flag) {
            lives = player.getLives();
            score = player.getScore();
            // The game runs until the player loses all its lives or eats up all the dots,
            // across the two levels.
            if ((lives == 0) || (score == WIN_SCORE_1 && currLevel == 1)) {
                endMessage.update(score);
                // If the player eats up all the dots in level 0, the player advances to the
                // next level. The next level can also be started by pressing W.
                // The board is cleared and redrawn for the next level.
            } else if ((score == WIN_SCORE_0 && currLevel == 0) || (input.wasPressed(Keys.W))) {
                clearBoard();
            } else {
                drawBoard(input, player, lives, score);
            }
        } else {
            startMessage.update();
        }
    }

    /**
     * This method is the main method which creates an object of the class ShadowPac
     * and makes use
     * of the method run.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }
}
