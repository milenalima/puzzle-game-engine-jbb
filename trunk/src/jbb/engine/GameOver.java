package jbb.engine;

@SuppressWarnings("serial")
/**
 * Game over is thrown whenever the Game is required to end.
 * @author Jonathan Gravel
 */
public class GameOver extends Exception {

	public GameOver(String message) {
		super(message);
	}

}
