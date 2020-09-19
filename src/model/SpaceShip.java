package model;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import constants.Constants;
import image.Image;
import image.ImageFactory;
/**
 * Represents ship that is controllable by a player.
 */
public class SpaceShip extends Sprite {
	
	public SpaceShip(){
		initialize();
	}
	
	private void initialize() {

		ImageIcon imageIcon = ImageFactory.createImage(Image.SPACESHIP);
		setImage(imageIcon.getImage());
		
		int start_x = Constants.BOARD_WIDTH / 2 - Constants.SPACESHIP_WIDTH/2;
		int start_y = Constants.BOARD_HEIGHT - 100;
		
		setX(start_x);
		setY(start_y);
	}

	@Override
	public void move() {
		
		x += dx;
		if(x < Constants.SPACESHIP_WIDTH) {
			x = Constants.SPACESHIP_WIDTH;
		}
		if(x >= Constants.BOARD_WIDTH - 2 * Constants.SPACESHIP_WIDTH) {
			x = Constants.BOARD_WIDTH - 2 * Constants.SPACESHIP_WIDTH;
		}
	}
	/**
	 * Handles the case when a user presses the key.
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}	
	}
	/**
	 * Handles the case when a user presses the key.
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = -2;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
		
	}
}