package image;

import javax.swing.ImageIcon;

import constants.Constants;

/**
 * Makes it easier to assign images to models.
 */

public class ImageFactory {
	
	public static ImageIcon createImage(Image image) {
		
		ImageIcon imageIcon = null;
		
		switch(image) {
		case BACKGROUND:
			imageIcon = new ImageIcon(Constants.BACKGROUND_IMAGE_URL);
			break;
			
		case ENEMY:
			imageIcon = new ImageIcon(Constants.ENEMY_IMAGE_URL);
			break;
			
		case LASER:
			imageIcon = new ImageIcon(Constants.LASER_IMAGE_URL);
			break;
			
		case SPACESHIP:
			imageIcon = new ImageIcon(Constants.SPACESHIP_IMAGE_URL);
			break;
			
		case BOMB:
			imageIcon = new ImageIcon(Constants.BOMB_IMAGE_URL);
			break;
			
		default:
			return null;
		}
		return imageIcon;
	}
}
