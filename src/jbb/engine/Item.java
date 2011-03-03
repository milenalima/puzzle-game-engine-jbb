package jbb.engine;

import javax.swing.ImageIcon;

/**
 * The Item class consist of an Item that the Avatar can pick up to get points.
 * @author Jonathan Gravel
 */
@SuppressWarnings("serial")
public abstract class Item extends Tile {

	protected int pointValue;
		
	/**
	 * @param position 
	 * @param board
	 */
	public Item(ImageIcon image, Position position, Board board) {
		this(position, board, 0); // not all Items give points
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The constructor create an item with given point value
	 * @param position
	 * @param board
	 * @param pointValue
	 */
	public Item(Position position, Board board, int pointValue) {
		super(position, board);
		this.pointValue = pointValue;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * returns the amount of points the item is worth
	 * @return pointValue
	 */
	public int getPointValue(){
		return pointValue;
	}

	/**
	 * sets the amount of points the item is worth
	 */
	public void setPointValue(int pointValue){
		this.pointValue = pointValue;
	}
	
	/**
	 * The method will alter the Avatar based on if the Item has been picked up
	 */
	public abstract void pickedUp(Avatar picker);
}
