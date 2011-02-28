/**
 * The Abstract Avatar class defines the controllable tile on the board: it is the only one that will be moved.
 * Avatar may pick up items, but not go through walls (generally). The behaviour is further developed in the subclasses Hero and NPC.
 */

package jbb.engine;

import javax.swing.ImageIcon;


public abstract class Avatar extends Tile {
	
	private int hitPoints;
	private int lives;
	
	/**
	 * Constructor for Avatar using specified Position.
	 * 
	 * @param image pictorial representation of the Avatar to be used on a Tile
	 * @param hitPoints represents the starting health of the Avatar
	 * @param lives represents the starting number of lives of the Avatar
	 * @param board represents the board that is associated to this Avatar
	 * @param position represents the position of the Avatar on the Board
	 */
	public Avatar(ImageIcon image, int hitPoints, int lives, Position position, Board board) {
		super(position, board);
		setImage(image);
		this.hitPoints = hitPoints;
		this.lives = lives;
	}
	
	/**
	 * this function checks to see if the tile has an item that can be picked up by
	 * the Avatar. If so, the item will do what it is supposed to.
	 * 
	 * @param position of tile
	 * @return true if the object can be picked up by Avatar
	 */
	protected abstract boolean hasGoodie(Position position);
	
	/**
	 * Determines if the position given is possible (accessible, within the 
	 * boundaries, within one tile of movement), if so, move to this position,
	 * otherwise, calculate what is assumed to be the desired direction
	 * 
	 * @param position position to move to or towards
	 * @throws IllegalArgumentException if move is out of bounds, or if the Avatar
	 * will move into a wall.
	 * @return true if the Avatar picks up an object where it moved.
	 */
	public boolean moveTo(Position position) throws IllegalArgumentException{
		try {
			if (canMoveTo(position)) {
				boolean pickup = hasGoodie(position);
				
				this.setPosition(position);
				return pickup;
			}
		} catch (ArrayIndexOutOfBoundsException exc) {
			throw new IllegalArgumentException("Not a possible move");
		}
		// determine which direction is wanted
		/*
		 * + - - - - - - - - - -
		 * + + - - - - - - - - +
		 * + + + - - - - - - + +
		 * + + + + - - - - + + +
		 * + + + + + - - + + + +
		 * + + + + +   + + + + +
		 * + + + + - - + + + + +
		 * + + + - - - - + + + +
		 * + + - - - - - - + + +
		 * + - - - - - - - - + +
		 * - - - - - - - - - - +
		 * 
		 * The pluses and minuses indicate which direction (up,down,left,right)
		 * to move in depending on where the user clicked.
		 */
		int gotoRow = position.getRow();
		int gotoCol = position.getCol();
		int myRow = this.position.getRow();
		int myCol = this.position.getCol();
		if (myRow == gotoRow && myCol == gotoCol) {
			throw new IllegalArgumentException("Cannot move to same position");
		}
		try {
			if (gotoRow <= myRow) { // we are moving upwards
				if (gotoCol <= myCol) { // we are moving left or up
					if (myCol-gotoCol >= myRow-gotoRow) return moveTo(LEFT);
					else return moveTo(TOP);
				} else { // we are moving right or up
					if (gotoCol-myCol > myRow-gotoRow) return moveTo(RIGHT);
					else return moveTo(TOP);
				}
			} else { // we are moving downwards
				if (gotoCol <= myCol) { // we are moving left or down
					if (myCol-gotoCol > gotoRow-myRow) return moveTo(LEFT);
					else return moveTo(BOTTOM);
				} else { // we are moving right or down
					if (gotoCol-myCol > gotoRow-myRow) return moveTo(RIGHT);
					else return moveTo(BOTTOM);
				}
			}
		} catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException("Not a possible move");
		}
	}
	
	/**
	 * Tells the Avatar to move in a certain direction if the move is allowed.
	 * 
	 * @param direction will be either TOP, BOTTOM, RIGHT or LEFT (constants defined in Tile class)
	 * @return true if avatar picks up something that it may use
	 * @throws IllegalArgumentException when an "Invalid direction" is received (such as TOP_RIGHT)
	 * or if the "Move is not permitted" due to a Wall for example.
	 */
	protected boolean moveTo(int direction) throws IllegalArgumentException {
		boolean pickup;
		Position newPos;
		switch (direction) {
			case TOP:
				newPos = new Position(position.getRow()-1, position.getCol());
				pickup = hasGoodie(newPos);
				if (!this.canMoveTo(newPos)) throw(new IllegalArgumentException("Move is not permitted"));
				setPosition(newPos);
				return pickup;
			case BOTTOM:
				newPos = new Position(position.getRow()+1, position.getCol());
				pickup = hasGoodie(newPos);
				if (!this.canMoveTo(newPos)) throw(new IllegalArgumentException("Move is not permitted"));
				setPosition(newPos);
				return pickup;
			case LEFT:
				newPos = new Position(position.getRow(), position.getCol()-1);
				pickup = hasGoodie(newPos);
				if (!this.canMoveTo(newPos)) throw(new IllegalArgumentException("Move is not permitted"));
				setPosition(newPos);
				return pickup;
			case RIGHT:
				newPos = new Position(position.getRow(), position.getCol()+1);
				pickup = hasGoodie(newPos);
				if (!this.canMoveTo(newPos)) throw(new IllegalArgumentException("Move is not permitted"));
				setPosition(newPos);
				return pickup;
			default:
				throw(new IllegalArgumentException("Invalid direction"));
		}
	}

	/**
	 * Determines if the Tile at that position is Accessible and is adjacent to the current Position
	 * 
	 * @param position
	 * @return false if the position not accessible, true if if the new position is possible
	 */
	public boolean canMoveTo(Position position) {
		if (position.getRow() < 0 || position.getCol() < 0
				|| position.getRow() >= board.getHeight() || position.getCol() >= board.getWidth())
			return false;
		else if (!board.getTile(position).getAccessible()) return false;
		else if ((Math.abs(position.getRow() - this.position.getRow()) == 1 && Math.abs(position.getCol() - this.position.getCol()) == 0))
			return true; // moved one space vertically
		else if ((Math.abs(position.getRow() - this.position.getRow()) == 0 && Math.abs(position.getCol() - this.position.getCol()) == 1))
			return true; // moved one space horizontally
		else return false;
	}
	
	/**
	 * Get number of hitPoints remaining
	 * 
	 * @return hitPoints value
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	
	/**
	 * Subtracts current hitPoints by amount specified
	 * 
	 * @param amount how much damage is taken
	 * @return true if the number of HitPoints has reached 0 or less
	 */
	public boolean damageHitPoints(int amount) {
		if (hitPoints-amount <= 0) {
			return true;
		}
		hitPoints -= amount;
		return false;
	}
	
	/**
	 * Increase current hitPoints by amount specified
	 */
	public void healHitPoints(int amount) {
		hitPoints += amount;
	}
	
	/**
	 * Get number of lives remaining
	 * 
	 * @return
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Increment lives by 1
	 */
	public void addLife() {
		lives++;
	}
	
	/**
	 * Decrement lives by 1
	 * 
	 * @return true if the number of lives has reached 0 or less
	 */
	public boolean removeLife() {
		lives--;
		if (lives == 0) {
			return true;
		}
		return false;
	}
}
