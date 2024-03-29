package jbb.engine;

import javax.swing.JOptionPane;

import jbb.engine.mouseland.MouseGameView;
import jbb.engine.pacman.PacGameView;
import jbb.engine.pipes.PipeGameView;

/**
 * Asks the user which game he would like to play and then launches it.
 * @author Jonathan Gravel
 */
public class GameLauncher {

	/**
	 * execute
	 */
	public static void main(String[] args) {
		String[] options = {"PacMan", "MouseLand", "Pipes"};
		int result = JOptionPane.showOptionDialog(null, "Select the game you would like to play",
				"Game Launcher", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
				null);
		switch (result) {
		case 0:
			PacGameView.main(null);
			break;
		case 1:
			MouseGameView.main(null);
			break;
		case 2:
			PipeGameView.main(null);
			break;
		case JOptionPane.CLOSED_OPTION:
			// shut down the application
			System.exit(0);
		}
			
	}

}
