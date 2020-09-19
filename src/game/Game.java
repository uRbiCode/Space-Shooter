package game;

import java.awt.EventQueue;

import ui.GameMainFrame;

/**
 * Main function to run the game frame. Consists of one lambda expression to initialize the GameMainFrame.
 * @author Patryk Urbañski 
 */
public class Game {

	public static void main(String[] args) {

		EventQueue.invokeLater( () -> {
			new GameMainFrame();
		});

	}

}