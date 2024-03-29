package jbb.engine;

import javax.swing.ImageIcon;

/**
 * The NPC is an Avatar that generally has the primary goal of ruining the life of
 * the hero by attempting to kill it. The NPC operates using A.I. to dodge walls and
 * reach the hero.
 * @author Jonathan Gravel
 */
@SuppressWarnings("serial")
public abstract class NPC extends Avatar{

	/**
	 * @param image pictorial representation of the NPC to be used on a Tile
	 * @param lives represents the starting number of lives of the NPC
	 * @param position represents the position of the NPC on the Board
	 * @param board represents the board that is associated to this NPC
	 */
	public NPC(ImageIcon image, int lives, Position position, Board board) {
		super(image, lives, position, board);
	}
	
	/**
	 * NPCs will move towards given position and find a way around obstructing walls.
	 * This method defines stupid AI that may move repeatedly to the same position.
	 */
	public Position getNextPosition(Position position){
		try {
			return super.getNextPosition(position);
		} catch (IllegalArgumentException ex1) {
			int gotoRow = position.getRow();
			int gotoCol = position.getCol();
			int myRow = this.position.getRow();
			int myCol = this.position.getCol();
			// see where the NPC can move
			if (gotoRow >= myRow) {// try to move down
				try {
					return getNextPosition(BOTTOM);
				} catch (IllegalArgumentException ex2) {
					if (gotoCol >= myCol) { // try to move right
						try {
							return getNextPosition(RIGHT);
						} catch (IllegalArgumentException ex3) {
							try {
								return getNextPosition(TOP);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return getNextPosition(LEFT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					} else { // try to move left
						try {
							return getNextPosition(LEFT);
						} catch (IllegalArgumentException ex3) {
							try {
								return getNextPosition(TOP);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return getNextPosition(RIGHT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					}
				}
			} else { // try to move up
				try {
					return getNextPosition(TOP);
				} catch (IllegalArgumentException ex2) {
					if (gotoCol > myCol) { // try to move right
						try {
							return getNextPosition(RIGHT);
						} catch (IllegalArgumentException ex3) {
							try {
								return getNextPosition(BOTTOM);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return getNextPosition(LEFT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					} else { // try to move left
						try {
							return getNextPosition(LEFT);
						} catch (IllegalArgumentException ex3) {
							try {
								return getNextPosition(BOTTOM);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return getNextPosition(RIGHT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					}
				}
			}
		} return this.position; // Don't move at all
	}
}