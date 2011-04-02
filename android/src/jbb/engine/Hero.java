
package jbb.engine;


/**
 * Hero is the user controlled character in the game. It collects points.
 * @author Jonathan Gravel
 */

public abstract class Hero extends Avatar {
	
	private int points;
	
	/**
	 * @param image Image to use on the tile
	 * @param lives Lives allocated at the start of the game
	 * @param position Initial position
	 * @param board
	 */
	public Hero(int lives, Position position, Board board) {
		super(lives, position, board);
		points = 0;
	}
	
	/**
	 * Increase point count by amount.
	 * @param amount
	 */
	public void addPoints(int amount) {
		points += amount;
	}
	
	/**
	 * Decrease point count by amount.
	 * @param amount
	 */
	public void subtractPoints(int amount) {
		points -= amount;
	}
	
	/**
	 * @return points collected
	 */
	public int getPoints() {
		return points;
	}
	
}
