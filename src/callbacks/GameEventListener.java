package callbacks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ui.GamePanel;

/**
 * This class is responsible for assigning the given GamePanel as a listener.
 */
public class GameEventListener extends KeyAdapter {
	
	private GamePanel board;
	
	public GameEventListener(GamePanel board) {
		this.board = board;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		this.board.keyReleased(e);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.board.keyPressed(e);
	}
}