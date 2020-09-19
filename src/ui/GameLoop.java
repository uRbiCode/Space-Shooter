package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Calls the assigned GamePanel to perform update() and repaint() functions.
 */
public class GameLoop implements ActionListener {

		private GamePanel gamePanel;
		
		public GameLoop(GamePanel gamePanel) {
			this.gamePanel = gamePanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.gamePanel.doOneLoop();
		}
}
