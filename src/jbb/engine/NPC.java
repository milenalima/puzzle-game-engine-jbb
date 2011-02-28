/**
 * The NPC is an Avatar that generally has the primary goal of ruining the life of
 * the hero by attempting to kill it. The NPC operates using A.I. to dodge walls and
 * reach the hero.
 */

package jbb.engine;

import javax.swing.ImageIcon;


public abstract class NPC extends Avatar{

	private int damage;

	/**
	 * Constructor for NPC using specified Position.
	 * 
	 * @param image pictorial representation of the NPC to be used on a Tile
	 * @param hitPoints represents the starting health of the NPC
	 * @param lives represents the starting number of lives of the NPC
	 * @param board represents the board that is associated to this NPC
	 * @param position represents the position of the NPC on the Board
	 */
	public NPC(ImageIcon image, int hitPoints, int lives, int damage, Position position, Board board) {
		super(image, hitPoints, lives, position, board);
		this.damage = damage;
	}
	
	/**
	 * NPCs will move towards given position and find a way around obstructing walls.
	 * The current method defines stupid AI that may move repeatedly to the same position.
	 * This will be redefined later.
	 */
	public boolean moveTo(Position position) throws IllegalArgumentException {
		try {
			return super.moveTo(position);
		} catch (IllegalArgumentException ex1) {
			int gotoRow = position.getRow();
			int gotoCol = position.getCol();
			int myRow = this.position.getRow();
			int myCol = this.position.getCol();
			// see where the NPC can move
			if (gotoRow >= myRow) {// try to move down
				try {
					return moveTo(BOTTOM);
				} catch (IllegalArgumentException ex2) {
					if (gotoCol >= myCol) { // try to move right
						try {
							return moveTo(RIGHT);
						} catch (IllegalArgumentException ex3) {
							try {
								return moveTo(TOP);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return moveTo(LEFT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					} else { // try to move left
						try {
							return moveTo(LEFT);
						} catch (IllegalArgumentException ex3) {
							try {
								return moveTo(TOP);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return moveTo(RIGHT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					}
				}
			} else { // try to move up
				try {
					return moveTo(TOP);
				} catch (IllegalArgumentException ex2) {
					if (gotoCol > myCol) { // try to move right
						try {
							return moveTo(RIGHT);
						} catch (IllegalArgumentException ex3) {
							try {
								return moveTo(BOTTOM);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return moveTo(LEFT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					} else { // try to move left
						try {
							return moveTo(LEFT);
						} catch (IllegalArgumentException ex3) {
							try {
								return moveTo(BOTTOM);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
							try {
								return moveTo(RIGHT);
							} catch (IllegalArgumentException ex4) {
								// do nothing
							}
						}
					}
				}
			}
		} return false; // dont move at all
	}

	public int getDamage() {
		return damage;
	}
}