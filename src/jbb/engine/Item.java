/**
 * The Item class consist of an Item that the Avatar can pick up to get points.
 */

package jbb.engine;

public abstract class Item extends Tile {

	protected int pointValue;
		
	/**
	 * The constructor create an item
	 */
	public Item(Position position, Board board) {
		super(position, board);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * returns the amount of points the item is worth
	 * @return
	 */
	public int getPointValue(){
		return pointValue;
		
	}

	/**
	 * sets the amount of points the item is worth
	 * @return
	 */
	public void setPointValue(){
		
	}
	
	/**
	 * The method will alter the Avatar based on if the Item has been picked up
	 */
	public abstract void pickedUp(Avatar picker);
}
