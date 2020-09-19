package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import callbacks.GameEventListener;
import constants.Constants;
import image.Image;
import image.ImageFactory;
import model.Laser;
import model.SpaceShip;
import model.Bomb;
import model.EnemyShip;
/**
 * Performs all the calculations included in game's frontend and backend.
 */
public class GamePanel extends JPanel {
	
	private ImageIcon backgroundImage;
	private Timer timer;
	private SpaceShip spaceShip;
	private boolean inGame = true;
	private Laser laser;
	private int direction = -1;
	private List<EnemyShip> enemyShips;
	private List<Bomb> bombs;
	private Random generator;
	private String message;
	private int deaths = 0;
	private int score = 0;
	private int shields = 2;

	public GamePanel() {
		initializeLayout();
		initializeVariables();
		initializeGame();
	}
	/**
	 * Creates enemy ships.
	 */
	private void initializeGame() {
		for(int i = 0; i < Constants.ENEMY__ROW; i++) {
			for(int j = 0; j < Constants.ENEMY_COLUMN; j++) {
				EnemyShip enemyShip = new EnemyShip(Constants.ENEMY_INIT_X + 50 * j, Constants.ENEMY_INIT_Y + 50 * i);
				this.enemyShips.add(enemyShip);
			}
		}	
	}
	/**
	 * Initializes all variables needed for the game to run.
	 */
	private void initializeVariables() {
		this.generator = new Random();
		this.bombs = new ArrayList<>();
		this.enemyShips = new ArrayList<>();
		this.spaceShip = new SpaceShip();
		this.laser = new Laser();
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
		this.timer.start();
	}
	/**
	 * Sets the GamePanel as a listener and sets the GamePanel's parameters.
	 */
	private void initializeLayout() {
		
		addKeyListener(new GameEventListener(this));
		setFocusable(true);
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));		
	}
	
	private void drawPlayer(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}
	private void drawLaser(Graphics g) {
		if(!laser.isDead()) {
			g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
		}		
	}
	
	private void drawAliens(Graphics g) {
		
		for(EnemyShip enemyShip : this.enemyShips)
			if(enemyShip.isVisible())
				g.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);
	}
	
	private void drawBombs(Graphics g) {
		
		for(Bomb bomb : this.bombs)
			if(!bomb.isDead())
				g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
	}
	
	private void drawGameOver(Graphics g) {
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		Font font = new Font("Helvetica", Font.BOLD, 50);
		FontMetrics fontMetrics = this.getFontMetrics(font);
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(message, (Constants.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
				Constants.BOARD_HEIGHT / 2 -100);
		drawScores(g);
	}
	
	private void drawScores(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 20);		
		g.setFont(font);
		g.setColor(Color.gray);
		g.drawString("Score: " + score, Constants.BOARD_WIDTH - 150, 50);
		if(shields >= 0) {
			g.drawString("Shields: " + shields, 50, 50);
		}
	}
	/**
	 * Draws all the visible objects.	
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		
		doDrawing(g);
	}
	
	/**
	 * Synchronizes the game animations and calls drawing functions for each of the models and HUD elements.
	 * @param g
	 */
	private void doDrawing(Graphics g) {
			
		if(inGame) {
			drawScores(g);
			drawPlayer(g);
			drawLaser(g);
			drawAliens(g);
			drawBombs(g);
		}
		else {
			if(timer.isRunning()) {
				timer.stop();
			}
			
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	/**
	 * Calls the update() and repaint() methods.
	 */

	public void doOneLoop() {
		update();
		repaint();
	}
	
	/**
	 * Does all the calculations included in the game's logic like objects movement and collision checks, handles the collisions itself and checks if the game is supposed to end in either way.
	 */
	private void update() {
		
		if(spaceShip.isDead()) {
			inGame = false;
			message = Constants.GAME_OVER;
		}
		
		if(deaths == this.enemyShips.size()) {
			inGame = false;
			message = Constants.WIN;
		}
		
		this.spaceShip.move();
		if(!laser.isDead()) {
			int shotX = laser.getX();
			int shotY = laser.getY();
			for(EnemyShip enemyShip : enemyShips) {
				int enemyX = enemyShip.getX();
				int enemyY = enemyShip.getY();
				if(!enemyShip.isVisible() && !laser.isDead()) continue;
				if(shotX >= (enemyX) && shotX <= (enemyX + Constants.ENEMY_WIDTH) &&
						shotY >= (enemyY) && shotY <= (enemyY + Constants.ENEMY_HEIGHT)) {
					enemyShip.setVisible(false);
					laser.die();
					int tmp = 0;
					for(int i = 0; i < enemyShips.size(); i++) {
						if(!enemyShips.get(i).isVisible()) {
							tmp ++;
						}
					}
					deaths = tmp;
					score = (20 * tmp) - (40 * (2 - shields));					        
				}
			}
			this.laser.move();
		}

		for(EnemyShip enemyShip : this.enemyShips) {
			
			if(enemyShip.getX() >= Constants.BOARD_WIDTH - 2 * Constants.BOARD_PADDING && direction != -1 || enemyShip.getX() < Constants.BOARD_PADDING && direction != 1) {
				direction *= -1;
				
				Iterator<EnemyShip> enemyIterator = enemyShips.iterator();
				while(enemyIterator.hasNext()) {
					EnemyShip tmp = enemyIterator.next();
					tmp.setY(tmp.getY() + Constants.GO_DOWN);
				}
			}
			if(enemyShip.isVisible()) {
				if(enemyShip.getY() > Constants.BOARD_HEIGHT - 100 - Constants.SPACESHIP_HEIGHT) {
					spaceShip.die();
				}
				
				enemyShip.move(direction);
			}
		}
		for(EnemyShip enemyShip : this.enemyShips) {
			if(enemyShip.isVisible() && generator.nextDouble() < Constants.BOMB_DROPPING_PROBABILITY) {
				Bomb bomb = new Bomb(enemyShip.getX(), enemyShip.getY());
				this.bombs.add(bomb);
			}
		}
		
		for(Bomb bomb : this.bombs) {
			int bombX = bomb.getX();
			int bombY = bomb.getY();
			int spaceShipX = spaceShip.getX();
			int spaceShipY = spaceShip.getY();
			
			if(!bomb.isDead() && !spaceShip.isDead()) {
				if(bombX >= (spaceShipX) && bombX <= (spaceShipX + Constants.ENEMY_WIDTH) &&
						bombY >= (spaceShipY) && bombY <= (spaceShipY + Constants.ENEMY_HEIGHT)) {
					bomb.die();
					shields--;
					score -= 40;
					if(shields < 0) spaceShip.die();
				}
			}
			if(!bomb.isDead()) {
				bomb.move();
			}
		}						
	}
	/**
	 * Calls the case when a user releases the key.
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {

		this.spaceShip.keyReleased(e);
		
	}
	/**
	 * Calls the case when a user presses the key.
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {

		this.spaceShip.keyPressed(e);
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			int laserX = this.spaceShip.getX();
			int laserY = this.spaceShip.getY();
			
			if(inGame && laser.isDead()) {
				laser = new Laser(laserX, laserY);
			}
		}
		
	}
}
