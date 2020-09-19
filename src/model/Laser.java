package model;

import javax.swing.ImageIcon;

import constants.Constants;
import image.Image;
import image.ImageFactory;
/**
 * Represents missiles fired by a player.
 */
public class Laser extends Sprite{
	
	public Laser() {
		
	}

	public Laser(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize() {
		
		ImageIcon imageIcon = ImageFactory.createImage(Image.LASER);
		setImage(imageIcon.getImage());
		
		setX(x + Constants.SPACESHIP_WIDTH / 2);
		setY(y);
	}

	@Override
	public void move() {

		this.y -= Constants.LASER_HORIZONTAL_TRANSLATION;
		if(this.y < 0) {
			this.die();
		}
	}
	
}
