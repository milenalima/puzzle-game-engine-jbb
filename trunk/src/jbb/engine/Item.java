package jbb.engine;


/**
 * The Item class consist of an Item that the Avatar can pick up.
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
	 * The method will alter an Avatar based on the type of Avatar that picks it up.
	 * @return true if the item is to be removed from the field
	 * @return false if the item is to remain on the field.
	 */
	public abstract boolean pickedUp(Avatar picker);
}
