package model;

import javax.swing.ImageIcon;

import constants.Constants;
import image.Image;
import image.ImageFactory;

/**
 * Represents enemy ships missiles.
 */

public class Bomb extends Sprite{

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.BOMB);
		setImage(imageIcon.getImage());	
	}
	@Override
	public void move() {		
		this.y++;
		if(y >= Constants.BOARD_HEIGHT - Constants.BOMB_HEIGHT) {
			die();
		}
	}
}