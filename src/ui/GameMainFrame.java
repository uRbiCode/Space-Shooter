package ui;

import javax.swing.JFrame;

import constants.Constants;
import image.Image;
import image.ImageFactory;
/**
 * Called by Game to create a frame and parameterize it. Initializes a GamePanel.
 */
public class GameMainFrame extends JFrame {
	
	public GameMainFrame() {
		initializeLayout();
	}

	private void initializeLayout() {
		
		add(new GamePanel());

		setTitle(Constants.TITLE);
		setIconImage(ImageFactory.createImage(Image.SPACESHIP).getImage());
		
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
