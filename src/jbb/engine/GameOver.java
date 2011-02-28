/**
 * Game over is thrown whenever the Game is required to end
 */

package jbb.engine;

@SuppressWarnings("serial")
public class GameOver extends Exception {

	public GameOver(String message) {
		super(message);
	}

}
