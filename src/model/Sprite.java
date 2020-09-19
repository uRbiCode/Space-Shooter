package  model;

import java.awt.Image;

/**
 * Abstract class that is a parent for every model in the game.
 */
public abstract class Sprite {
	
	private Image image;
	private boolean dead = false;
	
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	
	public abstract void move();
	
	public Sprite() {
		this.dead = false;
	}
	
	public void die() {
		this.dead = true;
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
