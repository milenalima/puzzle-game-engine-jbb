package jbb.engine;

/**
 * A Position is a representation of a pair of coordinates on the board.
 * Every Tile will have a position.
 * @author Boris Ionine
 */
public class Position {
	private int row;
	private int col;
	
	/**
	 * Constructor. Initialises the instance variables row and col.
	 * @param row
	 * @param col
	 */
	public Position(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Returns the row.
	 * @return row
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Returns the column.
	 * @return col
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * Sets the row.
	 * @param row
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	/**
	 * Sets the column.
	 * @param col
	 */
	public void setCol(int col)
	{
		this.col = col;
	}
	
	/**
	 * determine if pos given is above the this
	 * @param pos
	 */
	public boolean isNorthOf(Position pos) {
		return row < pos.row;
	}
	
	/**
	 * determine if pos given is below the this
	 * @param pos
	 */
	public boolean isSouthOf(Position pos) {
		return row > pos.row;
	}
	
	/**
	 * determine if pos given is to the right of this
	 * @param pos
	 */
	public boolean isEastOf(Position pos) {
		return col > pos.col;
	}

	/**
	 * determine if pos given is to the left of this
	 * @param pos
	 */
	public boolean isWestOf(Position pos) {
		return col < pos.col;
	}

	/**
	 * Determines if two positions have the same coordinates
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Position)) return false;
		Position p = (Position) obj;
		return p.row == this.row && p.col == this.col;
	}
}
