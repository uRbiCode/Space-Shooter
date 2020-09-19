package constants;

/**
 * It holds all the constants. Both number values needed to calculate the games physics and URLs used to print images.
 */
public class Constants {
	
	private Constants() {
		
	}
	
	public static final String TITLE = "Space Shooter";
	public static final int BOARD_WIDTH = 900;
	public static final int BOARD_HEIGHT = 750;
	public static final int BOARD_PADDING = 50;
	
	public static String BACKGROUND_IMAGE_URL = "images/Background.png";
	public static String SPACESHIP_IMAGE_URL = "images/SpaceShip.png";
	public static String LASER_IMAGE_URL = "images/Laser.png";
	public static String ENEMY_IMAGE_URL = "images/Enemy.png";
	public static String BOMB_IMAGE_URL = "images/Bomb.png";
		
	public static final int GAME_SPEED = 10;
	
	public static final int SPACESHIP_WIDTH = 40;
	public static final int SPACESHIP_HEIGHT = 29;
	
	public static final int LASER_HORIZONTAL_TRANSLATION = 4;
	
	public static final int ENEMY_WIDTH = 32;
	public static final int ENEMY_HEIGHT = 26;
	public static final int ENEMY_INIT_X = 270;
	public static final int ENEMY_INIT_Y = 100;
	public static final int ENEMY__ROW = 4;
	public static final int ENEMY_COLUMN = 8;
	public static final int GO_DOWN = 30;
	
	public static final int BOMB_HEIGHT = 6;
	public static final double BOMB_DROPPING_PROBABILITY = 0.0005;
	
	public static final String GAME_OVER = "GAME OVER!";
	public static final String WIN = "WIN!";
}
