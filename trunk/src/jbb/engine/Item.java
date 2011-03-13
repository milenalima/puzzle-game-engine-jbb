package jbb.engine;

import javax.swing.ImageIcon;

/**
 * The Item class consist of an Item that the Avatar can pick up to get points.
 * @author Jonathan Gravel
 */
@SuppressWarnings("serial")
public abstract class Item extends Tile {
	
	/**
	 * @param position
	 * @param board
	 */
	public Item(Position position, Board board) {
		super(position, board);
	}
	
	/**
	 * The method will alter the Avatar based on if the Item has been picked up
	 */
	public abstract void pickedUp(Avatar picker);
}
